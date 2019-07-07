package com.example.demo.proxy;

import lombok.Data;

@Data
public class XiaoXingXing implements Person{

    private String sex = "女";
    private String name = "小星星";


    @Override
    public void findLove() {
        System.out.println("我是" + this.name + "，性别为" + this.sex + "要找对象的条件为：");
        System.out.println("高富帅");
        System.out.println("有房有车");
        System.out.println("身高180cm，体重70kg");
    }
}
