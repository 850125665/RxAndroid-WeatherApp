package edu.xtu.androidbase.weaher.ui.test;

import android.view.View;
import android.widget.TextView;

import com.android.volley.VolleyError;

import java.util.Random;

import edu.xtu.androidbase.weaher.R;
import edu.xtu.androidbase.weaher.ui.base.BaseFragment;
import edu.xtu.androidbase.weaher.ui.base.TerminalActivity;
import edu.xtu.androidbase.weaher.util.view.LoadView;
import edu.xtu.androidbase.weaher.util.LogUtils;
import edu.xtu.androidbase.weaher.util.volley.ApiRequestFactory;
import edu.xtu.androidbase.weaher.util.volley.ApiRequestListener;

public class Fragment2 extends BaseFragment {

    TextView tv;

    @Override
    protected void initData() {
        if(data!=null){
            LogUtils.d(TAG,data.getString("test"));
            tv.setText(data.getString("test"));
        }
    }

    @Override
    protected void initView(View view) {
        tv = (TextView) view.findViewById(R.id.first_tv);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TerminalActivity.show(mContext,Fragment3.class,null);
            }
        });
    }

    @Override
    protected int getResourceId() {
        return R.layout.fragment2;
    }

    /**
     * 请求网络获取数据
     *
     * @return
     */
    @Override
    protected void getNet(final LoadView.IOnNetListener iOnNetListener) {
        Random r =new Random();
        ApiRequestFactory.regist(mContext, "xx", "xx", "xx", "xx", new ApiRequestListener() {
            @Override
            public void onSuccessResponse(Object response) {
                iOnNetListener.getState(LoadView.LoadResult.success);
            }

            @Override
            public void onErrorResponse(VolleyError error) {
                iOnNetListener.getState(LoadView.LoadResult.error);
            }
        });

    }

}