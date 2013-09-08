package com.smith.adapter;

import java.util.List;

import com.smith.activity.R;
import com.smith.entity.Bean_UI;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ModuleAdapter extends BaseAdapter {
	private Context context;
	private List<Bean_UI> uis;
	private OnClickListener onClickListener;

	public ModuleAdapter(Context context, List<Bean_UI> uis) {
		this.context = context;
		this.uis = uis;

	}

	public void setOnClickListener(OnClickListener onRewardClickListener) {
		this.onClickListener = onRewardClickListener;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return uis.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return uis.get(position);
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
		final Bean_UI ui = uis.get(position);
		convertView = LayoutInflater.from(context).inflate(
				R.layout.module_item, null);

		viewHolder.txt_module_name = (TextView) convertView
				.findViewById(R.id.txt_module_name);
		viewHolder.lly_module = (LinearLayout) convertView
				.findViewById(R.id.lly_module);

		viewHolder.txt_module_name.setText(ui.getName());
		viewHolder.lly_module.getBackground().setAlpha(80);
		viewHolder.lly_module.setOnClickListener(onClickListener);

		return convertView;
	}

	public class ViewHolder {
		TextView txt_module_name;
		LinearLayout lly_module;
	}
}
