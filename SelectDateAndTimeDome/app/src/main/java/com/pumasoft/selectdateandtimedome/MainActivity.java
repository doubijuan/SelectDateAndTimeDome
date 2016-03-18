package com.pumasoft.selectdateandtimedome;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity implements View.OnClickListener {
    private RelativeLayout mRlSelectDate, mRlSelectTime;
    private TextView mTvSelectDate, mTvSelectTime;
    private Context mContext;
    private static int SELECTDATE = 0, SELECTTIME = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        bindView();
    }

    private void bindView() {
        mRlSelectDate = (RelativeLayout) findViewById(R.id.rl_select_date);
        mRlSelectTime = (RelativeLayout) findViewById(R.id.rl_select_time);

        mTvSelectDate = (TextView) findViewById(R.id.tv_select_date);
        mTvSelectTime = (TextView) findViewById(R.id.tv_select_time);

        mRlSelectDate.setOnClickListener(this);
        mRlSelectTime.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.rl_select_date:
                startActivityForResult(new Intent(mContext,
                        DatePickerActivity.class), SELECTDATE);
                break;
            case R.id.rl_select_time:
                if (TextUtils.isEmpty(mTvSelectDate.getText().toString().trim())) {
                    Toast.makeText(mContext, "请先选择请假开始日期", Toast.LENGTH_LONG);
                } else {
                    startActivityForResult(new Intent(mContext,
                            TimePickerActivity.class), SELECTTIME);
                }
                break;
            default:
                break;
        }
    }

    /**
     * 方法名: onActivityResult
     * <p/>
     * 方法描述: 回传数据
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == MainActivity.SELECTDATE
                && resultCode == RESULT_OK) {// 选择日期
            String startDate = data.getStringExtra("date");
            mTvSelectDate.setText(startDate);
        } else if (requestCode == MainActivity.SELECTTIME
                && resultCode == RESULT_OK) {// 选择时间
            String leaveStartTime = data.getStringExtra("hourOfDay") + ":"
                    + data.getStringExtra("minute");
            mTvSelectTime.setText(leaveStartTime);
        }
    }
}
