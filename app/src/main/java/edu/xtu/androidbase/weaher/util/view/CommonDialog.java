package edu.xtu.androidbase.weaher.util.view;

import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import edu.xtu.androidbase.weaher.R;
import edu.xtu.androidbase.weaher.util.AppInfo;

/**
 * Created by huilin on 2016/12/3.
 */

public class CommonDialog extends Dialog {


    @Bind(R.id.tv_content)
    TextView tvContent;
    @Bind(R.id.bt_left)
    public Button btLeft;
    @Bind(R.id.bt_right)
    public Button btRight;
    @Bind(R.id.img_icon)
    ImageView imgIcon;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.ll_title)
    LinearLayout llTitle;

    public CommonDialog(Context context, Builder builder) {
        super(context, R.style.dialog);
        setContentView(R.layout.commom_dialog);
        ButterKnife.bind(this);
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.width = AppInfo.getAppInstant().getScreenWitdh() * 11 / 12;
        attributes.gravity = Gravity.CENTER;
        if (!TextUtils.isEmpty(builder.content)) {
            tvContent.setText(builder.content);
        }
        if (!TextUtils.isEmpty(builder.left)) {
            btLeft.setText(builder.left);
        }
        if (!TextUtils.isEmpty(builder.right)) {
            btRight.setText(builder.right);
        }
        if (!TextUtils.isEmpty(builder.title)) {
            tvTitle.setText(builder.title);
        } else {
            llTitle.setVisibility(View.GONE);
        }
        if(builder.imgRid>0){
            imgIcon.setBackgroundResource(builder.imgRid);
        }


    }


    public static class Builder {
        Context mContext;
        String content;
        String left;
        String right;
        String title;
        int imgRid;

        public Builder(Context context) {
            mContext = context;
        }

        public Builder setLeft(String left) {
            this.left = left;
            return this;
        }

        public Builder setRight(String right) {
            this.right = right;
            return this;
        }

        public Builder setContent(String content) {
            this.content = content;
            return this;
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setImgRid(int imgRid) {
            this.imgRid = imgRid;
            return this;
        }

        public CommonDialog create() {
            CommonDialog commonDialog = new CommonDialog(mContext, this);
            return commonDialog;
        }
    }

}
