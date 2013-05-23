package agenda.serpisapp_alpha;


import com.example.serpisapp_alpha.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class Agenda extends Activity {

	private Button boton_nota;
	private Button boton_listado;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.agenda);
        crearListado();
        crearEventoCalendario();
    }
    private void crearEventoCalendario() {
    	this.boton_nota = (Button) findViewById(R.id.boton_nota);
         this.boton_nota.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				crearNotaCalendario();

			}
		});
    }
    
    private void crearListado() {
    	this.boton_listado = (Button) findViewById(R.id.button3);
         this.boton_listado.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				  lanzarListado();
			}
		});
    }
    
    public void lanzarListado() { 
  	  Intent i = new Intent(this, Notepad.class); 
  	  startActivity(i); 
  	}
    
    private void crearNotaCalendario() {
    	Intent l_intent = new Intent(Intent.ACTION_EDIT);
    	// API de calendario proporcionada por Android
    	l_intent.setType("vnd.android.cursor.item/event");
    	l_intent.putExtra("Asunto", "Asunto");
    	l_intent.putExtra("Descripcion", "Introduce tu descripcion");
    	l_intent.putExtra("Lugar", "@home");
    	l_intent.putExtra("Fecha Inicio", System.currentTimeMillis());
    	l_intent.putExtra("Fecha Final", System.currentTimeMillis() + 1800*1000);
    	l_intent.putExtra("Todo el dia", 0);
    	//status: 0~ tentative; 1~ confirmed; 2~ canceled
    	l_intent.putExtra("eventStatus", 1);
    	//0~ default; 1~ confidential; 2~ private; 3~ public
    	l_intent.putExtra("visibility", 0);
    	l_intent.putExtra("transparency", 0);
    	//0~ false; 1~ true
    	l_intent.putExtra("Activar Alarma", 1);
    	try {
    		startActivity(l_intent);
    	} catch (Exception e) {
    		Toast.makeText(this.getApplicationContext(), "Error, no se ha encontrado un calendario compatible!", Toast.LENGTH_LONG).show();
    	}
    }
    public boolean onKeyDown(int keyCode, KeyEvent event) {
		 
		 if ((keyCode == KeyEvent.KEYCODE_BACK)) {
			 Intent intent = new Intent(getApplicationContext(),com.example.serpisapp_alpha.MainActivity.class);
			 finish();
			 startActivity(intent);
	        }
		 return false;
	 }
}