package com.smith.adapter;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;

import org.apache.http.client.utils.URLEncodedUtils;

import uk.co.senab.bitmapcache.CacheableBitmapDrawable;
import uk.co.senab.bitmapcache.samples.NetworkedCacheableImageView;
import uk.co.senab.bitmapcache.samples.NetworkedCacheableImageView.OnImageLoadedListener;

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
import android.widget.LinearLayout;
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
	private OnImageLoadedListener loadedListener;
	private UpdateViewListener updateViewListener;
	private int[] img_status; // status 1:正在加载 2：加载成功 3：加载失败

	public CommonDetailNextAdapter(Context context, List<Bean_common_page> datas) {
		this.context = context;
		this.datas = datas;
		updateViewListener = new UpdateViewListener();
		img_status = new int[datas.get(0).getMaximum()];
	}

	public void setOnClickListener(OnClickListener onRewardClickListener) {
		this.onClickListener = onRewardClickListener;
	}

	public void setOnImageLoadedListener(OnImageLoadedListener loadedListener) {
		this.loadedListener = loadedListener;
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

		convertView = LayoutInflater.from(context).inflate(R.layout.common_detail_next_item, null);

		viewHolder.img_content = (NetworkedCacheableImageView) convertView.findViewById(R.id.img_content);
		viewHolder.lly_conten_instead = (LinearLayout) convertView.findViewById(R.id.lly_conten_instead);
		String img_url = data.getPage_img_url();

		updateViewListener.setImg_status(img_status);
		updateViewListener.setInstead(viewHolder.lly_conten_instead);
		updateViewListener.setMe(viewHolder.img_content);
		if (null != img_url && !"".equals(img_url.trim())) {
			if(viewHolder.img_content.loadImage(img_url, false, updateViewListener, data.getSocketToHttp())){
				img_status[position]=1;
			};
		}
		switch (img_status[position]) {
		case 0:
			viewHolder.lly_conten_instead.setVisibility(View.VISIBLE);
			viewHolder.img_content.setVisibility(View.GONE);
			break;
		case 1:
			viewHolder.lly_conten_instead.setVisibility(View.GONE);
			viewHolder.img_content.setVisibility(View.VISIBLE);
			break;
		case 2:

			break;
		default:
			break;
		}

		return convertView;
	}

	public class ViewHolder {
		NetworkedCacheableImageView img_content;
		LinearLayout lly_conten_instead;
	}

	class UpdateViewListener implements NetworkedCacheableImageView.OnImageLoadedListener {

		private View instead;
		private View me;
		private int[] img_status;
		private int position;

		public void setInstead(View instead) {
			this.instead = instead;
		}

		public void setMe(View me) {
			this.me = me;
		}

		public void setImg_status(int[] img_status) {
			this.img_status = img_status;
		}

		public void setPosition(int position) {
			this.position = position;
		}

		@Override
		public void onImageLoaded(CacheableBitmapDrawable result) {

			if (result == null) {
				img_status[position]=2;
				return;
			}
			img_status[position]=1;
			instead.setVisibility(View.GONE);
			me.setVisibility(View.VISIBLE);

		}
	}
}
