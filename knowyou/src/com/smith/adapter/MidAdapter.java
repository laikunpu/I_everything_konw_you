package com.smith.adapter;

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
import com.smith.entity.Bean_module;

public class MidAdapter extends BaseAdapter {
	private Context context;
	private List<Bean_common> datas;
	private OnClickListener onClickListener;

	public MidAdapter(Context context, List<Bean_common> datas) {
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
		if (convertView == null) {
			convertView = LayoutInflater.from(context).inflate(R.layout.mid_item, null);
		}
		viewHolder.img_cover = (NetworkedCacheableImageView) convertView.findViewById(R.id.img_cover);
		viewHolder.txt_bookName = (TextView) convertView.findViewById(R.id.txt_bookName);
		viewHolder.txt_bookAuthor = (TextView) convertView.findViewById(R.id.txt_bookAuthor);
		viewHolder.txt_modifiedTime = (TextView) convertView.findViewById(R.id.txt_modifiedTime);
		
		
		viewHolder.img_cover.loadImage(data.getCover_url(), true, null, null);
		viewHolder.txt_bookName.setText(data.getName());
		
		
		
		return convertView;
	}

	public class ViewHolder {
		NetworkedCacheableImageView img_cover;
		TextView txt_bookName;
		TextView txt_bookAuthor;
		TextView txt_modifiedTime;
	}
}
