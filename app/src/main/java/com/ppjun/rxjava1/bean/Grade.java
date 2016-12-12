package com.ppjun.rxjava1.bean;

/**
 * Package :com.ppjun.rxjava1.bean
 * Description :
 * Author :Rc3
 * Created at :2016/12/9 15:53.
 */

public class Grade {

    Student[] mStudents;

    public Grade(Student[] student) {
        this.mStudents = student;
    }

    public Student[] getStudent() {
        return mStudents;
    }
}
