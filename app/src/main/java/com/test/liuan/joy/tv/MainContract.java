package com.test.liuan.joy.tv;

import com.test.liuan.joy.bean.WeatherDataBean;

public interface MainContract {
	interface View {
		void showDate(String date);
		
		void loadWeatherInfoSuccess(WeatherDataBean weatherDataBean);
		
		void loadWeatherInfoError(String message);
	}
	
	interface Presenter {
		void loadTime();
		
		void loadWeatherInfo();
	}
	
}
