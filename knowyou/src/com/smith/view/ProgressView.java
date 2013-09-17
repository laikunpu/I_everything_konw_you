package com.smith.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class ProgressView extends View {
	private float fArcNum;
	private float max;
	private float density;

	private int scale = 40;

	// private int percent;

	public float getDensity() {
		return density;
	}

	public void setDensity(float density) {
		this.density = density;
	}

	public ProgressView(Context context) {
		super(context);
	}

	public ProgressView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		Paint paint = new Paint();
		// if (fArcNum > 0) {
		paint.setColor(Color.GRAY);
		paint.setFlags(Paint.ANTI_ALIAS_FLAG);
		canvas.drawCircle(scale * density / 2, scale * density / 2, scale * density / 2, paint);
		// }
		paint.setColor(Color.BLUE);
		paint.setFlags(Paint.ANTI_ALIAS_FLAG);
		// paint.setStrokeWidth(2);
		RectF rect = new RectF(0, 0, scale * density, scale * density);
		canvas.drawArc(rect, -90, fArcNum, true, paint);
		paint.setColor(Color.BLACK);
		paint.setFlags(Paint.ANTI_ALIAS_FLAG);
		canvas.drawCircle(scale * density / 2, scale * density / 2, scale * density / 2 - 5, paint);
		paint.setColor(Color.BLACK);
		paint.setFlags(Paint.ANTI_ALIAS_FLAG);
		paint.setTextSize(30);
		paint.setTextAlign(Align.CENTER);
		// canvas.drawText(percent+"%", scale * density / 2, scale * density /
		// 2+10, paint);
	}

	public void setProgress(float num) {
		fArcNum = (num / max) * 360;
		// percent=(int)((num / max) * 100);
		this.postInvalidate();
	}

	public float getfArcNum() {
		return fArcNum;
	}

	public void setfArcNum(float fArcNum) {
		this.fArcNum = fArcNum;
	}

	public float getMax() {
		return max;
	}

	public void setMax(float fMax) {
		this.max = fMax;
	}

	public void clear() {
		fArcNum = 0;
		// percent=0;
	}
}