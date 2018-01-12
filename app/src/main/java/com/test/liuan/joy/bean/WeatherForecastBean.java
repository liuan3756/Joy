package com.test.liuan.joy.bean;

import com.google.gson.annotations.SerializedName;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WeatherForecastBean {
	/** 日期 */
	@SerializedName("date")
	public String date;
	/** 日出时间 */
	@SerializedName("sunrise")
	public String sunriseTime;
	/** 日落时间 */
	@SerializedName("sunset")
	public String sunsetTime;
	/** 最高温度 */
	@SerializedName("high")
	public String highTemp;
	/** 最低温度 */
	@SerializedName("low")
	public String lowTemp;
	/** 空气指数 */
	@SerializedName("aqi")
	public int aqi;
	/** 风向 */
	@SerializedName("fx")
	public String windDirect;
	/** 风力 */
	@SerializedName("fl")
	public String windLevel;
	/** 天气类型 */
	@SerializedName("type")
	public String weatherTypeName;
	/** 天气类型提示 */
	@SerializedName("notice")
	public String weatherTypeNotice;
	
	public String getTempDescribe() {
		String result = "";
		Matcher m;
		Pattern p = Pattern.compile("([1-9]\\d*\\.?\\d*)|(0\\.\\d*[1-9])");
		m = p.matcher(this.lowTemp);
		if (m.find()) {
			result += m.group();
		}
		m = p.matcher(this.highTemp);
		if (m.find()) {
			result += "~" + m.group() + "°C";
		}
		return result;
	}
}
