package social.serpisapp_alpha;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.KeyEvent;

import com.example.serpisapp_alpha.R;

public class Social_inicio extends PreferenceActivity {
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.layout.alert_register_layout);
        
        
    }
    
    public boolean onKeyDown(int keyCode, KeyEvent event) {
		 
		 if ((keyCode == KeyEvent.KEYCODE_BACK)) {
			 SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(Social_inicio.this);
			 if(pref.getString("pref_nombre", "")==""){
				 Intent i = new Intent(getApplicationContext(), social.serpisapp_alpha.Social_bienvenida.class);
		         finish();
		         startActivity(i); 
			 }else{
				 Intent i = new Intent(getApplicationContext(), social.serpisapp_alpha.Tablon.class);
		         finish();
		         startActivity(i); 
			 }				 
	        }
		 return false;
	 }
}
