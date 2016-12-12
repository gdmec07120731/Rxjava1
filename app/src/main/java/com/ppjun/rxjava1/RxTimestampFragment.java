package com.ppjun.rxjava1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

import rx.Observable;
import rx.functions.Action1;
import rx.schedulers.Timestamped;

/**
 * Package :com.ppjun.rxjava1
 * Description :
 * Author :Rc3
 * Created at :2016/12/12 15:57.
 */

public class RxTimestampFragment extends BaseFragment {


    TextView mArgument;
    TextView mResult;
    Button mAnalyze;

    @Override
    protected void initListeners() {
        mAnalyze.setOnClickListener(this);
    }

    @Override
    protected void initDatas() {
        mArgument.setText("给数据列表加上时间戳");
    }

    @Override
    protected void initViews(View view) {
        mArgument = (TextView) view.findViewById(R.id.argument);
        mResult = (TextView) view.findViewById(R.id.result);
        mAnalyze= (Button) view.findViewById(R.id.analyze);
    }

    @Override
    protected View setContentView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.layout1, container, false);
    }

    @Override
    public void onClick(View view) {
        Integer[] integer={1,2,3,4,5};
        Observable.from(integer).timestamp()
                .subscribe(new Action1<Timestamped<Integer>>() {
                    @Override
                    public void call(Timestamped<Integer> integerTimestamped) {
                        SimpleDateFormat sdf=new SimpleDateFormat("HH:mm");
                        mResult.append(integerTimestamped.getValue()+"   "+ sdf.format(new Date(integerTimestamped.getTimestampMillis()))+"\n");
                    }
                });

    }
}
