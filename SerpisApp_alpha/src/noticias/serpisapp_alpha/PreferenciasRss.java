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
	/*
    <string-array name="rss_value">
        <item>http://www.institutoserpis.org/feed</item>
        <item>http://ruvid.webs.upv.es/?cat=3&feed=rss2</item>
        <item>http://ruvid.webs.upv.es/?cat=4&feed=rss2</item>
        <item>http://ruvid.webs.upv.es/?cat=5&feed=rss2</item>
        <item>http://ruvid.webs.upv.es/?cat=6&feed=rss2</item>
        <item>http://ruvid.webs.upv.es/?cat=7&feed=rss2</item>
        <item>http://ruvid.webs.upv.es/?cat=13&feed=rss2</item>
        <item>http://www.mcu.es/comun/RSS/noticias.xml</item>
        <item>https://wwws.mcu.es/cultura20/web/guest/agenda/cultural/mcu/listado/detalle?p_p_id=MCU_AGENDA_5&p_p_lifecycle=2&p_p_cacheability=cacheLevelPage&p_p_col_id=column-2&p_p_col_pos=2&p_p_col_count=6&_MCU_AGENDA_5_groupId=10148&_MCU_AGENDA_5_tipoAgenda=&_MCU_AGENDA_5_struts_action=%2Fext%2Fagenda%2Fdetalle%2Frss</item>
        <item>http://www.mcu.es/comun/RSS/becas.xml</item>
    </string-array>
    */
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
