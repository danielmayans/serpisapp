package agenda.serpisapp_alpha;

import com.example.serpisapp_alpha.R;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class Edicion extends Activity {
	private SQLiteDatabase db;
	
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.agenda_edit);
        
        AgendaSQLiteHelper dataBaseAlumno = new AgendaSQLiteHelper(this, "db_datos", null, 1);
		db = dataBaseAlumno.getWritableDatabase();
        
        Bundle intent = getIntent().getExtras();
        
        final String b_id = intent.getString("Id");
        String b_nombre = intent.getString("Titulo");
        String b_edad = intent.getString("Descripcion");
        
        final EditText nota_Titulo = (EditText)findViewById(R.id.nota_titulo);
        final EditText edit_Descripcion = (EditText)findViewById(R.id.edit_Descripcion);
        
        final Button b_Actualizar = (Button)findViewById(R.id.actualizar);
        final Button b_Borrar = (Button)findViewById(R.id.borrar);
        
        b_Actualizar.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
	        	db.execSQL("UPDATE Notas SET titulo = '"+ nota_Titulo.getText().toString() 
			+"', descripcion = '"+ edit_Descripcion.getText().toString() 
			+"' WHERE _id = '"+ b_id +"'");
	        	Intent intent = new Intent(getApplicationContext(),Agenda.class);
	        	Edicion.this.finish();
	        	startActivity(intent);
				
			}
		});
        
        b_Borrar.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				db.execSQL("DELETE FROM Notas WHERE _id = '"+ b_id +"'");
				Intent intent = new Intent(getApplicationContext(),Agenda.class);
				Edicion.this.finish();
				startActivity(intent);
			}
		});
        
        nota_Titulo.setText(b_nombre);
        edit_Descripcion.setText(b_edad);
    }
    
    public boolean onKeyDown(int keyCode, KeyEvent event) {
		 
		 if ((keyCode == KeyEvent.KEYCODE_BACK)) {
			 Intent intent = new Intent(getApplicationContext(),Agenda.class);
			 finish();
			 startActivity(intent);
	        }
		 return false;
	 }
}
