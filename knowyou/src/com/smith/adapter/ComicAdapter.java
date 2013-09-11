package com.smith.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.smith.activity.R;
import com.smith.entity.Bean_common;

public class ComicAdapter extends BaseAdapter {
	private Context context;
	private List<Bean_common> datas;
	private OnClickListener onClickListener;

	public ComicAdapter(Context context, List<Bean_common> datas) {
		this.context = context;
		this.datas = datas;
	}

	public void setOnClickListener(OnClickListener onRewardClickListener) {
		this.onClickListener = onRewardClickListener;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return datas.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return datas.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub

		final ViewHolder viewHolder = new ViewHolder();
		final Bean_common data = datas.get(position);
		convertView = LayoutInflater.from(context).inflate(
				R.layout.channel_item, null);

		viewHolder.img_cover = (ImageView) convertView
				.findViewById(R.id.img_cover);
		viewHolder.txt_data_name = (TextView) convertView
				.findViewById(R.id.txt_data_name);

		return convertView;
	}

	public class ViewHolder {
		ImageView img_cover;
		TextView txt_data_name;
	}
}
