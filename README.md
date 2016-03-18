# DatePicker与TimePicker的使用
# Android开发中经常会使用到日期选择器与时间选择器，接下来我就来总结一下

效果图：
![](http://img.blog.csdn.net/20160111214712608?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQv/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/Center)

# 先贴一下项目结构图：
![](http://img.blog.csdn.net/20160111172134686?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQv/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/Center)

# MainActivity.class
```Java
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

```

# activity_main.xml
```Java
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:orientation="vertical"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <RelativeLayout
        android:layout_width="match_parent"
        android:layout_height="@dimen/activity_tabhost_height"
        android:background="@color/app_color">

        <TextView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_centerInParent="true"
            android:layout_centerVertical="true"
            android:text="选择日期与时间"
            android:textColor="@color/white"
            android:textSize="16dp" />
    </RelativeLayout>
    <!-- 选择日期 -->

    <RelativeLayout
        android:id="@+id/rl_select_date"
        android:layout_width="match_parent"
        android:layout_height="56dp"
        android:background="@drawable/selector_ontouch">

        <TextView
            android:id="@+id/select_date"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_centerVertical="true"
            android:layout_marginLeft="@dimen/acitvity_margin"
            android:text="选择日期"
            android:textColor="@color/app_color"
            android:textSize="@dimen/size_text_medium" />

        <TextView
            android:id="@+id/tv_select_date"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_centerVertical="true"
            android:layout_marginLeft="10dp"
            android:layout_toRightOf="@+id/select_date"
            android:textSize="@dimen/size_text_medium" />

        <ImageView
            android:layout_width="wrap_content"
            android:layout_height="15dp"
            android:layout_alignParentRight="true"
            android:layout_centerVertical="true"
            android:layout_marginRight="@dimen/acitvity_margin"
            android:src="@mipmap/icon_rirht" />
    </RelativeLayout>

    <View style="@style/horizontalDivider" />
    <!-- 选择时间 -->

    <RelativeLayout
        android:id="@+id/rl_select_time"
        android:layout_width="match_parent"
        android:layout_height="56dp"
        android:background="@drawable/selector_ontouch">

        <TextView
            android:id="@+id/select_time"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_centerVertical="true"
            android:layout_marginLeft="@dimen/acitvity_margin"
            android:text="选择时间"
            android:textColor="@color/app_color"
            android:textSize="@dimen/size_text_medium" />

        <TextView
            android:id="@+id/tv_select_time"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_centerVertical="true"
            android:layout_marginLeft="10dp"
            android:layout_toRightOf="@+id/select_time"
            android:textSize="@dimen/size_text_medium" />

        <ImageView
            android:layout_width="wrap_content"
            android:layout_height="15dp"
            android:layout_alignParentRight="true"
            android:layout_centerVertical="true"
            android:layout_marginRight="@dimen/acitvity_margin"
            android:src="@mipmap/icon_rirht" />
    </RelativeLayout>

    <View style="@style/horizontalDivider" />
</LinearLayout>
```

# selector_ontouch.xml
```Java
<?xml version="1.0" encoding="utf-8"?>
<selector xmlns:android="http://schemas.android.com/apk/res/android">
    <item android:drawable="@color/gray" android:state_pressed="true" />
    <item android:drawable="@color/white" android:state_pressed="false" />
</selector>
```

# DatePickerActivity.class
```Java
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

```

# activity_datepicker.xml.class
```Java
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:background="@color/white"
    android:orientation="vertical">

    <DatePicker
        android:id="@+id/dpPicker"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:background="@color/app_color"
        android:calendarViewShown="false" />

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="55dp"
        android:gravity="center"
        android:orientation="horizontal"
        android:padding="10dp">

        <Button
            android:id="@+id/btn_dismiss"
            android:layout_width="0dp"
            android:layout_height="35dp"
            android:layout_marginRight="5dp"
            android:layout_weight="1"
            android:background="@drawable/selector_border_ontouch"
            android:text="取消"
            android:textColor="@drawable/selector_text_grey"
            android:textSize="@dimen/size_text_big" />

        <Button
            android:id="@+id/btn_ok"
            android:layout_width="0dp"
            android:layout_height="35dp"
            android:layout_marginLeft="5dp"
            android:layout_weight="1"
            android:background="@drawable/selector_slipe_blue"
            android:text="确定"
            android:textColor="@color/white"
            android:textSize="@dimen/size_text_big" />
    </LinearLayout>

</LinearLayout>
```

# TimePickerActivity.class
```Java
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

```

# activity_timepicker.xml
```Java
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:background="@color/white"
    android:gravity="center_horizontal"
    android:orientation="vertical">

    <TimePicker
        android:id="@+id/timePicker"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:background="@color/app_color" />

    <Button
        android:id="@+id/btn_gettime"
        android:layout_width="75dp"
        android:layout_height="35dp"
        android:layout_marginBottom="10dp"
        android:layout_marginTop="10dp"
        android:background="@drawable/selector_slipe_blue"
        android:text="确定"
        android:textColor="@color/white" />

</LinearLayout>
```

# selector_border_ontouch.xml
```Java
<?xml version="1.0" encoding="utf-8"?>
<selector xmlns:android="http://schemas.android.com/apk/res/android">

    <item android:state_pressed="false">
        <shape android:shape="rectangle">
            <corners android:radius="3dp" />
            <solid android:color="@color/white" />
            <stroke android:width="1dp" android:color="@color/gray" />
        </shape>
    </item>
    <item android:state_pressed="true">
        <shape android:shape="rectangle">

            <corners android:radius="3dp" />
            <solid android:color="@color/white" />
            <stroke android:width="1dp" android:color="@color/app_color" />
        </shape>
    </item>

</selector>
```

# selector_slipe_blue.xml
```Java
<?xml version="1.0" encoding="utf-8"?>
<selector xmlns:android="http://schemas.android.com/apk/res/android">

    <item android:state_pressed="false"><shape android:shape="rectangle">

            <!-- solid表示远的填充色 -->
            <solid android:color="@color/app_color" />
            <!-- stroke则代表远的边框线 -->
            <stroke android:width="1dp" android:color="@color/app_color" />
            <!-- size控制高宽 -->
            <size android:height="20dp" android:width="20dp" />
        </shape></item>
    <item android:state_pressed="true"><shape android:shape="rectangle">

            <!-- solid表示远的填充色 -->
            <solid android:color="#97D1EC" />
            <!-- stroke则代表远的边框线 -->
            <stroke android:width="1dp" android:color="#97D1EC" />
            <!-- size控制高宽 -->
            <size android:height="20dp" android:width="20dp" />
        </shape></item>

</selector>
```

# selector_text_grey.xml
```Java
<?xml version="1.0" encoding="utf-8"?>
<selector xmlns:android="http://schemas.android.com/apk/res/android">

    <item android:color="@color/app_color" android:state_selected="true" />
    <item android:color="@color/app_color" android:state_focused="true" />
    <item android:color="@color/app_color" android:state_pressed="true" />
    <item android:color="@color/app_color" android:state_checked="true" />
    <item android:color="@color/gray" />

</selector>
```




