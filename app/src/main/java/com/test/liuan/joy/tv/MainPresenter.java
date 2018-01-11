package com.test.liuan.joy.tv;

import com.test.liuan.joy.bean.WeatherBean;
import com.test.liuan.joy.net.NetWork;
import com.test.liuan.joy.net.TaskCallBack;

public class MainPresenter implements MainContract.Presenter {
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
				.loadWeatherInfo(new TaskCallBack<WeatherBean>() {
					@Override
					public void onSuccess(WeatherBean weatherBean) {
						view.showWeatherInfo(weatherBean.weatherDataBean.temperature);
					}
					
					@Override
					public void onError(Throwable throwable) {
					
					}
				});
		
	}
}
