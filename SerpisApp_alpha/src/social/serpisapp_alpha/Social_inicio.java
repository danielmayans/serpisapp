package social.serpisapp_alpha;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.example.serpisapp_alpha.R;

public class Social_inicio extends Activity{
	
	
	private EditText edit_nombre;
	private EditText edit_ap1;
	private EditText edit_ap2;
	private Button b_aceptar;
	private Button b_cancelar;
	
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.alert_register_layout);
		
		edit_nombre = (EditText)findViewById(R.id.edit_nombre);
		edit_ap1 = (EditText)findViewById(R.id.edit_ap1);
		edit_ap2 = (EditText)findViewById(R.id.edit_ap2);
		
		b_aceptar = (Button)findViewById(R.id.b_aceptar);
		b_aceptar.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Log.i("INFO1",edit_nombre.getText().toString());
//				SharedPreferences prefs = getSharedPreferences("MisPreferencias",Context.MODE_PRIVATE);
//				String nombre=prefs.getString("NOMBRE", edit_nombre.getText().toString());
//				String apellido1=prefs.getString("APELLIDO 1", edit_ap1.getText().toString());
//				String apellido2=prefs.getString("APELLIDO 2", edit_ap2.getText().toString());
				
				Intent intent = new Intent(getApplicationContext(),social.serpisapp_alpha.Tablon.class);
				finish();
				startActivity(intent);
				
			}
		});
		
		b_cancelar = (Button)findViewById(R.id.b_cancelar);
		b_cancelar.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getApplicationContext(),social.serpisapp_alpha.Social_bienvenida.class);
				finish();
				startActivity(intent);
				
			}
		});
	}
}
