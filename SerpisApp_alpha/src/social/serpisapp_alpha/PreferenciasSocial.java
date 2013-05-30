package social.serpisapp_alpha;

import com.example.serpisapp_alpha.R;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.view.KeyEvent;

public class PreferenciasSocial extends PreferenceActivity{

	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.layout.pref_social);
    }
	
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		 
		 if ((keyCode == KeyEvent.KEYCODE_BACK)) {
			 Intent i = new Intent(getApplicationContext(), social.serpisapp_alpha.Tablon.class);
	         finish();
	         startActivity(i);
			 
	        }
		 return false;
	 }
}
