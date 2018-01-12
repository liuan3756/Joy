package com.test.liuan.joy.tv;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.test.liuan.joy.R;
import com.test.liuan.joy.bean.WeatherForecastBean;

import java.util.ArrayList;

public class FutureWeatherListAdapter extends
		RecyclerView.Adapter<FutureWeatherListAdapter.WeatherInfoHolder> {
	private LayoutInflater inflater;
	private ArrayList<WeatherForecastBean> list;
	
	public FutureWeatherListAdapter(Context context, ArrayList<WeatherForecastBean> list) {
		this.inflater = LayoutInflater.from(context);
		this.list = list;
	}
	
	@Override
	public FutureWeatherListAdapter.WeatherInfoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		return new WeatherInfoHolder(
				this.inflater.inflate(
						R.layout.item_forcast,
						null,
						false));
	}
	
	@Override
	public void onBindViewHolder(FutureWeatherListAdapter.WeatherInfoHolder holder, int position) {
		WeatherForecastBean weatherForecastBean = this.list.get(position);
		holder.tvDate.setText(weatherForecastBean.date);
		holder.tvTemp.setText(weatherForecastBean.getTempDescribe());
	}
	
	@Override
	public int getItemCount() {
		return this.list != null ? this.list.size() : 0;
	}
	
	class WeatherInfoHolder extends RecyclerView.ViewHolder {
		TextView tvDate;
		TextView tvTemp;
		
		WeatherInfoHolder(View itemView) {
			super(itemView);
			this.tvDate = itemView.findViewById(R.id.tvItemForecastDate);
			this.tvTemp = itemView.findViewById(R.id.tvItemForecastTemp);
		}
	}
}
