package com.example.serpisapp_alpha;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class MainActivity extends FragmentActivity {
	
	private ImageButton bSocial;
	private ImageButton bNoticias;
	private ImageButton bAgenda;
	
	public static String static_rss;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.principal);
		SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
		
		MainActivity.static_rss = pref.getString("RSS", "http://www.institutoserpis.org/feed");
		/**
		 * SOCIAL
		 */
		bSocial = (ImageButton) findViewById(R.id.imageButton3);
		bSocial.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(getApplicationContext(), social.serpisapp_alpha.Social_bienvenida.class);
				finish();
				startActivity(i);
				
			}
		});
		      /**
		       * NOTICIAS
		       */
		bNoticias = (ImageButton) findViewById(R.id.imageButton2);
		bNoticias.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent i = new Intent(getApplicationContext(), noticias.serpisapp_alpha.Noticias.class);
				finish();
				startActivity(i);
				
			}
		});
		
		bAgenda = (ImageButton) findViewById(R.id.imageButton1);
		bAgenda.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(getApplicationContext(), agenda.serpisapp_alpha.Agenda.class);
				finish();
				startActivity(i);
				
			}
		});
		
        ViewPager pager = (ViewPager) findViewById(R.id.pager);
        
        FragmentManager fm = getSupportFragmentManager();
        
        MyFragmentPagerAdapter pagerAdapter = new MyFragmentPagerAdapter(fm);
        
        pager.setAdapter(pagerAdapter);
	}

	public boolean onKeyDown(int keyCode, KeyEvent event) {
		 
		 if ((keyCode == KeyEvent.KEYCODE_BACK)) {
			 android.os.Process.killProcess(android.os.Process.myPid()); 
	        }
		 return false;
	 }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

}
