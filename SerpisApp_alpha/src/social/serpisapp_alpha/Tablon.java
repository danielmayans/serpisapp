package social.serpisapp_alpha;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.serpisapp_alpha.R;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.widget.SimpleCursorAdapter;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class Tablon extends ListActivity {
	
	
	// Progress Dialog
	private ProgressDialog pDialog;
	private String PREF_CURSO;
	private int fondo_tablon;
	// Creando el objeto JSON Parser
	JSONParser jParser = new JSONParser();
	JSONParser jsonParser = new JSONParser();
	
	ArrayList<HashMap<String, String>> listaMensajes;

	// URL para consultar todos los mensajes del tablon
	private static String url_tablon = "http://www.proyectawordpress.com/json/tablon.php";

	// JSON Node names
	private static final String TAG_SUCCESS = "success";
	private static final String TAG_MESSAGES = "messages";
	private static final String TAG_PID = "pid";
	private static final String TAG_DESCRIPTION = "mensaje";
	private static final String TAG_NAME = "nombre";
	private static final String TAG_APELLIDO1 = "ap1";
	// JSONArray
	JSONArray messages = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lay_tablon);
		
		SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(Tablon.this);
		PREF_CURSO = pref.getString("pref_curso", "");
		/*
		if(PREF_CURSO.equals("DAM")){fondo_tablon = R.drawable.back_dam;}
		else if(PREF_CURSO.equals("ASIR")){fondo_tablon = R.drawable.back_asir;}
		else if(PREF_CURSO.equals("SMR")){fondo_tablon = R.drawable.back_smr;}
		*/
		// Hashmap para el ListView
		listaMensajes = new ArrayList<HashMap<String, String>>();
		
		// Cargando los mensajes con Background Thread
		new CargaMensajes().execute();

	}
	 
	// Response from Edit Product Activity
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		
		if (resultCode == 100) {
			// Al recibir el codigo, recarga la lista de mensajes
			Intent intent = getIntent();
			finish();
			startActivity(intent);
		}

	}

	/**
	 * Background Async Task
	 * */
	class CargaMensajes extends AsyncTask<String, String, String> {

		/**
		 * Progress Dialog
		 * */
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(Tablon.this);
			pDialog.setMessage("Cargando el Tablon de mensajes. Por favor, espere...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(true);
			pDialog.show();
		}

		/**
		 * Sacando los mensajes de la URL
		 * */
		protected String doInBackground(String... args) {
			
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("curso", PREF_CURSO));
			
			
			
			JSONObject json = jParser.makeHttpRequest(url_tablon, "GET", params);
			

			try {

				int success = json.getInt(TAG_SUCCESS);

				if (success == 1) {
					// La variable de control determina que ha encontrado mensajes

					messages = json.getJSONArray(TAG_MESSAGES);

					// Looping todos los mensajes
					for (int i = 0; i < messages.length(); i++) {
						JSONObject c = messages.getJSONObject(i);

						String id = c.getString(TAG_PID);
						String name = c.getString(TAG_NAME)+" "+c.getString(TAG_APELLIDO1);
						String description = c.getString(TAG_DESCRIPTION);
						
						HashMap<String, String> map = new HashMap<String, String>();

						map.put(TAG_PID, id);
						map.put(TAG_NAME,name);
						map.put(TAG_DESCRIPTION, description);

						listaMensajes.add(map);
					}
				} else {
					// No hay mensajes
					Intent i = new Intent(getApplicationContext(),
							NuevoMensaje.class);
					// Cerrando Activities anteriores
					i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					startActivity(i);
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}

			return null;
		}

		/**
		 * Al terminar cierra el Progress Dialog
		 * **/
		protected void onPostExecute(String file_url) {

			pDialog.dismiss();

			runOnUiThread(new Runnable() {
				public void run() {
					/**
					 * Actualizando el ListView con la informacion del JSON
					 * */
					SimpleAdapter adapter = new SimpleAdapter(
							Tablon.this, listaMensajes,
							R.layout.list_item, new String[] { TAG_PID,TAG_NAME,TAG_DESCRIPTION},
							new int[] { R.id.pid, R.id.name, R.id.comentario });
					
					
					ListView lv = getListView();
				    lv.setCacheColorHint(0);
					setListAdapter(adapter);
				}	
			});
		}
	}
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		 
		 if ((keyCode == KeyEvent.KEYCODE_BACK)) {                               
	            Intent i = new Intent(getApplicationContext(), com.example.serpisapp_alpha.MainActivity.class);
	            finish();
	            startActivity(i); 
	            
	        }
		 return false;
	 }
	
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu_new_msj, menu);
		return true;
	}
	
	public boolean onOptionsItemSelected(MenuItem item) {
		Intent intent;
        switch (item.getItemId()) {
        case R.id.m_new_msj:
        	intent = new Intent(getApplicationContext(), NuevoMensaje.class);
        	intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        	startActivity(intent);
        	break;
        case R.id.m_act_tablon:
        	intent = new Intent(getApplicationContext(), Tablon.class);
			finish();
			startActivity(intent);
        	break;
        case R.id.m_cambia_curso:
        	intent = new Intent(getApplicationContext(), PreferenciasSocial.class);
			finish();
			startActivity(intent);
        	break;
    	}        
        return true;
        }
}