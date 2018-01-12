package com.test.liuan.joy.bean;

import com.google.gson.annotations.SerializedName;

public class WeatherBean {
	@SerializedName("status")
	public int statusCode;
	@SerializedName("message")
	public String message;
	/** 天气信息 */
	@SerializedName("data")
	public WeatherDataBean weatherDataBean;
}
