package com.zcc.frame.fragment;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zcc.frame.R;

/**
 * Copyright:更美互动信息科技有限公司
 * FileName: TestFragment
 * Author: zcc
 * Date: 2019-10-21 16:32
 * Description: 测试生命周期
 */
public class TestFragment extends Fragment {
    public TestFragment() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.e("zcc", "3=======onAttach");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("zcc", "3=======onCreate");

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.e("zcc", "3=======onCreateView");
        View view = inflater.inflate(R.layout.activity_choose, null);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.e("zcc", "3=======onViewCreated");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.e("zcc", "3=======onActivityCreated");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.e("zcc", "3=======onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("zcc", "3=======onResume");
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.e("zcc", "3=======onConfigurationChanged");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e("zcc", "3=======onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.e("zcc", "3=======onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.e("zcc", "3=======onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("zcc", "3=======onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.e("zcc", "3=======onDetach");
    }
}
