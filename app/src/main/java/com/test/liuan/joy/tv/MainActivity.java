package com.test.liuan.joy.tv;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
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
    private MainPresenter mainPresenter;
    @BindView(R.id.llBackGround)
    LinearLayout llBackGround;
    @BindView(R.id.tvDate)
    TextView tvDate;
    @BindView(R.id.tvTime)
    TextView tvTime;
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
        this.mainPresenter.loadTime();
        this.mainPresenter.loadBackGroundColor();
    }

    private void initView() {
        this.recyclerViewForecast.setLayoutManager(new LinearLayoutManager(this));
        this.futureWeatherListAdapter = new FutureWeatherListAdapter(this, this.forecastBeans);
        this.recyclerViewForecast.setAdapter(this.futureWeatherListAdapter);
    }

    @Override
    public void showRGB(int red, int green, int blue) {
        this.llBackGround.setBackgroundColor(Color.rgb(red, green, blue));
    }

    @Override
    public void showDateAndTime(String date, String time) {
        if (!this.tvDate.getText().toString().equals(date)) {
            this.tvDate.setText(date);
        }
        this.tvTime.setText(time);
    }

    @Override
    public void loadWeatherInfoSuccess(WeatherDataBean weatherDataBean) {
        this.tvTemp.setText(
                String.format(
                        getString(R.string.format_current_temp), weatherDataBean.nowTemperature));

        this.forecastBeans.clear();
        this.forecastBeans.addAll(weatherDataBean.getFutureWeatherInfoList());
        this.futureWeatherListAdapter.notifyDataSetChanged();
    }

    @Override
    public void loadWeatherInfoError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.mainPresenter.finishLoad();
    }
}
