package com.ppjun.rxjava1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * Package :com.ppjun.rxjava1
 * Description :
 * Author :Rc3
 * Created at :2016/12/12 14:19.
 */

public class RxIntervalFragment extends BaseFragment {

    TextView mArgument;
    TextView mResult;
    Button mAnalyze;
    Subscription mSubscription = null;
    boolean mIsUnsubscribed=true;

    @Override
    protected void initListeners() {
        mAnalyze.setOnClickListener(this);
    }

    @Override
    protected void initDatas() {
        mArgument.setText("定时器 每一秒发送打印一个数字\n ");
        mAnalyze.setText("开始定时");
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

if(mIsUnsubscribed){
            mSubscription = Observable.interval(1, TimeUnit.SECONDS)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Action1<Long>() {
                        @Override
                        public void call(Long aLong) {
                            mResult.setText(aLong + "");
                        }
                    });
            mAnalyze.setText("取消定时");
            mIsUnsubscribed=false;
        }else{
            if (mSubscription != null || !mSubscription.isUnsubscribed()) {
                mSubscription.unsubscribe();

                mAnalyze.setText("开始定时");
                mIsUnsubscribed=true;
            }

        }



    }
}
