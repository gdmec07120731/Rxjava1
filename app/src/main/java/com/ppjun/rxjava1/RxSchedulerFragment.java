package com.ppjun.rxjava1;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Package :com.ppjun.rxjava1
 * Description :
 * Author :Rc3
 * Created at :2016/12/9 15:08.
 */

public class RxSchedulerFragment extends BaseFragment {
    TextView mArgument;
    TextView mResult;
    Button mAnalyze;
    LinearLayout mLayout;

    @Override
    protected void initListeners() {
        mAnalyze.setOnClickListener(this);
    }

    @Override
    protected void initDatas() {
        mArgument.setText("显示图片");
    }

    @Override
    protected void initViews(View view) {
        mArgument = (TextView) view.findViewById(R.id.argument);
        mResult = (TextView) view.findViewById(R.id.result);
        mAnalyze = (Button) view.findViewById(R.id.analyze);
        mLayout = (LinearLayout) view.findViewById(R.id.layout1);
    }

    @Override
    protected View setContentView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.layout1, container, false);

    }

    @Override
    public void onClick(View view) {
        mResult.setText("");
        Observable.create(new Observable.OnSubscribe<Bitmap>() {
            @Override
            public void call(Subscriber<? super Bitmap> subscriber) {

                mResult.append("create线程：" + Thread.currentThread().getName() + "\n");
                subscriber.onNext(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
                subscriber.onCompleted();
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Bitmap>() {
                    @Override
                    public void call(Bitmap bitmap) {
                        ImageView image = new ImageView(getActivity());
                        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                        image.setLayoutParams(lp);
                        image.setImageBitmap(bitmap);
                        mLayout.addView(image);
                    }
                });


    }
}
