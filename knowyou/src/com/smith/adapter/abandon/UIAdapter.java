//package com.smith.adapter.abandon;
//
//import java.util.List;
//
//import com.smith.activity.R;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.view.View.OnClickListener;
//import android.widget.BaseAdapter;
//import android.widget.LinearLayout;
//import android.widget.TextView;
//
//public class UIAdapter extends BaseAdapter {
//	private Context context;
//	private OnClickListener onClickListener;
//
//	public UIAdapter(Context context) {
//		this.context = context;
//
//	}
//
//	public void setOnClickListener(OnClickListener onRewardClickListener) {
//		this.onClickListener = onRewardClickListener;
//	}
//
//	@Override
//	public int getCount() {
//		// TODO Auto-generated method stub
//		return uis.size();
//	}
//
//	@Override
//	public Bean_UI getItem(int position) {
//		// TODO Auto-generated method stub
//		return uis.get(position);
//	}
//
//	@Override
//	public long getItemId(int position) {
//		// TODO Auto-generated method stub
//		return position;
//	}
//
//	@Override
//	public View getView(int position, View convertView, ViewGroup parent) {
//		// TODO Auto-generated method stub
//
//		final ViewHolder viewHolder = new ViewHolder();
//		final Bean_UI ui = uis.get(position);
//
//		if (convertView == null) {
//			convertView = LayoutInflater.from(context).inflate(R.layout.ui_item, null);
//
//		}
//		viewHolder.txt_module_name = (TextView) convertView.findViewById(R.id.txt_module_name);
//		viewHolder.lly_module = (LinearLayout) convertView.findViewById(R.id.lly_module);
//		viewHolder.txt_module_name.setText(ui.getName());
//		viewHolder.lly_module.getBackground().setAlpha(80);
//		viewHolder.lly_module.setTag(position);
//		viewHolder.lly_module.setOnClickListener(onClickListener);
//
//		return convertView;
//	}
//
//	public class ViewHolder {
//		TextView txt_module_name;
//		LinearLayout lly_module;
//	}
//}
