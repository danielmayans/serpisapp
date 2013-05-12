package noticias.serpisapp_alpha;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import com.example.serpisapp_alpha.R;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import android.text.Html;
import android.view.KeyEvent;
import android.widget.ImageView;
import android.widget.TextView;

public class Descripcion extends Activity 
{
	
       	
	    @Override
	    public void onCreate(Bundle savedInstanceState) {
	    	super.onCreate(savedInstanceState);
	        setContentView(R.layout.desc_noticia);
//	        ImageView imgDestacada = (ImageView)findViewById(R.id.img_destacada);
	        	Bundle bundle = getIntent().getExtras();
//	        	try {	        		  
//	        		  Bitmap bitmap = BitmapFactory.decodeStream((InputStream)new URL(URLUtil.sacarPagina(bundle.getString("NOTICIA"))).getContent());
//	        		  imgDestacada.setImageBitmap(bitmap); 
//	        		} catch (MalformedURLException e) {
//	        		  e.printStackTrace();
//	        		} catch (IOException e) {
//	        		  e.printStackTrace();
//	        		}
	        	TextView titulo = (TextView)findViewById(R.id.LblTitulo);	        	
				TextView descripcion = (TextView)findViewById(R.id.LblSubTitulo);
				
//				descripcion.setText(URLUtil.limpiarPagina(bundle.getString("NOTICIA")));
				titulo.setText(bundle.getString("TITULO"));
//				descripcion.setText(Html.fromHtml(bundle.getString("NOTICIA")), TextView.BufferType.SPANNABLE);
				descripcion.setText(bundle.getString("NOTICIA"));
				
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