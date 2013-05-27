package noticias.serpisapp_alpha;

import com.example.serpisapp_alpha.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.TextView;

public class Descripcion extends Activity 
{
	WebView webview;
	      	
	    @Override
	    public void onCreate(Bundle savedInstanceState) {
	    	super.onCreate(savedInstanceState);
	        setContentView(R.layout.desc_noticia);

	        Bundle bundle = getIntent().getExtras();
	        	TextView titulo = (TextView)findViewById(R.id.LblTitulo);	        	
				TextView descripcion = (TextView)findViewById(R.id.LblSubTitulo);
				TextView link = (TextView)findViewById(R.id.text_link);

				titulo.setText(bundle.getString("TITULO"));
//				descripcion.setText(Html.fromHtml(bundle.getString("NOTICIA")), TextView.BufferType.SPANNABLE);
				descripcion.setText(bundle.getString("NOTICIA"));
				link.setText(bundle.getString("LINK"));
				
	    }
	    public static class URLUtil {
	    	public static String sacarPagina(String url) {
	    	
	    	String urlTratada = url;
	    	if (url.toUpperCase().startsWith("HTTP://")) {
	    	urlTratada = url.substring(7);
	    	}
	    	if(urlTratada.indexOf('/') == -1){
	    	return null;
	    	}
	    	while (urlTratada.indexOf('/') != -1) {
	    	urlTratada =
	    	urlTratada.substring(urlTratada.indexOf('/') + 1,
	    	urlTratada.length());
	    	}
	    	if (urlTratada.indexOf('?') != -1) {
	    	urlTratada = urlTratada.substring(0,
	    	urlTratada.indexOf('?'));
	    	}
	    	if (urlTratada.equals(""))
	    	return null;
	    	
	    	String link=url.substring(0,url.length()-urlTratada.length()-1);
	    	return link;

	    	}
	    	public static String limpiarPagina(String url) {
		    	
		    	String urlTratada = url;
		    	if (url.toUpperCase().startsWith("HTTP://")) {
		    	urlTratada = url.substring(7);
		    	}
		    	if(urlTratada.indexOf('/') == -1){
		    	return null;
		    	}
		    	while (urlTratada.indexOf('/') != -1) {
		    	urlTratada =
		    	urlTratada.substring(urlTratada.indexOf('/') + 1,
		    	urlTratada.length());
		    	}
		    	if (urlTratada.indexOf('?') != -1) {
		    	urlTratada = urlTratada.substring(0,
		    	urlTratada.indexOf('?'));
		    	}
		    	if (urlTratada.equals(""))
		    	return null;
		    	
		    	
		    	return urlTratada;

		    	}
 	    }
	    
}