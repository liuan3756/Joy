package com.test.liuan.joy.bean;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class WeatherDataBean {
	@SerializedName("wendu")
	public String temperature;
	@SerializedName("forecast")
	public ArrayList<WeatherForcastBean> forcastBeanList;
}
