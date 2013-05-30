package noticias.serpisapp_alpha;

import social.serpisapp_alpha.Social_inicio;

import com.example.serpisapp_alpha.MainActivity;
import com.example.serpisapp_alpha.R;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.KeyEvent;

public class PreferenciasRss extends PreferenceActivity{

	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.layout.pref_rss);
    }
	
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		 
		 if ((keyCode == KeyEvent.KEYCODE_BACK)) {
			 SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(PreferenciasRss.this);
			 MainActivity.static_rss = pref.getString("RSS", "");
			 Intent i = new Intent(getApplicationContext(), noticias.serpisapp_alpha.Noticias.class);
	         finish();
	         startActivity(i);
			 
	        }
		 return false;
	 }
}
