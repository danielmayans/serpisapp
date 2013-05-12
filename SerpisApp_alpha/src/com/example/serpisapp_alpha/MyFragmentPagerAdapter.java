package com.example.serpisapp_alpha;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class MyFragmentPagerAdapter extends FragmentPagerAdapter{
	
	final int PAGE_COUNT = 2;

	public MyFragmentPagerAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int arg0) {
		
		MyFragment myFragment = new MyFragment();
		Bundle data = new Bundle();
		data.putInt("current_page", arg0+1);
		myFragment.setArguments(data);
		return myFragment;
	}

	@Override
	public int getCount() {		
		return PAGE_COUNT;
	}
	
	@Override
	public CharSequence getPageTitle(int position) {		
		if(position==0){
		return "Bienvenido";
		}else{ return "Secretaria";}
 
	}
	
	
	
}
