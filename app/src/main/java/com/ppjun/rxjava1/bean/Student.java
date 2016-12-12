package com.ppjun.rxjava1.bean;

/**
 * Package :com.ppjun.rxjava1.bean
 * Description :
 * Author :Rc3
 * Created at :2016/12/9 15:53.
 */

public class Student {
    String name;
    String age;

    public Student(String name, String age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                '}';
    }
}
