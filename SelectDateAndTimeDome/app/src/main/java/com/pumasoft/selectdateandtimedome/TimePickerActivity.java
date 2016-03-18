package com.pumasoft.selectdateandtimedome;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;

/**
 * @author: adan
 * @description: 时间选择
 * @projectName: SelectDateAndTimeDome
 * @date: 2016-01-11
 * @time: 16:58
 */
public class TimePickerActivity extends Activity implements View.OnClickListener {
    private TimePicker timePicker;
    private Button mBtnGetTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timepicker);
        bindView();
    }

    private void bindView() {
        timePicker = (TimePicker) findViewById(R.id.timePicker);
        mBtnGetTime = (Button) findViewById(R.id.btn_gettime);

        // 是否使用24小时制
        timePicker.setIs24HourView(true);
        mBtnGetTime.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        intent.putExtra("hourOfDay", timePicker.getCurrentHour() + "");
        intent.putExtra("minute", timePicker.getCurrentMinute() + "");
        setResult(RESULT_OK, intent);
        finish();
    }
}
