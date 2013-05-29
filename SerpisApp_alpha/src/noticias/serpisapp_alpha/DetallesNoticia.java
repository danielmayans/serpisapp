package noticias.serpisapp_alpha;

import com.example.serpisapp_alpha.R;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class DetallesNoticia extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.detalles_noticia);
		TextView detailsTitle = (TextView)findViewById(R.id.detailstitle);
		TextView detailsDescription = (TextView)findViewById(R.id.detailsdescription);
		TextView detailsPubdate = (TextView)findViewById(R.id.detailspubdate);
		TextView detailsLink = (TextView)findViewById(R.id.detailslink);
		
		Bundle bundle = this.getIntent().getExtras();
        
        detailsTitle.setText(bundle.getString("keyTitle"));
        detailsDescription.setText(bundle.getString("keyDescription"));
        detailsPubdate.setText(bundle.getString("keyPubdate"));
        detailsLink.setText(bundle.getString("keyLink"));
        
	}

}
