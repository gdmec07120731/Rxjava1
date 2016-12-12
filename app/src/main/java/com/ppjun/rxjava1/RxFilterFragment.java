package com.ppjun.rxjava1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Package :com.ppjun.rxjava1
 * Description :
 * Author :Rc3
 * Created at :2016/12/12 11:50.
 */

public class RxFilterFragment extends BaseFragment {

    TextView mArgument;
    TextView mResult;
    Button mAnalyze;

    @Override
    protected void initListeners() {
        mAnalyze.setOnClickListener(this);
    }

    @Override
    protected void initDatas() {
        mArgument.setText("输出1-10能被2整除的数");
    }

    @Override
    protected void initViews(View view) {
        mArgument = (TextView) view.findViewById(R.id.argument);
        mResult = (TextView) view.findViewById(R.id.result);
        mAnalyze = (Button) view.findViewById(R.id.analyze);

    }

    @Override
    protected View setContentView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.layout1, container, false);
    }

    @Override
    public void onClick(View view) {
        Integer[] integer = new Integer[10];
        for (int i = 0; i < 10; i++) {
            integer[i] = i+1;
        }
        Observable.from(integer)
                .filter(new Func1<Integer, Boolean>() {
                    @Override
                    public Boolean call(Integer integer) {
                        return integer % 2 == 0;
                    }
                }).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                mResult.append(integer+" ");
            }
        });
    }
}
