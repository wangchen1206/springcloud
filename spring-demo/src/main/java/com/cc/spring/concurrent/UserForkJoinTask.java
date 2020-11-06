package com.cc.spring.concurrent;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cc.spring.entity.User;
import com.cc.spring.service.IUserService;
import com.cc.spring.service.impl.UserServiceImpl;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.RecursiveTask;

/**
 * Description
 *
 * @author wangchen
 * @createDate 2020/11/04
 */
@Data
@AllArgsConstructor
public class UserForkJoinTask extends RecursiveTask<List<User>> {
    private Date start;
    private Date end;
    private IUserService userService;


    @Override
    protected List<User> compute() {
        List<User> users = new ArrayList<>();
        if ((end.getTime() - start.getTime()) < 1000 * 20000) {
//            System.out.println(Thread.currentThread().getName());
            System.out.println("start: "+start+"------"+"end: "+end);
            //执行sql查询
            List<User> list = userService.list(new QueryWrapper<User>().lambda()
                    .ge(User::getCreateDate, start)
                    .le(User::getCreateDate, end));
            users.addAll(list);
            return users;
        } else {
            Date inter = new Date((start.getTime() + end.getTime()) / 2);
            UserForkJoinTask leftTask = new UserForkJoinTask(start, inter, userService);
            leftTask.fork();
            UserForkJoinTask rightTask = new UserForkJoinTask(inter, end, userService);
            rightTask.fork();

            users.addAll(leftTask.join());
            users.addAll(rightTask.join());
            return users;
        }
    }


}
