package com.pumasoft.selectdateandtimedome;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * @author: adan
 * @description: 日期选择
 * @projectName: SelectDateAndTimeDome
 * @date: 2016-01-11
 * @time: 16:45
 */
public class DatePickerActivity extends Activity implements View.OnClickListener {
    private DatePicker datePicker;
    private Button mBtnDismiss, mBtnOk;
    private Calendar calendar;
    private SimpleDateFormat format;
    private String date;
    private Calendar c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datepicker);
        bindView();
    }

    private void bindView() {
        format = new SimpleDateFormat("yyyy-MM-dd");
        datePicker = (DatePicker) findViewById(R.id.dpPicker);
        mBtnDismiss = (Button) findViewById(R.id.btn_dismiss);
        mBtnOk = (Button) findViewById(R.id.btn_ok);

        c = Calendar.getInstance();
        date = c.get(Calendar.YEAR) + "-" + (c.get(Calendar.MONTH) + 1) + "-"
                + c.get(Calendar.DAY_OF_MONTH); // 获取当前日期
        init();

        mBtnDismiss.setOnClickListener(this);
        mBtnOk.setOnClickListener(this);
    }

    private void init() {
        datePicker.init(c.get(Calendar.YEAR), c.get(Calendar.MONTH) + 1,
                c.get(Calendar.DAY_OF_MONTH), new DatePicker.OnDateChangedListener() {

                    @Override
                    public void onDateChanged(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {
                        // 获取一个日历对象，并初始化为当前选中的时间
                        calendar = Calendar.getInstance();
                        calendar.set(year, monthOfYear, dayOfMonth);
                        date = format.format(calendar.getTime());
                    }
                });
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btn_dismiss:
                finish();
                break;
            case R.id.btn_ok:
                Intent intent = new Intent();
                intent.putExtra("date", date);
                setResult(RESULT_OK, intent);
                finish();
                break;
            default:
                break;
        }

    }
}
