package com.test.liuan.joy.bean;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class WeatherDataBean {
	/*此刻天气*/
	/** 此刻湿度 */
	@SerializedName("shidu")
	public String NowAirHumidity;
	/** 此刻温度 */
	@SerializedName("wendu")
	public String nowTemperature;
	/** 此刻空气质量 */
	@SerializedName("quality")
	public String nowAirQuality;
	/** 此刻PM25 */
	@SerializedName("pm25")
	public int nowPM25;
	/** 此刻PM10 */
	@SerializedName("pm10")
	public int nowPM10;
	
	/** 昨日天气 */
	@SerializedName("yesterday")
	public WeatherForecastBean weatherForecastBean;
	/** 预报天气 */
	@SerializedName("forecast")
	private ArrayList<WeatherForecastBean> forecastBeanList;
	
	private WeatherForecastBean todayWeatherInfo;
	private ArrayList<WeatherForecastBean> futureWeatherInfoList = new ArrayList<>();
	
	public WeatherForecastBean getTodayWeatherInfo() {
		if (this.todayWeatherInfo == null && this.forecastBeanList != null && this.forecastBeanList.size() != 0) {
			todayWeatherInfo = this.forecastBeanList.get(0);
		}
		return this.todayWeatherInfo;
	}
	
	public ArrayList<WeatherForecastBean> getFutureWeatherInfoList() {
		if (this.futureWeatherInfoList.size() == 0 && this.forecastBeanList != null) {
			for (int i = 0; i < forecastBeanList.size(); i++) {
				if (i != 0) {
					this.futureWeatherInfoList.add(this.forecastBeanList.get(i));
				}
			}
		}
		return this.futureWeatherInfoList;
	}
}
