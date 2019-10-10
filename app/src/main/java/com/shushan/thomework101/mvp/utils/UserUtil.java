package com.shushan.thomework101.mvp.utils;

/**
 * 用户util
 */
public class UserUtil {
    /**
     * 根据选择的科目 判断是否是初中科目
     *
     * @param subject 科目  true 1-3 全部课程   false 4-9 初中课程
     */
    public static boolean isJuniorHighSchoolSubject(int subject) {
        return subject == 1 || subject == 2 || subject == 3;
    }


    /**
     * 科目int转换String
     */
    public static String subjectIntToString(int subject) {
        String subjectString = "";
        switch (subject) {
            case 1:
                subjectString = "语文";
                break;
            case 2:
                subjectString = "数学";
                break;
            case 3:
                subjectString = "英语";
                break;
            case 4:
                subjectString = "物理";
                break;
            case 5:
                subjectString = "化学";
                break;
            case 6:
                subjectString = "生物";
                break;
            case 7:
                subjectString = "历史";
                break;
            case 8:
                subjectString = "地理";
                break;
            case 9:
                subjectString = "政治";
                break;

        }
        return subjectString;
    }

    /**
     * 科目String转换int
     */
    public static int subjectStringToInt(String subject) {
        int subjectInt = 0;
        switch (subject) {
            case "语文":
                subjectInt = 1;
                break;
            case "数学":
                subjectInt = 2;
                break;
            case "英语":
                subjectInt = 3;
                break;
            case "物理":
                subjectInt = 4;
                break;
            case "化学":
                subjectInt = 5;
                break;
            case "生物":
                subjectInt = 6;
                break;
            case "历史":
                subjectInt = 7;
                break;
            case "地理":
                subjectInt = 8;
                break;
            case "政治":
                subjectInt = 9;
                break;

        }
        return subjectInt;
    }

}
