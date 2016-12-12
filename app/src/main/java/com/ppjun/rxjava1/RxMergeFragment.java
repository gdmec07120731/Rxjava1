package com.ppjun.rxjava1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Package :com.ppjun.rxjava1
 * Description :
 * Author :Rc3
 * Created at :2016/12/9 16:30.
 */

public class RxMergeFragment extends BaseFragment {

    TextView mArgument;
    TextView mResult;
    Button mAnalyze;

    @Override
    protected void initListeners() {
        mAnalyze.setOnClickListener(this);
    }

    @Override
    protected void initDatas() {
        mArgument.setText("两个任务并发，全部完成两个请求后更新数据");
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
          mResult.setText("");
        Observable<String> observavle1=Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {

                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                subscriber.onNext("aaa");
                subscriber.onCompleted();
            }
        }).subscribeOn(Schedulers.newThread());
        Observable<String> observavle2=Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {

                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                subscriber.onNext("bbb");
                subscriber.onCompleted();
            }
        }).subscribeOn(Schedulers.newThread());

Observable.merge(observavle1,observavle2).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<String>() {
    @Override
    public void onCompleted() {
        mResult.append("输出完成"+"\n");
    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onNext(String s) {
          mResult.append("得到第一个数据"+s+"\n");
    }
});

    }
}
