package com.cc.learn.concurrent;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Description
 *
 * @author wangchen
 * @createDate 2020/12/30
 */
public class AtomicRefrenceTest {
    public static void main(String[] args) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        AtomicReference<SimpleDateFormat> atomicReference = new AtomicReference<>(simpleDateFormat);

        Date date1 = new Date(1609222977000L); //2020-12-29 14:22:57
        Date date2 = new Date(1609309377000L); //2020-12-30 14:22:57
        new Thread(()-> {
            SimpleDateFormat simpleDateFormat1 = atomicReference.get();
            simpleDateFormat1.format(date1);
        }).start();
        new Thread(()-> {
            SimpleDateFormat simpleDateFormat1 = atomicReference.get();
            simpleDateFormat1.format(date2);
        }).start();
    }
}
