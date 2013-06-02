package agenda.serpisapp_alpha;

import com.example.serpisapp_alpha.MainActivity;
import com.example.serpisapp_alpha.R;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class Agenda extends ListActivity {
	private SQLiteDatabase db;
	private CursorAdapter cursorAdapter;
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.agenda);
//		CREA LA BASE DE DATOS
		AgendaSQLiteHelper dataBaseAlumno = new AgendaSQLiteHelper(this, "db_datos", null, 1);
		db = dataBaseAlumno.getWritableDatabase();
//		CREAMOS LA LISTA
		String columnas[] = { "titulo", "descripcion", "_id" };
		Cursor cursor = db.query("Notas", columnas, null, null, null, null,null);
		cursorAdapter = new SimpleCursorAdapter(this, R.layout.agenda_filas, cursor, columnas,new int[] { R.id.nota_titulo });
		final ListView listaAlumnos = getListView();
		listaAlumnos.setCacheColorHint(0);
		listaAlumnos.addHeaderView(getLayoutInflater().inflate(R.layout.agenda_filas, null));
		listaAlumnos.setOnItemClickListener(new OnItemClickListener() {
	
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
				int arg2, long arg3) {
				Intent intent = new Intent(Agenda.this, Edicion.class);
				Cursor cursor = (Cursor) listaAlumnos.getItemAtPosition(arg2);
				String id = cursor.getString(cursor.getColumnIndexOrThrow("_id"));
				String titulo = cursor.getString(cursor.getColumnIndexOrThrow("titulo"));
				String descripcion = cursor.getString(cursor.getColumnIndexOrThrow("descripcion"));
				intent.putExtra("Id", id);
				intent.putExtra("Titulo", titulo);
				intent.putExtra("Descripcion", descripcion);
				finish();
				startActivity(intent);				
				}
			});
			setListAdapter(cursorAdapter);
		}
	
//	MENU
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_agenda, menu);
        return true;
    }
	@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        case R.id.m_new_note:
        	nuevaNota();break;
        case R.id.m_new_event: 
            nuevoEvento();break;
    	}        
        return true;
        }
	
	
//	NUEVA NOTA
	public void nuevaNota(){
		Intent intent = new Intent(getApplicationContext(),NuevaNota.class);
		finish();
		startActivity(intent);
	}
//	NUEVO EVENTO
	public void nuevoEvento(){
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
			 Intent intent = new Intent(getApplicationContext(),MainActivity.class);
			 finish();
			 startActivity(intent);
	        }
		 return false;
	 }

}
