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
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NuevoMensaje extends Activity {

	private ProgressDialog pDialog;

	JSONParser jsonParser = new JSONParser();
	EditText text_alum;
	EditText input_msj;

	private static String url_nuevo_mensaje = "http://www.proyectawordpress.com/json/nuevo_mensaje.php";
	private static final String TAG_SUCCESS = "success";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.new_mensj);

		text_alum = (EditText) findViewById(R.id.text_alum);
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
			String alumno = text_alum.getText().toString();
			String mensaje = input_msj.getText().toString();


			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("name", alumno));
			params.add(new BasicNameValuePair("message", mensaje));

			JSONObject json = jsonParser.makeHttpRequest(url_nuevo_mensaje,
					"POST", params);

			try {
				int success = json.getInt(TAG_SUCCESS);

				if (success == 1) {

					Intent i = new Intent(getApplicationContext(), Tablon.class);
					startActivity(i);

					finish();
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
