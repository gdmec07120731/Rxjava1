package com.ppjun.rxjava1;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Package :com.ppjun.rxjava1
 * Description :
 * Author :Rc3
 * Created at :2016/12/9 14:36.
 */

public abstract class BaseFragment extends Fragment implements View.OnClickListener {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        
       View view=setContentView(inflater,container);
        initViews(view);
        initDatas();
        initListeners();
        return view;
    }

    protected abstract void initListeners();

    protected abstract void initDatas();

    protected abstract void initViews(View view);


    protected abstract View setContentView(LayoutInflater inflater, ViewGroup container);

}
