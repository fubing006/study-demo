package com.example.demo.proxy;

/**
 * @Desc
 * @Author gzl
 * @Date 2019/7/3
 */
public class TestFindLove {
    public static void main(String[] args) throws Exception {
        // 无代理时
        System.out.println("无代理时");
        new XiaoXingXing().findLove();
        // 有代理
        System.out.println("有代理");
        Person person = (Person) new MeiPo().getInstance(new XiaoXingXing());
        person.findLove();


    }
}
