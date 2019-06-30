package com.example.demo;

public class TestWater {
    public int trap(int[] height) {
        if( height==null||height.length==0){
            return 0;
        }
        int bigHeight = 0;
        int smallHeiht = 0;
        int totalNum = 0;
        //假设右边有无限高的柱子，从而忽略是否形成U字形的条件，
        //算出柱子往右的所有体积
        for (int i = 0; i < height.length; i++) {
            if (height[i] > bigHeight) {
                bigHeight = height[i];
            }else{
                totalNum += bigHeight - height[i];
            }
        }
        //移除柱子，减去流走的水。
        for (int i = height.length-1; height[i] < bigHeight; i--) {
            //U字形凹进去的一部分不会流走，
            //流走的高度其实就是柱子的高度减去右边的高度
            if (height[i] > smallHeiht) {
                smallHeiht = height[i];
            }
            totalNum -= bigHeight-smallHeiht;
        }
        return totalNum;
    }
    public static void main(String[] args) {
        int[] array = {0,1,0,2,1,3,1};
        TestWater s = new TestWater();
        System.out.println(s.trap(array));
    }
}