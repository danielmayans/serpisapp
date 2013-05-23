package social.serpisapp_alpha;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.serpisapp_alpha.R;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class NuevoMensaje extends Activity {

	private ProgressDialog pDialog;

	JSONParser jsonParser = new JSONParser();
	TextView text_alum;
	EditText input_msj;

	private static String url_nuevo_mensaje = "http://www.proyectawordpress.com/json/nuevo_mensaje.php";
	private static final String TAG_SUCCESS = "success";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.new_mensj);
		SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(NuevoMensaje.this);
		String nom = pref.getString("pref_nombre", "");
		String ap1 = pref.getString("pref_ap1", "");
		String ap2 = pref.getString("pref_ap2", "");
		text_alum = (TextView) findViewById(R.id.text_alum);
		text_alum.setText(nom+" "+ap1+" "+ap2);
		input_msj = (EditText) findViewById(R.id.input_msj);

		Button btnCreateProduct = (Button) findViewById(R.id.btnNewMsj);

		btnCreateProduct.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View view) {
				new CreateNewProduct().execute();
			}
		});
	}

	/**
	 * Background Async Task
	 * */
	class CreateNewProduct extends AsyncTask<String, String, String> {

		/**
		 * Progress Dialog
		 * */
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(NuevoMensaje.this);
			pDialog.setMessage("Enviando mensaje...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(true);
			pDialog.show();
		}

		/**
		 * Creando el mensaje
		 * */
		protected String doInBackground(String... args) {
			SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(NuevoMensaje.this);
			
			String p_nombre = pref.getString("pref_nombre", "");
			String p_ap1 = pref.getString("pref_ap1", "");
			String p_ap2 = pref.getString("pref_ap2", "");
			String p_curso = pref.getString("pref_curso", "");
			String p_mensaje = input_msj.getText().toString();
			

			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("name", p_nombre));
			params.add(new BasicNameValuePair("ap1", p_ap1));
			params.add(new BasicNameValuePair("ap2", p_ap2));
			params.add(new BasicNameValuePair("message", p_mensaje));
			params.add(new BasicNameValuePair("curso", p_curso));

			JSONObject json = jsonParser.makeHttpRequest(url_nuevo_mensaje,
					"POST", params);

			try {
				int success = json.getInt(TAG_SUCCESS);

				if (success == 1) {

					Intent i = new Intent(getApplicationContext(), social.serpisapp_alpha.Tablon.class);
					finish();
					startActivity(i);
				} else {
					// fallo al crear el mensaje
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}

			return null;
		}

		protected void onPostExecute(String file_url) {

			pDialog.dismiss();
		}

	}
}
