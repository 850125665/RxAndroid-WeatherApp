package edu.xtu.androidbase.weaher.util.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import edu.xtu.androidbase.weaher.R;
import edu.xtu.androidbase.weaher.util.LogUtils;

/**
 * Created by huilin on 2016/8/20.
 */
public abstract class LoadView extends FrameLayout {
    private String TAG = this.getClass().getSimpleName();
    /**
     * 数据为空布局
     */
    private View emptyView;
    /**
     * 加载中布局
     */
    private View loadingView;
    /**
     * 错误页面
     */
    private View errorView;
    /**
     * 成功页面
     */
    private View successView;

    private Context mContext;

    /**
     * 未知状态
     */
    public static int UNKNOW_STATE = 0;

    /**
     * 加载状态
     */
    public static int LOADING_STATE = 1;

    /**
     * 无数据状态
     */
    public static int EMPTY_STATE = 2;

    /**
     * 错误状态
     */
    public static int ERROR_STATE = 3;

    /**
     * 成功状态
     */
    public static int SUCCESS_STATE = 4;

    /**
     * 当前状态
     */
    public int currentState = UNKNOW_STATE;

    /**
     * 上个状态
     */
    public int beforeState = UNKNOW_STATE;


    /**
     * 服务器返回状态枚举，错误，空数据，成功
     */
    public enum LoadResult {
        error(ERROR_STATE), empty(EMPTY_STATE), success(SUCCESS_STATE);
        int value;

        LoadResult(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    public LoadView(Context context) {
        this(context, null);
    }

    public LoadView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoadView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        init();
    }

    private void init() {
        emptyView = createEmptyView();
        loadingView = createLoadingView();
        errorView = createErrorView();
        successView = createSuccessView();
        if (emptyView != null) {
            this.addView(emptyView);
        }
        if (loadingView != null) {
            this.addView(loadingView);
        }
        if (errorView != null) {
            this.addView(errorView);
        }
        if (successView != null) {
            this.addView(successView);
        }
        showPage();
    }

    /**
     * 加载当前展示布局
     *
     * @return
     */
    public abstract View createSuccessView();

    private View createErrorView() {

        if (errorView == null) {
            errorView = View.inflate(mContext, R.layout.view_error, null);
            Button loadBt = (Button) errorView.findViewById(R.id.load_bt);
            loadBt.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    show();
                }
            });
        }
        return errorView;
    }

    private View createLoadingView() {
        if (loadingView == null) {
            loadingView = View.inflate(mContext, R.layout.view_loading, null);
        }
        return loadingView;

    }

    private View createEmptyView() {
        if (emptyView == null) {
            emptyView = View.inflate(mContext, R.layout.view_empty, null);
            Button loadBt = (Button) emptyView.findViewById(R.id.load_bt);
            loadBt.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    show();
                }
            });
        }
        return emptyView;
    }

    public void showPage() {

        if (loadingView != null) {
            loadingView.setVisibility((currentState == UNKNOW_STATE || currentState == LOADING_STATE) && beforeState != SUCCESS_STATE ? VISIBLE : INVISIBLE);
        }
        if (emptyView != null) {
            emptyView.setVisibility(currentState == EMPTY_STATE ? VISIBLE : INVISIBLE);
        }
        if (errorView != null) {
            errorView.setVisibility(currentState == ERROR_STATE && beforeState != SUCCESS_STATE ? VISIBLE : INVISIBLE);
        }
        if (successView != null) {
            successView.setVisibility(currentState == SUCCESS_STATE || (beforeState == SUCCESS_STATE && currentState != EMPTY_STATE) ? VISIBLE : INVISIBLE);
        }
    }

    /**
     * 请求服务器加载数据页面
     */
    public void show() {
        if (currentState == UNKNOW_STATE || currentState == LOADING_STATE || currentState == EMPTY_STATE || currentState == ERROR_STATE) {
            currentState = LOADING_STATE;
        }
        loadNet(new IOnNetListener() {
            @Override
            public void getState(LoadResult result) {

                currentState = result.getValue();
                if (currentState == SUCCESS_STATE || currentState == EMPTY_STATE) {
                    beforeState = currentState;
                }
                showPage();
            }
        });
        showPage();
    }

    /**
     * 请求服务器返回状态，错误，数据空，成功
     *
     * @return
     */
    protected abstract void loadNet(IOnNetListener iOnNetListener);

    public interface IOnNetListener {
        void getState(LoadResult result);

    }


}
