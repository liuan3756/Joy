package com.test.liuan.joy.tv;

import com.test.liuan.joy.bean.WeatherBean;
import com.test.liuan.joy.bean.WeatherForecastBean;
import com.test.liuan.joy.net.NetWork;
import com.test.liuan.joy.net.TaskCallBack;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import rx.Observable;
import rx.functions.Func1;

public class MainPresenter implements MainContract.Presenter {
	private static final String CITY_NAME = "厦门";
	private MainContract.View view;
	
	MainPresenter(MainContract.View view) {
		this.view = view;
	}
	
	@Override
	public void loadTime() {
		this.view.showDate("");
	}
	
	@Override
	public void loadWeatherInfo() {
		NetWork.getInstance()
				.loadWeatherInfo(CITY_NAME, new TaskCallBack<WeatherBean>() {
					@Override
					public void onSuccess(WeatherBean weatherBean) {
						view.loadWeatherInfoSuccess(weatherBean.weatherDataBean);
					}
					
					@Override
					public void onError(Throwable throwable) {
						view.loadWeatherInfoError(throwable);
					}
				});
		
	}
}
