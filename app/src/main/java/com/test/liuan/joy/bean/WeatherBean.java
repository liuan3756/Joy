package com.test.liuan.joy.bean;

import com.google.gson.annotations.SerializedName;

public class WeatherBean {
	@SerializedName("status")
	public int statusCode;
	@SerializedName("data")
	public WeatherDataBean weatherDataBean;
	@SerializedName("message")
	public String message;
}
