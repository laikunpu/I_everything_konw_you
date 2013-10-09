package com.smith.adapter.abandon;

import java.util.List;

import uk.co.senab.bitmapcache.samples.NetworkedCacheableImageView;

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
import com.smith.entity.Bean_common_search;

public class CommonSearchAdapter extends BaseAdapter {
	private Context context;
	private List<Bean_common_search> datas;
	private OnClickListener onClickListener;

	public CommonSearchAdapter(Context context, List<Bean_common_search> datas) {
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
		final Bean_common_search data = datas.get(position);
		if (convertView == null) {
			convertView = LayoutInflater.from(context).inflate(R.layout.search_item, null);
		}
		viewHolder.img_cover = (NetworkedCacheableImageView) convertView.findViewById(R.id.img_cover);
		viewHolder.txt_bookName = (TextView) convertView.findViewById(R.id.txt_bookName);
		viewHolder.txt_bookAuthor = (TextView) convertView.findViewById(R.id.txt_bookAuthor);
		viewHolder.txt_modifiedTime = (TextView) convertView.findViewById(R.id.txt_modifiedTime);
		viewHolder.txt_summary = (TextView) convertView.findViewById(R.id.txt_summary);

		
		viewHolder.txt_bookName.setText(data.getName());
		viewHolder.txt_bookAuthor.setText(data.getAuthor());
		viewHolder.txt_modifiedTime.setText(data.getModify());
		viewHolder.txt_summary.setText(data.getSummary());
		
		
		viewHolder.img_cover.loadImage(data.getCover_url(), true, null, null);
		viewHolder.img_cover.setTag(position);
		viewHolder.img_cover.setOnClickListener(onClickListener);
		return convertView;
	}

	public class ViewHolder {
		NetworkedCacheableImageView img_cover;
		TextView txt_bookName;
		TextView txt_bookAuthor;
		TextView txt_modifiedTime;
		TextView txt_summary;
	}
}
