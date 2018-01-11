package com.test.liuan.joy.tv;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.test.liuan.joy.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends Activity implements MainContract.View {
	private MainPresenter mainPresenter;
	@BindView(R.id.tvTemp)
	TextView tvTemp;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ButterKnife.bind(this);
		this.mainPresenter = new MainPresenter(this);
		this.mainPresenter.loadWeatherInfo();
	}
	
	@Override
	public void showDate(String date) {
	
	}
	
	@Override
	public void showWeatherInfo(String weatherInfo) {
		this.tvTemp.setText(weatherInfo);
	}
}
