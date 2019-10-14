package com.shushan.thomework101.mvp.ui.activity.mine;


import com.shushan.thomework101.entity.request.ExpectedIncomeRequest;
import com.shushan.thomework101.entity.request.RevenueIncomeRequest;
import com.shushan.thomework101.entity.response.ExpectedIncomeResponse;
import com.shushan.thomework101.entity.response.RevenueIncomeResponse;
import com.shushan.thomework101.mvp.presenter.LoadDataView;
import com.shushan.thomework101.mvp.presenter.Presenter;

/**
 * Created by li.liu on 2019/09/17.
 */

public class ExpectedIncomeControl {
    public interface ExpectedIncomeView extends LoadDataView {
        void getExpectedIncomeSuccess(ExpectedIncomeResponse expectedIncomeResponse);

        void getRevenueIncomeSuccess(RevenueIncomeResponse revenueIncomeResponse);
    }

    public interface PresenterExpectedIncome extends Presenter<ExpectedIncomeView> {

        /**
         * 预计收益
         */
        void onRequestExpectedIncome(ExpectedIncomeRequest expectedIncomeRequest);

        /**
         * 已到手金额明细
         */
        void onRequestRevenueIncome(RevenueIncomeRequest revenueIncomeRequest);
    }

}
