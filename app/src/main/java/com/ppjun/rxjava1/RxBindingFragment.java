package com.ppjun.rxjava1;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.jakewharton.rxbinding.view.RxView;
import com.jakewharton.rxbinding.widget.RxTextView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Package :com.ppjun.rxjava1
 * Description :
 * Author :Rc3
 * Created at :2016/12/12 09:52.
 */

public class RxBindingFragment extends BaseFragment {
    private static final String TAG = RxBindingFragment.class.getSimpleName();
    Button mBtn;
    EditText mEditText;
    TextView mTextView;

    @Override
    protected void initListeners() {

        RxView.clicks(mBtn)
                .throttleFirst(1000, TimeUnit.MILLISECONDS)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        mTextView.append("防误触\n");
                    }
                });
        RxTextView.textChanges(mEditText)
                .debounce(500, TimeUnit.MILLISECONDS)
                .observeOn(Schedulers.newThread())
                .map(new Func1<CharSequence, List<String>>() {

                    List<String> list = new ArrayList<String>();

                    @Override
                    public List<String> call(CharSequence charSequence) {
                        if (charSequence.toString().contains("1")) {
                            for (int i = 0; i < 5; i++) {
                                list.add("11 " + i);
                            }
                        }
                        return list;
                    }
                })
                .flatMap(new Func1<List<String>, Observable<String>>() {
                    @Override
                    public Observable<String> call(List<String> strings) {
                        return Observable.from(strings);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .filter(new Func1<String, Boolean>() {
                    @Override
                    public Boolean call(String s) {
                        return !mTextView.getText().toString().contains(s);
                    }
                }).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                Log.i(TAG, "call: " + s);
                mTextView.append(s + "\n");
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {

            }
        });

    }

    @Override
    protected void initDatas() {
        mEditText.setHint("输入包含1的数字");
    }

    @Override
    protected void initViews(View view) {
        mBtn = (Button) view.findViewById(R.id.btn_click);
        mTextView = (TextView) view.findViewById(R.id.binding_result);
        mEditText = (EditText) view.findViewById(R.id.edittext_tips);
    }

    @Override
    protected View setContentView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.binding_view, container, false);
    }

    @Override
    public void onClick(View view) {

    }
}
