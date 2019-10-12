package com.shushan.thomework101.mvp.utils;

/**
 * @ClassName: StudentUtil
 * @Desciption: //TODO
 * @date: 2019-10-11
 */
public class StudentUtil {
    /**
     * 根据选择的学生类型显示字符串
     * paidType:学生付费类型 1：已付费 2：未付费
     */
    public static String studentTypeIntToString(int paidType,int type){
        String typeString = "";
        if(paidType ==1){
            switch (type){
                case 0:
                    typeString = "已付费";
                    break;
                case 1:
                    typeString = "月辅导";
                    break;
                case 2:
                    typeString = "季辅导";
                    break;
                case 3:
                    typeString = "年辅导";
                    break;
            }
        }else if(paidType==2){
            switch (type){
                case 0:
                    typeString = "未付费";
                    break;
                case 1:
                    typeString = "今日新增";
                    break;
                case 2:
                    typeString = "三日新增";
                    break;
                case 3:
                    typeString = "七日新增";
                    break;
            }
        }
        return typeString;
    }


    /**
     * 根据类型字符串转int
     * #1已付费 2月辅导 3季辅导 4年辅导 5未付费 6今日新增 7三日新增 8七日新增
     */
    public static int labelStringToInt(String label){
        int labelInt = 0;
        switch (label) {
            case "已付费":
                labelInt = 1;
                break;
            case "月辅导":
                labelInt = 2;
                break;
            case "季辅导":
                labelInt = 3;
                break;
            case "年辅导":
                labelInt = 4;
                break;
            case "未付费":
                labelInt = 5;
                break;
            case "今日新增":
                labelInt = 6;
                break;
            case "三日新增":
                labelInt = 7;
                break;
            case "七日新增":
                labelInt = 8;
                break;
        }
        return labelInt;
    }
}
