package com.yibu.kuaibu.app;

import com.yibu.kuaibu.R;
import com.yibu.kuaibu.fragment.LoginFragment;
import com.yibu.kuaibu.fragment.RegisterFragment;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.ContextMenu;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class LoginActivity extends FragmentActivity implements OnClickListener{

	private TextView login,register;
	private FragmentManager manager;
	private FragmentTransaction transaction;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		init();
	}
	private void init() {
		Bundle myBundle=new Bundle();
		myBundle.putString("data", "one");
		manager = getSupportFragmentManager();
		transaction = manager.beginTransaction();
		
		LoginFragment myFragment = new LoginFragment();
		
		myFragment.setArguments(myBundle);
		transaction.replace(R.id.fragment_layout1, myFragment, "myFragment");
		transaction.commit();
		
		login = (TextView) findViewById(R.id.iv_login);
		register = (TextView) findViewById(R.id.iv_register);

		login.setOnClickListener(this);
		register.setOnClickListener(this);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		getMenuInflater().inflate(R.menu.login_out, menu);
		return true;
	}
	@Override
	public void onClick(View v) {
		manager = getSupportFragmentManager();
		transaction = manager.beginTransaction();
		
		switch (v.getId()) {
		case R.id.iv_login:
			LoginFragment loginreg = new LoginFragment();
			transaction.replace(R.id.fragment_layout1, loginreg, "myFragment");
			transaction.commit();
			break;

		case R.id.iv_register:
			RegisterFragment regist = new RegisterFragment();
			transaction.replace(R.id.fragment_layout1, regist, "myFragment");
			transaction.commit();
			break;
		default:
			break;
		}
		
	}
	
}
