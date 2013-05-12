package social.serpisapp_alpha;

import com.example.serpisapp_alpha.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class Social_bienvenida extends Activity{
	
	private TextView t_tit;
	private TextView t_subt;
	private Button b_entrar;
	
	
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.social_bienvenida);
		
		final SharedPreferences prefs = getSharedPreferences("MisPreferencias",Context.MODE_PRIVATE);
		
		Typeface face=Typeface.createFromAsset(getAssets(),"fonts/ByPeopleHandwritten.ttf");
		
		t_tit = (TextView)findViewById(R.id.texto_prueba);
		t_subt = (TextView)findViewById(R.id.texto_prueba1);
		t_tit.setTypeface(face);
		t_subt.setTypeface(face);
		
		b_entrar = (Button)findViewById(R.id.b_entrar);
		b_entrar.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(prefs.getString("NOMBRE", "")==""){
					Intent i = new Intent(getApplicationContext(), social.serpisapp_alpha.Social_inicio.class);
					finish();
					startActivity(i);
				}else{
					Intent i = new Intent(getApplicationContext(), social.serpisapp_alpha.Tablon.class);
					finish();
					startActivity(i);
				}
			}
		});
		
	}
	
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		 
		 if ((keyCode == KeyEvent.KEYCODE_BACK)) {                               
	            Intent i = new Intent(getApplicationContext(), com.example.serpisapp_alpha.MainActivity.class);
	            finish();
	            startActivity(i); 
	            
	        }
		 return false;
	 }

}
