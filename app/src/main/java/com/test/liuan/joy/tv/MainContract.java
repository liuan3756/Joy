package com.test.liuan.joy.tv;

public interface MainContract {
	interface View {
		void showDate(String date);
		
		void showWeatherInfo(String weatherInfo);
	}
	
	interface Presenter {
		void loadTime();
		
		void loadWeatherInfo();
	}
	
}
