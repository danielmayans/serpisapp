package agenda.serpisapp_alpha;

import com.example.serpisapp_alpha.MainActivity;
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

public class NuevaNota extends Activity{
	
	private SQLiteDatabase db;
	
	private EditText new_titulo;
	private EditText new_descripcion;
	private Button b_new_nota;
	private Button b_cancel;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.agenda_new_nota);
		
		 AgendaSQLiteHelper dataBaseAlumno = new AgendaSQLiteHelper(this, "db_datos", null, 1);
			db = dataBaseAlumno.getWritableDatabase();
		
		new_titulo = (EditText)findViewById(R.id.new_titulo);
		new_descripcion = (EditText)findViewById(R.id.new_descripcion);
		b_new_nota = (Button)findViewById(R.id.b_new_nota);
		b_cancel = (Button)findViewById(R.id.b_cancelar);
		
		b_new_nota.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				nuevaNota();
			}});
		
		b_cancel.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getApplicationContext(),Agenda.class);
				finish();
				startActivity(intent);
			}});
		
		}
	
	public void nuevaNota(){
		String titulo = new_titulo.getText().toString();
		String descripcion = new_descripcion.getText().toString();
		
		db.execSQL("INSERT INTO Notas (titulo, descripcion) VALUES ('"+titulo+"', '"+descripcion+"')");
		
		Intent intent = new Intent(getApplicationContext(),Agenda.class);
		finish();
		startActivity(intent);
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
