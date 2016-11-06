package edu.xtu.androidbase.weaher.ui.test;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import edu.xtu.androidbase.weaher.R;
import edu.xtu.androidbase.weaher.util.AppMethods;

public class Fragment1 extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.v("fagment1","xx");
        return inflater.inflate(R.layout.fragment1, null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button b = (Button) view.findViewById(R.id.bt_one);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Bundle bundle = new Bundle();
//                bundle.putString("test","test");
//                Intent intent = new Intent();
//                intent.putExtra("bundle_data",bundle);
//                getActivity().setResult(Activity.RESULT_OK,intent);
//                getActivity().finish();
                Dialog loadingDialog = AppMethods.createLoadingDialog(getActivity());
                AppMethods.showDialog(getActivity(),loadingDialog);
            }
        });
    }




}
