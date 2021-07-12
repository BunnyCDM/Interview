package com.kl.aop.reflection;

import android.widget.Toast;

public class ToastUtils {
	private static Toast makeText;
	public static void show(String text){
		if(null==makeText){
			makeText = Toast.makeText(ContextUtil.getContext(), text, Toast.LENGTH_SHORT);
		}else{
			makeText.setText(text);
		}
		makeText.show();
	}
}
