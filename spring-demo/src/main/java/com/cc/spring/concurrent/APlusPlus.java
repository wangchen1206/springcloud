package com.cc.spring.concurrent;

/**
 * Description
 *
 * @author wangchen
 * @createDate 2020/11/09
 */
public class APlusPlus {
    public static void main(String[] args) {
//        i++,是先赋值，后加一，++i是先加一后赋值,楼主发的五个i=++i，是i在内存自增一，然后进寄存器，最后赋值的时候i赋值就是1，
//        而五个i=i++, 是赋值之后再自增1，i=0这个值先进寄存器，然后i在内存自增1，最后赋值的时候寄存器出来的0值会冲掉1值成为最终结果，
//        所以无论多少个结果都会是0。
        aPlusPlus();
        plusPlusB();
        aBPlusPlus();


    }

    private static void aPlusPlus() {
        int a = 0;
        for (int i = 0; i < 99; i++) {
            a = a++; //a++是 先拿出来使用，再++
        }
        System.out.println(a);
    }

    private static void plusPlusB() {
        int b = 0;
        for (int i = 0; i < 99; i++) {
            b = ++b;//++b是 先++，再拿出来使用
        }
        System.out.println(b);
    }

    private static void aBPlusPlus() {
        Integer a = 0;
        int b = 0;
        for (int i = 0; i < 99; i++) {
            a = a++;
            b = a++; //b 永远比 a小1，先赋值，a再+1
        }
        System.out.println(a);
        System.out.println(b);
    }
}
