package com.yibu.kuaibu.app;

import com.yibu.kuaibu.R;
import com.yibu.kuaibu.fragment.IndexFragment;
import com.yibu.kuaibu.fragment.FindFragment;
import com.yibu.kuaibu.fragment.FriendFragment;
import com.yibu.kuaibu.fragment.MyinfoFragment;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
/**
 * 主界面
 * @author zjc
 *
 * 2015-9-9
 */

public class MainActivity extends FragmentActivity implements OnClickListener{
	private RelativeLayout layout1;
	private RelativeLayout layout2;
	private RelativeLayout layout3;
	private RelativeLayout layout4;
	private RelativeLayout layout5;
	
	private ImageView imageView1;
	private ImageView imageView2;
	private ImageView imageView3;
	private ImageView imageView4;
	private ImageView imageView5;
	

	private LinearLayout fragmentLayout;

	private FragmentManager manager;
	private FragmentTransaction transaction;
	private TextView text1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);//无标题
		setContentView(R.layout.activity_main);
		init();
	}

	private void init(){
		layout1 = (RelativeLayout)findViewById(R.id.layout1);
		layout2 = (RelativeLayout)findViewById(R.id.layout2);
		layout3 = (RelativeLayout)findViewById(R.id.layout3);
		layout4 = (RelativeLayout)findViewById(R.id.layout4);
		layout5 = (RelativeLayout)findViewById(R.id.layout5);
		
		imageView1 = (ImageView)findViewById(R.id.iv_index);
		imageView2 = (ImageView)findViewById(R.id.iv2);
		imageView3 = (ImageView)findViewById(R.id.iv3);
		imageView4 = (ImageView)findViewById(R.id.iv4);
		imageView5 = (ImageView)findViewById(R.id.iv5);
		
		
		text1 = (TextView) findViewById(R.id.iv_index_text);

		fragmentLayout = (LinearLayout)findViewById(R.id.fragment_layout);

		layout1.setOnClickListener(this);
		layout2.setOnClickListener(this);
		layout3.setOnClickListener(this);
		layout4.setOnClickListener(this);
		layout5.setOnClickListener(this);

		Bundle myBundle=new Bundle();
		myBundle.putString("data", "one");

		manager = getSupportFragmentManager();
		transaction = manager.beginTransaction();

		IndexFragment myFragment = new IndexFragment();
		myFragment.setArguments(myBundle);
		transaction.replace(R.id.fragment_layout, myFragment, "myFragment");
		transaction.commit();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		manager = getSupportFragmentManager();
		transaction = manager.beginTransaction();
		
		switch (v.getId()) {
		case R.id.layout1:
			changeImageViewBg(imageView1);
			IndexFragment myFragment = new IndexFragment();
			transaction.replace(R.id.fragment_layout, myFragment, "myFragment");
			transaction.commit();
			break;
	
		case R.id.layout2:
			changeImageViewBg(imageView2);
			FindFragment myFragment2 = new FindFragment();
			transaction.replace(R.id.fragment_layout, myFragment2, "myFragment2");
			transaction.commit();
			break;
			
		case R.id.layout3:
			MyDialog myDialog=new MyDialog(MainActivity.this);
			myDialog.Show();
			break;
			
		case R.id.layout4:
			changeImageViewBg(imageView4);
			FriendFragment myFragment3 = new FriendFragment();
			transaction.replace(R.id.fragment_layout, myFragment3, "myFragment3");
			transaction.commit();
			break;
			
		case R.id.layout5:
			changeImageViewBg(imageView5);
			MyinfoFragment myFragment4 = new MyinfoFragment();
			transaction.replace(R.id.fragment_layout, myFragment4, "myFragment4");
			transaction.commit();
			break;

		default:
			break;
		}
	}
	
	private void changeImageViewBg(ImageView imageView){
		switch (imageView.getId()) {
		case R.id.iv_index:
			imageView1.setBackgroundResource(R.drawable.index_indicator_selected);
			imageView2.setBackgroundResource(R.drawable.find_indicator);
			imageView4.setBackgroundResource(R.drawable.myinfo_indicator_selected);
			imageView5.setBackgroundResource(R.drawable.friend_indicator);
			break;
		case R.id.iv2:
			imageView1.setBackgroundResource(R.drawable.index_indicator);
			imageView2.setBackgroundResource(R.drawable.find_indicator_selected);
			imageView4.setBackgroundResource(R.drawable.myinfo_indicator_selected);
			imageView5.setBackgroundResource(R.drawable.friend_indicator);
			break;
		case R.id.iv4:
			imageView1.setBackgroundResource(R.drawable.index_indicator);
			imageView2.setBackgroundResource(R.drawable.find_indicator);
			imageView4.setBackgroundResource(R.drawable.myinfo_indicator);
			imageView5.setBackgroundResource(R.drawable.friend_indicator);
			break;
		case R.id.iv5:
			imageView1.setBackgroundResource(R.drawable.index_indicator);
			imageView2.setBackgroundResource(R.drawable.find_indicator);
			imageView4.setBackgroundResource(R.drawable.myinfo_indicator_selected);
			imageView5.setBackgroundResource(R.drawable.friend_indicator_selected);
			//cese
			break;

		default:
			break;
		}
	}

}
