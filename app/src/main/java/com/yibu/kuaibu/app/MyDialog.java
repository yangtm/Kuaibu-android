package com.yibu.kuaibu.app;


import com.yibu.kuaibu.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.Window;
/**
 * 自定义Dialog
 * @author zjc
 *
 * 2015-9-9
 */
public class MyDialog {
	private AlertDialog dialog;
	private Activity activity;
	
	public MyDialog(Activity activity) {
		this.activity=activity;
	}
	
	public void Show(){
		dialog=new AlertDialog.Builder(activity).create();
		dialog.show();

		Window window = dialog.getWindow();
		window.setContentView(R.layout.mydialog);
		window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,
				ViewGroup.LayoutParams.WRAP_CONTENT);
		window.setGravity(Gravity.BOTTOM);
	}
}
