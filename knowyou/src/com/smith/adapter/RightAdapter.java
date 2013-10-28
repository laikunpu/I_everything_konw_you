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

public class RightAdapter extends BaseAdapter {
	private Context context;
	private List<String> datas;
	private OnClickListener onClickListener;

	public RightAdapter(Context context, List<String> datas) {
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
		final String data = datas.get(position);
		if (convertView == null) {
			convertView = LayoutInflater.from(context).inflate(R.layout.right_item, null);
		}
		viewHolder.txt_rightItem = (TextView) convertView.findViewById(R.id.txt_rightItem);
		
		viewHolder.txt_rightItem.setText(data);
		viewHolder.txt_rightItem.setTag(position);
		return convertView;
	}

	public class ViewHolder {
		TextView txt_rightItem;
	}
}
