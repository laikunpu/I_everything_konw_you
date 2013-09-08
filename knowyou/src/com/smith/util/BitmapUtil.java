package com.smith.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;

public class BitmapUtil {
	public static Bitmap ReadBitmapById(Context mContext, int resId,
			int mWindth, int mSurfaceHeight) {
		Options opts = new Options();
		opts.outWidth = mWindth;
		opts.outHeight = mSurfaceHeight;
		Bitmap bitmap = BitmapFactory.decodeResource(mContext.getResources(),
				resId, opts);
		return bitmap;
	}
}
