package com.ppjun.rxjava1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

/**
 * Package :com.ppjun.rxjava1
 * Description :
 * Author :Rc3
 * Created at :2016/12/9 14:31.
 */

public class RxMapFragment extends BaseFragment {
    TextView mArgument;
    TextView mResult;
    Button mAnalyze;

    @Override
    protected void initListeners() {
        mAnalyze.setOnClickListener(this);
    }

    @Override
    protected void initDatas() {
        mArgument.setText("输出 1, 2, 3, 4, 5, 6 和3比较的结果true or false");
    }

    @Override
    protected void initViews(View view) {
        mAnalyze = (Button) view.findViewById(R.id.analyze);
        mArgument = (TextView) view.findViewById(R.id.argument);
        mResult = (TextView) view.findViewById(R.id.result);
    }

    @Override
    protected View setContentView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.layout1, container, false);
    }

    @Override
    public void onClick(View view) {
        mResult.setText("");
        mResult.append("输入参数 1, 2, 3, 4, 5, 6"+"\n");
        if (view.getId() == R.id.analyze) {
            Observable.just(1, 2, 3, 4, 5, 6).map(new Func1<Integer, Boolean>() {
                @Override
                public Boolean call(Integer integer) {
                    mResult.append("Integer-->Boolean"+"\n");
                    return integer < 3 ? true : false;
                }
            }).subscribe(new Subscriber<Boolean>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onNext(Boolean aBoolean) {

                    mResult.append("result："+aBoolean+"\n");
                }
            });

        }
    }
}
