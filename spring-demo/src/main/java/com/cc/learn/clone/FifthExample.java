package com.cc.learn.clone;

import com.alibaba.fastjson.JSON;

/**
 * 深克隆实现方式五：通过 JSON 工具实现
 */
public class FifthExample {
    public static void main(String[] args) throws CloneNotSupportedException {
        // 创建对象
        Address address = new Address(110, "北京");
        People p1 = new People(1, "Java", address);
        // 调用 Fastjson 克隆对象 会报错，需将Address类移动到包下边。也就是public
        String s = JSON.toJSONString(p1);
        People p2 = JSON.parseObject(s,People.class);
        // 修改原型对象
        p1.getAddress().setCity("西安");
        // 输出 p1 和 p2 地址信息
        System.out.println("p1:" + p1.getAddress().getCity() +
                " p2:" + p2.getAddress().getCity());
    }
    /**
     * 用户类
     */
    static class People {
        private Integer id;
        private String name;
        private Address address;

        public People(Integer id, String name, Address address) {
            this.id = id;
            this.name = name;
            this.address = address;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Address getAddress() {
            return address;
        }

        public void setAddress(Address address) {
            this.address = address;
        }
    }
    /**
     * 地址类
     */
    static class Address {
        private Integer id;
        private String city;

        public Address(Integer id, String city) {
            this.id = id;
            this.city = city;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }
    }
}