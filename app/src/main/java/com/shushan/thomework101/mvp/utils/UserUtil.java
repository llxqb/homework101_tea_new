package com.shushan.thomework101.mvp.utils;

import android.text.TextUtils;

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
     * 年级json数组转字符串
     */
    public static String gradeArrayToString(String grade) {
        StringBuffer stringBuffer = new StringBuffer();
        if (grade.contains("1")) {
            if (!TextUtils.isEmpty(stringBuffer)) {
                stringBuffer.append("、一年级");
            } else {
                stringBuffer.append("一年级");
            }
        }
        if (grade.contains("2")) {
            if (!TextUtils.isEmpty(stringBuffer)) {
                stringBuffer.append("、二年级");
            } else {
                stringBuffer.append("二年级");
            }
        }
        if (grade.contains("3")) {
            if (!TextUtils.isEmpty(stringBuffer)) {
                stringBuffer.append("、三年级");
            } else {
                stringBuffer.append("三年级");
            }
        }
        if (grade.contains("4")) {
            if (!TextUtils.isEmpty(stringBuffer)) {
                stringBuffer.append("、四年级");
            } else {
                stringBuffer.append("四年级");
            }
        }
        if (grade.contains("5")) {
            if (!TextUtils.isEmpty(stringBuffer)) {
                stringBuffer.append("、五年级");
            } else {
                stringBuffer.append("五年级");
            }
        }
        if (grade.contains("6")) {
            if (!TextUtils.isEmpty(stringBuffer)) {
                stringBuffer.append("、六年级");
            } else {
                stringBuffer.append("六年级");
            }
        }
        if (grade.contains("7")) {
            if (!TextUtils.isEmpty(stringBuffer)) {
                stringBuffer.append("、初一");
            } else {
                stringBuffer.append("初一");
            }
        }
        if (grade.contains("8")) {
            if (!TextUtils.isEmpty(stringBuffer)) {
                stringBuffer.append("、初二");
            } else {
                stringBuffer.append("初二");
            }
        }
        if (grade.contains("9")) {
            if (!TextUtils.isEmpty(stringBuffer)) {
                stringBuffer.append("、初三");
            } else {
                stringBuffer.append("初三");
            }
        }
        return String.valueOf(stringBuffer);
    }

    /**
     * 年级int转换String
     */
    public static String gradeIntToString(int grade) {
        String gradeString = "";
        switch (grade) {
            case 1:
                gradeString = "一年级";
                break;
            case 2:
                gradeString = "二年级";
                break;
            case 3:
                gradeString = "三年级";
                break;
            case 4:
                gradeString = "四年级";
                break;
            case 5:
                gradeString = "五年级";
                break;
            case 6:
                gradeString = "六年级";
                break;
            case 7:
                gradeString = "初一";
                break;
            case 8:
                gradeString = "初二";
                break;
            case 9:
                gradeString = "初三";
                break;

        }
        return gradeString;
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

    /**
     * 天数字转字符串
     * 1->周一
     */
    public static String dayArrayToString(String day) {
        if (day == null) return "";
        StringBuffer stringBuffer = new StringBuffer();
        if (day.contains("1,2,3,4,5")) {
            return "周一 至 周五";
        }
        if (day.contains("1")) {
            stringBuffer.append("周一 ");
        }
        if (day.contains("2")) {
            stringBuffer.append("周二 ");
        }
        if (day.contains("3")) {
            stringBuffer.append("周三 ");
        }
        if (day.contains("4")) {
            stringBuffer.append("周四 ");
        }
        if (day.contains("5")) {
            stringBuffer.append("周五 ");
        }
        if (day.contains("6")) {
            stringBuffer.append("周六 ");
        }
        if (day.contains("7")) {
            stringBuffer.append("周日 ");
        }
        return String.valueOf(stringBuffer);
    }

//    /**
//     * 学生名字转某某老师
//     */
//    public static String toTeacherName(String name) {
//        String teacherName = "";
//        if (!TextUtils.isEmpty(name)) {
//            if (name.contains("老师")) {
//                teacherName = name;
//            } else {
////                teacherName = name.substring(0, 1) + "老师";
//                teacherName = name + "老师";
//            }
//        }
//        return teacherName;
//    }


    /**
     * @return studentTypeInt: 0全部 1免费体验学生 2月辅导 3季辅导 4年辅导  不传默认全部
     */
    public static int studentTypeToInt(String studentType) {
        int studentTypeInt = 0;
        switch (studentType) {
            case "全部":
                studentTypeInt = 0;
                break;
            case "免费体验学生":
                studentTypeInt = 1;
                break;
            case "月辅导学生":
                studentTypeInt = 2;
                break;
            case "季辅导学生":
                studentTypeInt = 3;
                break;
            case "年辅导学生":
                studentTypeInt = 4;
                break;
        }
        return studentTypeInt;
    }


}
