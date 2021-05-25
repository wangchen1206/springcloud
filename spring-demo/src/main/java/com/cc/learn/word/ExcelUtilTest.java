package com.cc.learn.word;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.cc.learn.entity.User;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Description
 *
 * @author wangchen
 * @createDate 2021/04/01
 */
public class ExcelUtilTest {

    public static final String tempPath = "D:\\temp\\";

    public static void main(String[] args) {
        createExcelByMap();
    }

    public static void createExcelByList(){
        List<User> list = new ArrayList<>();

        list.add(new User("zhangsan","职业1",new Date()));

        list.add(new User("zhangsan1","职业2",new Date()));

        list.add(new User("zhangsan2","职业1",new Date()));

        list.add(new User("zhangsan3","职业1",new Date()));

        list.add(new User("zhangsan4","职业1",new Date()));

// 通过工具类创建writer，默认创建xls格式

        ExcelWriter writer = ExcelUtil.getWriter(true);

//自定义标题别名

        writer.addHeaderAlias("username", "姓名");

        writer.addHeaderAlias("userInfor", "职业");

        writer.addHeaderAlias("createDate", "生日");

// 合并单元格后的标题行，使用默认标题样式

        writer.merge(2, "申请人员信息");

// 一次性写出内容，使用默认样式，强制输出标题

        writer.write(list, true);

        writer.flush(new File("D:\\temp\\a.xlsx"));
    }

    public static void createExcelByMap(){
        HashMap<String, Number> counts = CollectionUtil.newHashMap();
        counts.put("事件总数",100);
        counts.put("事件解决数",80);
        counts.put("事件办结数",66);
        counts.put("解决率",0.80);
        counts.put("办结率",0.66);
        counts.put("满意率",0.87);
        counts.put("回访数",99);
        ExcelWriter writer = ExcelUtil.getWriter(true);
        writer.merge(counts.size()-1, "接诉即办数据项");
        writer.writeRow(counts,true);
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String dateS = dateTimeFormatter.format(now);
        writer.flush(new File(tempPath+dateS+".xlsx"));
    }
}
