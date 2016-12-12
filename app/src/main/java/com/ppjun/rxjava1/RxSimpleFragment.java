package com.ppjun.rxjava1;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.Arrays;
import java.util.Iterator;

import rx.Observable;
import rx.Subscriber;

/**
 * Package :com.ppjun.rxjava1
 * Description :
 * Author :Rc3
 * Created at :2016/12/9 11:13.
 */

public class RxSimpleFragment extends Fragment {

    TextView mArgument;
    TextView mResult;
    Button mAnalyze;

    String[] str = {"NASA：全新喷气引擎测试成功，可能彻底改变空中旅行", "移动、联通和电信的优惠政策为什么只给新用户？"
            , "苹果发布macOS 10.12.2 Beta6更新"};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout1, container, false);

        initview(view);
        initDatas();
        initListeners();
        return view;
    }

    private void initListeners() {

        mAnalyze.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mResult.setText("");
                start();
            }
        });
    }

    private void initDatas() {
        Iterator it = Arrays.asList(str).iterator();
        while (it.hasNext()) {
            mArgument.append(it.next().toString()+"\n");
        }

    }

    private void initview(View view) {
        mArgument = (TextView) view.findViewById(R.id.argument);
        mResult = (TextView) view.findViewById(R.id.result);
        mAnalyze= (Button) view.findViewById(R.id.analyze);

    }



    private void start() {
        Observable<String> observable = createObservable();
        Subscriber<String> subscribe = createSubscriber();
        observable.subscribe(subscribe);
    }

    private Subscriber<String> createSubscriber() {
        return new Subscriber<String>() {
            @Override
            public void onCompleted() {
                mResult.append("加载完成！"+"\n");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                mResult.append("开始读取："+"\n");
                mResult.append(s);
            }
        };
    }

    private Observable<String> createObservable() {
        return Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                Iterator it = Arrays.asList(str).iterator();
                while (it.hasNext()) {
                    subscriber.onNext(it.next().toString()+"\n");

                }
                subscriber.onCompleted();
            }
        });
    }
}
