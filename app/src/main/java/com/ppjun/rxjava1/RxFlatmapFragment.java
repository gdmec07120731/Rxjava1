package com.ppjun.rxjava1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.ppjun.rxjava1.bean.Grade;
import com.ppjun.rxjava1.bean.Student;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

/**
 * Package :com.ppjun.rxjava1
 * Description :
 * Author :Rc3
 * Created at :2016/12/9 15:44.
 */

public class RxFlatmapFragment extends BaseFragment {
    TextView mArgument;
    Button mAnalyze;
    TextView mResult;
    Grade[] mGrade;

    @Override
    protected void initListeners() {
       mAnalyze.setOnClickListener(this);
    }

    @Override
    protected void initDatas() {
        mArgument.setText("打印一个学校2个班学生的信息");
        mGrade = new Grade[2];
        Student[] clazz1 = new Student[5];
        for (int i = 0; i < 5; i++) {
            Student student = new Student("小米" + i, "20");
            clazz1[i] = student;
        }
        mGrade[0] = new Grade(clazz1);
        Student[] clazz2 = new Student[5];
        for (int i = 0; i < 5; i++) {
            Student student = new Student("苹果" + i, "22");
            clazz2[i] = student;
        }
        mGrade[1] = new Grade(clazz2);
    }

    @Override
    protected void initViews(View view) {
        mAnalyze = (Button) view.findViewById(R.id.analyze);
        mResult = (TextView) view.findViewById(R.id.result);
        mArgument = (TextView) view.findViewById(R.id.argument);
    }

    @Override
    protected View setContentView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.layout1, container, false);
    }

    @Override
    public void onClick(View view) {
        mResult.setText("");
        Observable.from(mGrade).flatMap(new Func1<Grade, Observable<Student>>() {
            @Override
            public Observable<Student> call(Grade grade) {
                return Observable.from(grade.getStudent());
            }
        }).subscribe(new Subscriber<Student>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Student student) {
                mResult.append(student.toString()+"\n");
            }
        });

    }

}
