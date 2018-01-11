package com.test.liuan.joy.widget;

import android.content.Context;
import android.os.Handler;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class ClockView extends TextView {
	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss", Locale.CHINA);
	
	public ClockView(Context context) {
		super(context);
		init();
	}
	
	public ClockView(Context context, @Nullable AttributeSet attrs) {
		super(context, attrs);
		init();
	}
	
	public ClockView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init();
	}
	
	private void init() {
		final Handler handler = new Handler();
		handler.post(new Runnable() {
			@Override
			public void run() {
				String time = simpleDateFormat.format(new Date(System.currentTimeMillis()));
				setText(time);
				handler.postDelayed(this, 998);
			}
		});
	}
}
