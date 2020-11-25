package com.cc.spring.concurrent.visibility;

/**
 * 描述可见性问题
 *
 * @author wangchen
 * @createDate 2020/11/20
 */
public class VisibilityProblem {
    int a = 10;
    int b = 20;
    private void change() {
        a = 30;
        b = a;
    }
    private void print() {
        System.out.println("b=" + b + ";a=" + a);
    }
    public static void main(String[] args) {
        while (true) {
            VisibilityProblem problem = new VisibilityProblem();
            new Thread(() -> {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                problem.change();
            }).start();
            new Thread(() -> {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                problem.print();
            }).start();
        }
    }
}
