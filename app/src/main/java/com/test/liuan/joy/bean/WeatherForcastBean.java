package com.test.liuan.joy.bean;

import com.google.gson.annotations.SerializedName;

public class WeatherForcastBean {
	/** 风向 */
	@SerializedName("fengxiang")
	public String windDirect;
	/** 风力 */
	@SerializedName("fengli")
	public String windLevel;
	/** 最高温度 */
	@SerializedName("high")
	public String highTemp;
	/** 天气类型 */
	@SerializedName("type")
	public String weatherTypeName;
	/** 最低温度 */
	@SerializedName("low")
	public String lowTemp;
	/** 日期 */
	@SerializedName("date")
	public String date;
}
