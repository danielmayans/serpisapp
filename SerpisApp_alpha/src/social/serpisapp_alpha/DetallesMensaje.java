package social.serpisapp_alpha;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
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

public class DetallesMensaje extends Activity {

	EditText txtName;
	EditText txtMsj;
	EditText txtCreatedAt;
	Button btnAtras;
	String pid;

	private ProgressDialog pDialog;

	JSONParser jsonParser = new JSONParser();

	private static final String url_detalles_mensaje = "http://www.proyectawordpress.com/json/detalles_mensaje.php";

	private static final String TAG_SUCCESS = "success";
	private static final String TAG_MESSAGE = "message";
	private static final String TAG_PID = "pid";
	private static final String TAG_NAME = "name";
	private static final String TAG_DESCRIPTION = "description";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.detalles_mensaje);

		btnAtras = (Button) findViewById(R.id.btnAtras);

		Intent i = getIntent();
		
		pid = i.getStringExtra(TAG_PID);

		new GetProductDetails().execute();

		btnAtras.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(getApplicationContext(),social.serpisapp_alpha.Tablon.class);
				finish();
				startActivity(intent);
			}
		});

	}

	/**
	 * Background Async Task
	 * */
	class GetProductDetails extends AsyncTask<String, String, String> {

		/**
		 * Progress Dialog
		 * */
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(DetallesMensaje.this);
			pDialog.setMessage("Cargando mensaje. Por favor espere...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(true);
			pDialog.show();
		}

		protected String doInBackground(String... params) {


			runOnUiThread(new Runnable() {
				public void run() {

					int success;
					try {

						List<NameValuePair> params = new ArrayList<NameValuePair>();
						params.add(new BasicNameValuePair("pid", pid));

						JSONObject json = jsonParser.makeHttpRequest(
								url_detalles_mensaje, "GET", params);

						success = json.getInt(TAG_SUCCESS);
						if (success == 1) {

							JSONArray productObj = json
									.getJSONArray(TAG_MESSAGE);
							
							JSONObject product = productObj.getJSONObject(0);

							txtName = (EditText) findViewById(R.id.inputName);
							txtMsj = (EditText) findViewById(R.id.inputDesc);

							txtName.setText(product.getString(TAG_NAME));
							txtMsj.setText(product.getString(TAG_DESCRIPTION));

						}else{

						}
					} catch (JSONException e) {
						e.printStackTrace();
					}
				}
			});

			return null;
		}

		protected void onPostExecute(String file_url) {

			pDialog.dismiss();
		}
	}

}
