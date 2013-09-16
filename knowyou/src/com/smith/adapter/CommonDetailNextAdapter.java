package com.smith.adapter;

import java.util.List;

import uk.co.senab.bitmapcache.samples.NetworkedCacheableImageView;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.HttpAuthHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.smith.activity.R;
import com.smith.entity.Bean_common;
import com.smith.entity.Bean_common_detail_content;
import com.smith.entity.Bean_common_page;
import com.smith.entity.Bean_common_page_Res;

public class CommonDetailNextAdapter extends BaseAdapter {
	private Context context;
	private List<Bean_common_page> datas;
	private OnClickListener onClickListener;

	public CommonDetailNextAdapter(Context context, List<Bean_common_page> datas) {
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
		final Bean_common_page data = datas.get(position);
		final int pos = position;

		convertView = LayoutInflater.from(context).inflate(R.layout.common_detail_next_item, null);

		viewHolder.img_content = (NetworkedCacheableImageView) convertView.findViewById(R.id.img_content);
		String img_url = data.getPage_img_url();
		if (null != img_url && !"".equals(img_url.trim())) {
			viewHolder.img_content.loadImage(img_url, false, null, data.getSocketToHttp());
		}

		// System.out.println("wb_content->url= " + data.getPage_url());
		return convertView;
	}

	public class ViewHolder {
		NetworkedCacheableImageView img_content;
	}
}
