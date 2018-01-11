package com.test.liuan.joy.net;

import com.test.liuan.joy.bean.WeatherBean;

import retrofit2.Call;
import retrofit2.http.GET;

public interface TVService {
	@GET("json.shtml?city=北京")
	Call<WeatherBean> login();
}
