package com.yibu.kuaibu.fragment;



import com.yibu.kuaibu.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
/**
 * Fragment
 * @author zjc
 *
 * 2015-9-9
 */

public class FriendFragment extends Fragment {
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.friend_layout, container, false);
		return view;
	}
}
