package com.test.liuan.joy.tv;

import com.test.liuan.joy.bean.WeatherBean;
import com.test.liuan.joy.bean.WeatherDataBean;
import com.test.liuan.joy.net.NetWork;
import com.test.liuan.joy.net.TaskCallBack;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

public class MainPresenter implements MainContract.Presenter {
	private static final String CITY_NAME = "厦门";
	/** 修改目标颜色间隔时间 */
	private static final int CHANGE_TARGET_COLOR_MILLIS = 5000;
	/** 刷新界面间隔时间 */
	private static final int VALIDATE_COLOR_MILLIS = 40;
	private static final float DIVISOR = (float) CHANGE_TARGET_COLOR_MILLIS / (float) VALIDATE_COLOR_MILLIS;
	private MainContract.View view;

	private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 E&HH:mm:ss", Locale.CHINA);

	private float currentRed = 128;
	private float currentGreen = 128;
	private float currentBlue = 128;

	private int targetRed = 0;
	private int targetGreen = 0;
	private int targetBlue = 0;

	private float stepRed = 0;
	private float stepGreen = 0;
	private float stepBlue = 0;

	private Random random;

	private Observable observable;

	MainPresenter(final MainContract.View view) {
		this.view = view;
	}

	@Override
	public void loadBackGroundColor() {
		if (random == null) {
			random = new Random();
		}
		Observable.interval(CHANGE_TARGET_COLOR_MILLIS, TimeUnit.MILLISECONDS)
				.observeOn(AndroidSchedulers.mainThread())
				.subscribeOn(AndroidSchedulers.mainThread())
				.subscribe(new Action1<Long>() {
					@Override
					public void call(Long aLong) {
						targetRed = random.nextInt(255);
						targetGreen = random.nextInt(255);
						targetBlue = random.nextInt(255);
						System.out.println("targetRed " + targetRed + " targetGreen " + targetGreen + " targetBlue " + targetBlue);

						stepRed = (targetRed - currentRed) / DIVISOR;
						stepGreen = (targetGreen - currentGreen) / DIVISOR;
						stepBlue = (targetBlue - currentBlue) / DIVISOR;
						System.out.println("stepRed " + stepRed + " stepGreen " + stepGreen + " stepBlue " + stepBlue);
					}
				});

		Observable.interval(VALIDATE_COLOR_MILLIS, TimeUnit.MILLISECONDS)
				.observeOn(AndroidSchedulers.mainThread())
				.subscribeOn(AndroidSchedulers.mainThread())
				.subscribe(new Action1<Long>() {
					@Override
					public void call(Long aLong) {
						currentRed += stepRed;
						currentGreen += stepGreen;
						currentBlue += stepBlue;
						view.showRGB((int) currentRed, (int) currentGreen, (int) currentBlue);
						System.out.println("currentRed " + currentRed + " currentGreen " + currentGreen + " currentBlue " + currentBlue);
					}
				});
	}

	@Override
	public void loadTime() {
		Observable.interval(1, TimeUnit.SECONDS)
				.observeOn(AndroidSchedulers.mainThread())
				.subscribeOn(AndroidSchedulers.mainThread())
				.subscribe(new Action1<Long>() {
					@Override
					public void call(Long aLong) {
						String date = simpleDateFormat.format(new Date(System.currentTimeMillis()));
						String[] dataAndTime = date.split("&");
						view.showDateAndTime(dataAndTime[0], dataAndTime[1]);
					}
				});
	}

	@Override
	public void loadWeatherInfo() {
		NetWork.getInstance()
				.loadWeatherInfo(CITY_NAME, new TaskCallBack<WeatherBean>() {
					@Override
					public void onSuccess(WeatherBean weatherBean) {
						WeatherDataBean weatherDataBean = weatherBean.weatherDataBean;
						if (weatherDataBean != null) {
							view.loadWeatherInfoSuccess(weatherDataBean);
						} else {
							view.loadWeatherInfoError(weatherBean.message);
						}
					}

					@Override
					public void onError(Throwable throwable) {
						view.loadWeatherInfoError(throwable.getMessage());
					}
				});

	}

	@Override
	public void finishLoad() {

	}
}
