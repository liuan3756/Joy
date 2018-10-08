package com.test.liuan.joy.tv;

import com.test.liuan.joy.bean.WeatherDataBean;

public interface MainContract {
	interface View {
		
		void showRGB(int red, int green, int blue);
		
		void showDateAndTime(String date, String time);
		
		void loadWeatherInfoSuccess(WeatherDataBean weatherDataBean);
		
		void loadWeatherInfoError(String message);
	}
	
	interface Presenter {
		
		void loadBackGroundColor();
		void loadTime();
		
		void loadWeatherInfo();
		void finishLoad();
	}
	
}
