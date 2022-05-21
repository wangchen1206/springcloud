package com.cc.test.self.springboot.starter.config;

import java.util.Objects;

/**
 * @ClassName Student
 * @Desc TODO
 * @Author DELL
 * @Date 2022/5/3
 * @Version 1.0
 **/
public class Student {

    private Integer age;

    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(age, student.age) &&
                Objects.equals(name, student.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(age, name);
    }

    public Student(Integer age, String name) {
        this.age = age;
        this.name = name;
    }

    public static void main(String[] args) {
//        Student student = new Student(1,"a");
//        Student student1 = new Student(1,"a");
//
//        System.out.println(student.hashCode());
//        System.out.println(student1.hashCode());
//        System.out.println(student.equals(student1));

        System.out.println(System.getProperty("sun.boot.class.path"));
        System.out.println(System.getProperty("java.ext.dirs"));
        System.out.println(System.getProperty("java.class.path"));

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        System.out.println(classLoader);
        ClassLoader parent = classLoader.getParent();
        System.out.println(parent);
        ClassLoader parent1 = parent.getParent();
        System.out.println(parent1);
        ClassLoader parent2 = parent1.getParent();
        System.out.println(parent2);

    }
}
