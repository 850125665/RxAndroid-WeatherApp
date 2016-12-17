package edu.xtu.androidbase.weaher.ui.base;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.trello.rxlifecycle.components.support.RxFragment;

import edu.xtu.androidbase.weaher.R;
import edu.xtu.androidbase.weaher.util.AppInfo;
import edu.xtu.androidbase.weaher.util.view.LoadView;
import edu.xtu.androidbase.weaher.util.LogUtils;
import edu.xtu.androidbase.weaher.util.view.ToolBar;

/**
 * Created by huilin on 2016/8/16.
 */
public abstract class BaseFragment extends RxFragment implements SwipeRefreshLayout.OnRefreshListener {

    public String TAG = this.getClass().getName();
    public SwipeRefreshLayout swipeRefreshLayout;
    public ToolBar toolBar;
    private FrameLayout frameContent;

    public Context mContext;

    public LoadView loadView;

    /**
     * activity传数据需要传参的key值
     */
    public String BUNDLE_DATA = "bundle_data";
    /**
     * 传来的数据
     */
    public Bundle data=null;
    /**
     * 保存crash时的数据
     */
    public static String SAVE_INSTANCE = "saveInstance";
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        LogUtils.d(TAG,"onCreateView");
        if(savedInstanceState!=null){
            data = savedInstanceState.getBundle(SAVE_INSTANCE);
        }
        processExtraData();
        initDataBeforeView();
        View root =  View.inflate(getActivity(),R.layout.fragment_base,null);
        FrameLayout frameLayout = (FrameLayout) root.findViewById(R.id.frame_base_content);
        if(loadView==null){
            mContext=getActivity();
            loadView = new LoadView(getActivity()) {
                @Override
                public View createSuccessView() {
                    return View.inflate(getActivity(),R.layout.view_success,null);
                }

                /**
                 * 请求服务器返回状态，错误，数据空，成功
                 *
                 * @param iOnNetListener
                 * @return
                 */
                @Override
                protected void loadNet(IOnNetListener iOnNetListener) {
                    getNet(iOnNetListener);
                }


            };
        }
        frameLayout.removeAllViews();
        frameLayout.addView(loadView);
        return root;
    }

    protected abstract void initDataBeforeView();


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        LogUtils.d(TAG,"onViewCreated");

        toolBar = (ToolBar) view.findViewById(R.id.tool_bar);
       if(getActivity() instanceof TerminalToolBarActivity){
           ((TerminalToolBarActivity) getActivity()).setSupportActionBar(toolBar.getToolbar());
       }
        toolBar.getToolbar().setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    getActivity().finishAfterTransition();
                }else{
                    getActivity().finish();
                }
            }
        });
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.main_swipe);
        frameContent = (FrameLayout) view.findViewById(R.id.content_frame);
        frameContent.addView(View.inflate(mContext,getResourceId(),null));
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
        initView(view);
        initData();
        loadView.show();
    }

    /**
     * 解析传来的参数
     */
    private void processExtraData() {
        LogUtils.d(TAG,"processExtraData");
        Bundle bundle = getArguments();
        if(bundle!=null){
            data = bundle;
        }
    }

    /**
     * 初始化数据
     */
    protected abstract void initData();

    /**
     * 初始化视图
     */
    protected abstract void initView(View view);

    /**
     * 加载视图资源ID
     * @return
     */
    protected abstract int getResourceId();



    @Override
    public void onRefresh() {
        new Thread(){
            @Override
            public void run() {
                SystemClock.sleep(2000);

                AppInfo.getAppInstant().getUiHandler().post(new Runnable() {
                    @Override
                    public void run() {
                        swipeRefreshLayout.setRefreshing(false);
                        show();
                    }
                });

            }
        }.start();

    }

    /**
     * 请求网络获取数据
     * @return
     */
    protected abstract void getNet(LoadView.IOnNetListener iOnNetListener);

    /**
     * 展示对应页面
     */
    public void show(){
        loadView.show();
    }

    /**
     *
     * @param outState
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        LogUtils.d(TAG,"onSaveInstanceState");
        if(data!=null){
            outState.putBundle(SAVE_INSTANCE,data);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtils.d(TAG,"onDestroy");
    }

    /**
     * 显示toolbar
     */
    public void showToolBar(){
        toolBar.setVisibility(View.VISIBLE);
    }

    /**
     * 设置是否刷新
     * @param enable
     */
    public void setRefreshEnable(boolean enable){
        swipeRefreshLayout.setEnabled(enable);
    }


}
