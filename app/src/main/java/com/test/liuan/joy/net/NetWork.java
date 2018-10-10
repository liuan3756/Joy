package com.test.liuan.joy.net;

import com.test.liuan.joy.bean.WeatherBean;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetWork {
    private static NetWork netWork;

    private NetWork() {

    }

    public static NetWork getInstance() {
        if (netWork == null) {
            synchronized (NetWork.class) {
                if (netWork == null) {
                    netWork = new NetWork();
                }
            }
        }
        return netWork;
    }

    public void loadWeatherInfo(String cityName, final TaskCallBack<WeatherBean> taskCallBack) {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://t.weather.sojson.com/api/weather/")
                .build();
        TVService service = retrofit.create(TVService.class);
        Call<WeatherBean> call = service.getWeather(cityName);
        System.out.println("requestUrl = " + call.request().url().toString());
        call.enqueue(new Callback<WeatherBean>() {
            @Override
            public void onResponse(Call<WeatherBean> call, Response<WeatherBean> response) {
                if (response != null && response.body() != null) {
                    taskCallBack.onSuccess(response.body());
                }
            }

            @Override
            public void onFailure(Call<WeatherBean> call, Throwable t) {
                t.printStackTrace();
                taskCallBack.onError(t);
            }
        });
    }
}
