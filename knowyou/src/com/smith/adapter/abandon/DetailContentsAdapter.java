package com.smith.adapter.abandon;

import java.util.List;

import com.smith.activity.R;
import com.smith.entity.Bean_common_detail_content;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class DetailContentsAdapter extends BaseAdapter {
	private Context context;
	private List<Bean_common_detail_content> datas;
	private OnClickListener onClickListener;

	public DetailContentsAdapter(Context context, List<Bean_common_detail_content> datas) {
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
	public Bean_common_detail_content getItem(int position) {
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
		final Bean_common_detail_content data = datas.get(position);

		if (convertView == null) {
			convertView = LayoutInflater.from(context).inflate(R.layout.detail_contents_item, null);
		}
		viewHolder.btn_content = (Button) convertView.findViewById(R.id.btn_content);
		viewHolder.btn_content.setText(data.getContet_name());
		viewHolder.btn_content.setTag(position);
		viewHolder.btn_content.setOnClickListener(onClickListener);

		return convertView;
	}

	public class ViewHolder {
		Button btn_content;
	}
}
