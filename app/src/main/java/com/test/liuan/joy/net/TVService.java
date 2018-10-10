package com.test.liuan.joy.net;

import com.test.liuan.joy.bean.WeatherBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface TVService {
    @GET("city/{cityCode}")
    Call<WeatherBean> getWeather(@Path("cityCode") String cityCode);
}
