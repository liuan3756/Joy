package com.test.liuan.joy.tv;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import com.test.liuan.joy.R;
import com.test.liuan.joy.bean.WeatherBean;
import com.test.liuan.joy.bean.WeatherDataBean;
import com.test.liuan.joy.bean.WeatherForecastBean;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends Activity implements MainContract.View {
	private static final String CITY_NAME = "厦门";
	private MainPresenter mainPresenter;
	@BindView(R.id.tvTemp)
	TextView tvTemp;
	@BindView(R.id.recyclerViewForecast)
	RecyclerView recyclerViewForecast;
	private ArrayList<WeatherForecastBean> forecastBeans = new ArrayList<>();
	private FutureWeatherListAdapter futureWeatherListAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ButterKnife.bind(this);
		initView();
		
		this.mainPresenter = new MainPresenter(this);
		
		this.mainPresenter.loadWeatherInfo();
	}
	
	private void initView() {
		this.recyclerViewForecast.setLayoutManager(new LinearLayoutManager(this));
		this.futureWeatherListAdapter = new FutureWeatherListAdapter(this, this.forecastBeans);
		this.recyclerViewForecast.setAdapter(this.futureWeatherListAdapter);
	}
	
	@Override
	public void showDate(String date) {
	
	}
	
	@Override
	public void loadWeatherInfoSuccess(WeatherDataBean weatherDataBean) {
		this.tvTemp.setText(weatherDataBean.nowTemperature);
		
		this.forecastBeans.clear();
		this.forecastBeans.addAll(weatherDataBean.getFutureWeatherInfoList());
		this.futureWeatherListAdapter.notifyDataSetChanged();
	}
	
	@Override
	public void loadWeatherInfoError(String message) {
		Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
	}
	
}
