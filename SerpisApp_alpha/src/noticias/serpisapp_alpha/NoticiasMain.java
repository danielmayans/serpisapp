package noticias.serpisapp_alpha;

import java.util.List;


import com.example.serpisapp_alpha.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

public class NoticiasMain extends Activity 
{
    
        //Tratamiento del array de noticias
		public class Titular 
		{
			private String titulo;
			private String subtitulo;

			public Titular(String tit, String sub){
				titulo = tit;
				subtitulo = sub;
			}
			
			
			public String getTitulo(){
				return titulo;
			}
			
			public String getSubtitulo(){
				return subtitulo;
			}
			
			public String toString(){
				return titulo+" "+subtitulo;
			}
		}
		
		RssParserSax saxparser = new RssParserSax("http://www.institutoserpis.org/feed/");
		
		final List<Noticia> noticias = saxparser.parse();
		Titular[] datos = new Titular[]{
			new Titular(noticias.get(0).getTitulo(),noticias.get(0).getFecha()),
			new Titular(noticias.get(1).getTitulo(),noticias.get(1).getFecha()),
			new Titular(noticias.get(2).getTitulo(),noticias.get(2).getFecha()),
			new Titular(noticias.get(3).getTitulo(),noticias.get(3).getFecha()),
			new Titular(noticias.get(4).getTitulo(),noticias.get(4).getFecha()),
			new Titular(noticias.get(5).getTitulo(),noticias.get(5).getFecha())};
		
	    @Override
	    public void onCreate(Bundle savedInstanceState) {
	    	super.onCreate(savedInstanceState);
	        setContentView(R.layout.noticias);
	        
			
	        AdaptadorTitulares adaptador = 
	        	new AdaptadorTitulares(this);
	        
	        final ListView ListNoticias = (ListView)findViewById(R.id.ListNoticias);
	        
	        ListNoticias.setAdapter(adaptador);
	        
	        
	    }
	    
	    class AdaptadorTitulares extends ArrayAdapter<Object> {
	    	
	    	Activity context;
	    	
	    	AdaptadorTitulares(Activity context) {
	    		super(context, R.layout.titular, datos);
	    		this.context = context;
	    		
	    	}
	    	
	    	
			public View getView(final int position, View convertView, ViewGroup parent) {
				
				LayoutInflater inflater = context.getLayoutInflater();
				View item = inflater.inflate(R.layout.titular, null);
				
				final TextView lblTitulo = (TextView)item.findViewById(R.id.LblTitulo);
				lblTitulo.setText(datos[position].getTitulo());
				
				final TextView lblSubtitulo = (TextView)item.findViewById(R.id.LblSubTitulo);
				lblSubtitulo.setText(datos[position].getSubtitulo());
				
				item.setOnClickListener(new OnClickListener() {
				    @Override
				    public void onClick(View arg0) {
				        Intent intent = new Intent(NoticiasMain.this, Descripcion.class);
				 
				        Bundle bundle = new Bundle();
				        bundle.putString("TITULO", noticias.get(position).getTitulo());
				        bundle.putString("NOTICIA", noticias.get(position).getDescripcion());
				        bundle.putString("LINK", noticias.get(position).getLink());
				        intent.putExtras(bundle);
				 
				        startActivity(intent);
				    }
				});
				
				return(item);
			}
			
	  }
	    public boolean onKeyDown(int keyCode, KeyEvent event) {
			 
			 if ((keyCode == KeyEvent.KEYCODE_BACK)) {
				 Intent intent = new Intent(getApplicationContext(),com.example.serpisapp_alpha.MainActivity.class);
				 finish();
				 startActivity(intent);
		        }
			 return false;
		 }
 }