package edu.xtu.androidbase.weaher.util.view;


import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import edu.xtu.androidbase.weaher.R;

/**
 * Created by huilin on 2016/11/29.
 */

public class ToolBar extends FrameLayout {

    @Bind(R.id.tool_title)
    TextView toolTitle;
    @Bind(R.id.tool_subtitle)
    TextView toolSubtitle;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    private Context mContext;

    private String title = "";
    private String subtitle = "";
    private boolean isShowBackIcon = false;

    public ToolBar(Context context) {
        this(context, null);
    }

    public ToolBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ToolBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ToolBar);
        try {
            title = typedArray.getString(R.styleable.ToolBar_title);
            subtitle = typedArray.getString(R.styleable.ToolBar_subtitle);
            isShowBackIcon = typedArray.getBoolean(R.styleable.ToolBar_show_back_icon,false);
        }finally {
            typedArray.recycle();
        }
        mContext = context;
        initView();
    }

    private void initView() {
        View view = View.inflate(mContext, R.layout.view_toolbar, null);
        this.addView(view);
        ButterKnife.bind(this, view);
        toolbar.setTitle("");
        if(isShowBackIcon){
            toolbar.setNavigationIcon(R.mipmap.left);
        }else{
            toolbar.setNavigationIcon(null);
        }
        toolTitle.setText(title);
        toolSubtitle.setText(subtitle);

    }

    public Toolbar getToolbar(){
        return toolbar;
    }

    /**
     * 设置返回的图标
     * @param rId
     */
    public void setBackIcon(int rId){
        toolbar.setNavigationIcon(rId);
    }

    /**
     * 设置标题
     * @param title
     */
    public void setToolTitle(String title){
        toolTitle.setText(title);
    }

    /**
     * 设置小标题
     * @param subtitle
     */
    public void setToolSubtitle(String subtitle){
        toolSubtitle.setText(subtitle);
    }

    /**
     * 返回点击事件
     * @param backOnclickLisner
     */
    public void setBackOnclickListener(OnClickListener backOnclickLisner){
        toolbar.setNavigationOnClickListener(backOnclickLisner);
    }

    /**
     * 小标题点击事件
     * @param subtitleOnClickListener
     */
    public void setToolSubtitleOnClickListener(OnClickListener subtitleOnClickListener){
        toolSubtitle.setOnClickListener(subtitleOnClickListener);
    }

    /**
     * 隐藏小标题
     */
    public void hideSubtitle(){
        toolSubtitle.setVisibility(GONE);
    }

    /**
     * 显示小标题
     */
    public void showSubtitle(){
        toolSubtitle.setVisibility(VISIBLE);
    }

    public void hideBackIcom(){
        toolbar.setNavigationIcon(null);
    }


}
