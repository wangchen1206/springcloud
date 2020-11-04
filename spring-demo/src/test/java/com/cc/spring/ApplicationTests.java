package com.cc.spring;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cc.spring.concurrent.UserForkJoinTask;
import com.cc.spring.entity.DemoProperties;
import com.cc.spring.entity.User;
import com.cc.spring.service.IUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

@SpringBootTest
class ApplicationTests {

    @Autowired
    private IUserService userService;

    @Autowired
    private DemoProperties demoProperties;

    @Test
    void contextLoads() {
//        System.out.println(11);
//        User cc = userService.getOne(new QueryWrapper<User>().lambda().eq(User::getUsername, "cc"));
//        System.out.println(cc.getUsername()+"---"+cc.getUserInfor());
        System.out.println(demoProperties.getUuid());

    }

    @Test
    void testBatch() {

        Date date = new Date();
        List<User> users = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            Date d = new Date();
            User u = new User();
            u.setUserInfor("aaa"+i);
            u.setUsername("bbb"+i);
            d.setTime(date.getTime()-i*1000l);
            u.setCreateDate(d);
            users.add(u);
        }

        userService.saveBatch(users);

    }

    @Test
    void testForkJoin() throws ParseException {
        long s1 = System.currentTimeMillis();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //2020-11-03 13:35:57  2020-11-04 17:22:36
        Date start = simpleDateFormat.parse("2020-11-03 13:35:57");
        Date end = simpleDateFormat.parse("2020-11-04 17:22:36");
        ForkJoinPool pool = new ForkJoinPool();
        ForkJoinTask<List<User>> result = pool.submit(new UserForkJoinTask(start, end, userService));
        try {
            List<User> users = result.get();
            System.out.println(users.size());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        long s2 = System.currentTimeMillis();

        System.out.println(s2-s1);
    }

    @Test
    void testSelect() throws ParseException {
        long s1 = System.currentTimeMillis();


        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //2020-11-03 13:35:57  2020-11-04 17:22:36
        Date start = simpleDateFormat.parse("2020-11-03 13:35:57");
        Date end = simpleDateFormat.parse("2020-11-04 17:22:36");
        List<User> list = userService.list(new QueryWrapper<User>().lambda()
                .ge(User::getCreateDate, start)
                .le(User::getCreateDate, end));
        System.out.println(list.size());

        long s2 = System.currentTimeMillis();
        System.out.println(s2-s1);
    }


}
