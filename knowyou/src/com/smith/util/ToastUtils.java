package com.smith.util;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;
 
/**
 * Toast utils
 * 
 */
public class ToastUtils {
 
    private static Handler handler = new Handler(Looper.getMainLooper());
 
    private static Toast toast = null;
 
    private static Object synObj = new Object();
    
 
    public static void showToast(final Context context, final String msg) {
        showToast(context, msg, Toast.LENGTH_SHORT);
    }
 
    public static void showToast(final Context context, final int resId) {
        showToast(context, resId, Toast.LENGTH_SHORT);
    }
 
    public static void showToast(final Context context, final String msg, final int len) {
        new Thread(new Runnable() {
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                    	Log.i("an", "showToast##############################1");
                        synchronized (synObj) {// 同步锁
                            if (toast != null) {
                            	Log.i("an", "showToast##############################2_if");
                                toast.setDuration(len);
                                toast.setText(msg);
                            } else {
                            	Log.i("an", "showToast##############################2_else");
                                toast = Toast.makeText(context, msg, len);
                            }
                        	Log.i("an", "showToast##############################3");
                            toast.show();
                        	Log.i("an", "showToast##############################4");
                        }
                    }
                });
            }
        }).start();
    }
 
    public static void showToast(final Context context, final int resId, final int len) {
        new Thread(new Runnable() {
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        synchronized (synObj) {// 同步锁
                            if (toast != null) {
                                toast.setDuration(len);
                                toast.setText(resId);
                            } else {
                                toast = Toast.makeText(context, resId, len);
                            }
                            toast.show();
                        }
                    }
                });
            }
        }).start();
    }
 
}