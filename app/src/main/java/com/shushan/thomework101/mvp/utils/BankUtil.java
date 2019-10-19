package com.shushan.thomework101.mvp.utils;

import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.shushan.thomework101.R;
import com.shushan.thomework101.entity.constants.Constant;

/**
 * 银行util
 */
public class BankUtil {
    public static void labelToBankIcon(String label, ImageView mBankIconIv, RelativeLayout mBankLayout) {
        int bankIcon = 0;
        if (TextUtils.isEmpty(label)) return;
        label = label.toUpperCase();//默认小写转大写
        switch (label) {
            case Constant.LABEL_ABC:
                mBankIconIv.setImageResource(R.mipmap.abc);
                mBankLayout.setBackgroundResource(R.mipmap.abc2);
                break;
            case Constant.LABEL_BOC:
                mBankIconIv.setImageResource(R.mipmap.boc);
                mBankLayout.setBackgroundResource(R.mipmap.boc2);
                break;
            case Constant.LABEL_BOHAIB:
                mBankIconIv.setImageResource(R.mipmap.bohaib);
                mBankLayout.setBackgroundResource(R.mipmap.bohaib2);
                break;
            case Constant.LABEL_CCB:
                mBankIconIv.setImageResource(R.mipmap.ccb);
                mBankLayout.setBackgroundResource(R.mipmap.ccb2);
                break;
            case Constant.LABEL_CEB:
                mBankIconIv.setImageResource(R.mipmap.ceb);
                mBankLayout.setBackgroundResource(R.mipmap.ceb2);
                break;
            case Constant.LABEL_CIB:
                mBankIconIv.setImageResource(R.mipmap.cib);
                mBankLayout.setBackgroundResource(R.mipmap.cib2);
                break;
            case Constant.LABEL_CITIC:
                mBankIconIv.setImageResource(R.mipmap.citic);
                mBankLayout.setBackgroundResource(R.mipmap.citic2);
                break;
            case Constant.LABEL_CMB:
                mBankIconIv.setImageResource(R.mipmap.cmb);
                mBankLayout.setBackgroundResource(R.mipmap.cmb2);
                break;
            case Constant.LABEL_CMBC:
                mBankIconIv.setImageResource(R.mipmap.cmbc);
                mBankLayout.setBackgroundResource(R.mipmap.cmbc2);
                break;
            case Constant.LABEL_COMM:
                mBankIconIv.setImageResource(R.mipmap.comm);
                mBankLayout.setBackgroundResource(R.mipmap.comm2);
                break;
            case Constant.LABEL_CZBANK:
                mBankIconIv.setImageResource(R.mipmap.czbank);
                mBankLayout.setBackgroundResource(R.mipmap.czbank2);
                break;
            case Constant.LABEL_EGBANK:
                mBankIconIv.setImageResource(R.mipmap.egbank);
                mBankLayout.setBackgroundResource(R.mipmap.egbank2);
                break;
            case Constant.LABEL_HXBANK:
                mBankIconIv.setImageResource(R.mipmap.hxbank);
                mBankLayout.setBackgroundResource(R.mipmap.hxbank2);
                break;
            case Constant.LABEL_ICBC:
                mBankIconIv.setImageResource(R.mipmap.icbc);
                mBankLayout.setBackgroundResource(R.mipmap.icbc2);
                break;
            case Constant.LABEL_PSBC:
                mBankIconIv.setImageResource(R.mipmap.psbc);
                mBankLayout.setBackgroundResource(R.mipmap.psbc2);
                break;

        }
    }


    /**
     * 隐藏 银行卡卡号前面数字
     */
    public static String getBankHidedNum(String cardNum) {
        StringBuilder stringBuffer = new StringBuilder();
        char[] cardNumArr = cardNum.toCharArray();
        for (int i = 0; i < cardNumArr.length; i++) {
            if (i < cardNumArr.length - 4) {
                stringBuffer.append('*');
                if (i > 0 && (i + 1) % 4 == 0) {
                    stringBuffer.append(' ');
                }
            } else if (i == cardNumArr.length - 4) {
                stringBuffer.append(cardNumArr[i]);
                stringBuffer.append(' ');
            } else {
                stringBuffer.append(cardNumArr[i]);
            }
        }

        return stringBuffer.toString();
    }


    /**
     * 根据银行名称查找label信息
     */
    public static String selectLabelByBankName(String bankName) {
        String labelValue = "";
        switch (bankName) {
            case Constant.LABEL_ABC_NAME:
                labelValue = Constant.LABEL_ABC;
                break;
            case Constant.LABEL_BOC_NAME:
                labelValue = Constant.LABEL_BOC;
                break;
            case Constant.LABEL_BOHAIB_NAME:
                labelValue = Constant.LABEL_BOHAIB;
                break;
            case Constant.LABEL_CCB_NAME:
                labelValue = Constant.LABEL_CCB;
                break;
            case Constant.LABEL_CEB_NAME:
                labelValue = Constant.LABEL_CEB;
                break;
            case Constant.LABEL_CIB_NAME:
                labelValue = Constant.LABEL_CIB;
                break;
            case Constant.LABEL_CITIC_NAME:
                labelValue = Constant.LABEL_CITIC;
                break;
            case Constant.LABEL_CMB_NAME:
                labelValue = Constant.LABEL_CMB;
                break;
            case Constant.LABEL_CMBC_NAME:
                labelValue = Constant.LABEL_CMBC;
                break;
            case Constant.LABEL_COMM_NAME:
                labelValue = Constant.LABEL_COMM;
                break;
            case Constant.LABEL_CZBANK_NAME:
                labelValue = Constant.LABEL_CZBANK;
                break;
            case Constant.LABEL_EGBANK_NAME:
                labelValue = Constant.LABEL_EGBANK;
                break;
            case Constant.LABEL_HXBANK_NAME:
                labelValue = Constant.LABEL_HXBANK;
                break;
            case Constant.LABEL_ICBC_NAME:
                labelValue = Constant.LABEL_ICBC;
                break;
            case Constant.LABEL_PSBC_NAME:
                labelValue = Constant.LABEL_PSBC;
                break;
        }

        return labelValue;
    }
}
