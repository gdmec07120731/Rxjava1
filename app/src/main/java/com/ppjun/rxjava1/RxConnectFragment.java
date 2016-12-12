package com.ppjun.rxjava1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import rx.Observable;
import rx.functions.Action1;
import rx.observables.ConnectableObservable;

/**
 * Package :com.ppjun.rxjava1
 * Description :
 * Author :Rc3
 * Created at :2016/12/12 15:11.
 */

public class RxConnectFragment extends BaseFragment {

    TextView mArgument;
    TextView mResult;
    Button mAnalyze;
    Button mConnect;

    @Override
    protected void initListeners() {
        mAnalyze.setOnClickListener(this);
        mConnect.setOnClickListener(this);
    }

    @Override
    protected void initDatas() {
        mConnect.setVisibility(View.VISIBLE);
        mArgument.setText("Observable 发送6个事件 ，两个观察者同时接受这6个事件\n" +
                "要求每发送一个事件，观察者A和B都会收到，而不是先把所有事件都发送给A然后再发送给B");
    }

    @Override
    protected void initViews(View view) {
        mArgument = (TextView) view.findViewById(R.id.argument);
        mResult = (TextView) view.findViewById(R.id.result);
        mAnalyze = (Button) view.findViewById(R.id.analyze);
        mConnect = (Button) view.findViewById(R.id.connect);

    }

    @Override
    protected View setContentView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.layout1, container, false);
    }

    @Override
    public void onClick(View view) {
        Integer[] integer = {1, 2, 3, 4, 5, 6};
        Action1 action1 = new Action1<Integer>() {
            @Override
            public void call(Integer o) {
                mResult.append("A received" + o+"\n");
            }
        };
        Action1 action2 = new Action1<Integer>() {
            @Override
            public void call(Integer o) {
                mResult.append("B received" + o+"\n");
            }
        };
        if (view.getId() == R.id.analyze) {
            mResult.setText("");
            Observable mObservable = Observable.from(integer);

            mObservable.subscribe(action1);
            mObservable.subscribe(action2);

        } else if (view.getId() == R.id.connect) {
            mResult.setText("");
            ConnectableObservable connectableObservable = Observable.from(integer).publish();

            connectableObservable.subscribe(action1);
            connectableObservable.subscribe(action2);
            connectableObservable.connect();
        }
    }
}
