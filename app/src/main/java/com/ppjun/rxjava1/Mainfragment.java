package com.ppjun.rxjava1;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Package :com.ppjun.rxjava1
 * Description :
 * Author :Rc3
 * Created at :2016/12/9 11:14.
 */

public class Mainfragment extends Fragment implements View.OnClickListener {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        initViews(view);
        return view;
    }

    private void initViews(View view) {

        view.findViewById(R.id.btn_normal).setOnClickListener(this);
        view.findViewById(R.id.btn2).setOnClickListener(this);
        view.findViewById(R.id.btn3).setOnClickListener(this);
        view.findViewById(R.id.btn4).setOnClickListener(this);
        view.findViewById(R.id.btn5).setOnClickListener(this);
        view.findViewById(R.id.btn6).setOnClickListener(this);
        view.findViewById(R.id.btn7).setOnClickListener(this);
        view.findViewById(R.id.btn8).setOnClickListener(this);
        view.findViewById(R.id.btn9).setOnClickListener(this);
        view.findViewById(R.id.btn10).setOnClickListener(this);
        view.findViewById(R.id.btn11).setOnClickListener(this);
        view.findViewById(R.id.btn12).setOnClickListener(this);
    }


    private void gotoFragment(Fragment fragment) {
        FragmentManager manager = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.framelayout, fragment);
        transaction.addToBackStack(null);
        transaction.commit();

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.btn_normal:

                gotoFragment(new RxSimpleFragment());
                break;
            case R.id.btn2:
                gotoFragment(new RxSchedulerFragment());
                break;
            case R.id.btn3:
                gotoFragment(new RxMapFragment());
                break;
            case R.id.btn4:
                gotoFragment(new RxFlatmapFragment());
                break;
            case R.id.btn5:
                gotoFragment(new RxMergeFragment());
                break;
            case R.id.btn6:
                gotoFragment(new RxBindingFragment());
                break;
            case R.id.btn7:
                gotoFragment(new RxFilterFragment());
                break;
            case R.id.btn8:
                gotoFragment(new RxTakeFragment());
                break;
            case R.id.btn9:
                gotoFragment(new RxIntervalFragment());
                break;
            case R.id.btn10:
                gotoFragment(new RxToSortedListFragment());
                break;
            case R.id.btn11:
                gotoFragment(new RxConnectFragment());
                break;
            case R.id.btn12:
                gotoFragment(new RxTimestampFragment());
                break;
        }
    }
}
