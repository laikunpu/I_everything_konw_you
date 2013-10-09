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
import com.smith.entity.Bean_module;

public class MidAdapter extends BaseAdapter {
	private Context context;
	private List<Bean_module> datas;
	private OnClickListener onClickListener;

	public MidAdapter(Context context, List<Bean_module> datas) {
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
		final Bean_module data = datas.get(position);
		if (convertView == null) {
			convertView = LayoutInflater.from(context).inflate(R.layout.left_item, null);
		}
		viewHolder.img_leftItem = (ImageView) convertView.findViewById(R.id.img_leftItem);
		viewHolder.txt_leftItem = (TextView) convertView.findViewById(R.id.txt_leftItem);
		
		viewHolder.txt_leftItem.setText(data.getModule_name());
		viewHolder.img_leftItem.setTag(position);
		viewHolder.img_leftItem.setOnClickListener(onClickListener);
		return convertView;
	}

	public class ViewHolder {
		ImageView img_leftItem;
		TextView txt_leftItem;
	}
}
