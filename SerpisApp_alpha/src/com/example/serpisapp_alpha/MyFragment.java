package com.example.serpisapp_alpha;

import com.example.serpisapp_alpha.R;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class MyFragment extends Fragment{
	
	int mCurrentPage;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		Bundle data = getArguments();
		
		mCurrentPage = data.getInt("current_page", 0);
		
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		
		if(mCurrentPage==1){
			
			View v = inflater.inflate(R.layout.bienvenida, container,false);	
			
			Typeface face=Typeface.createFromAsset(getActivity().getAssets(),"fonts/ByPeopleHandwritten.ttf");
			
			TextView t_titulo = (TextView) v.findViewById(R.id.text_titulo);
			TextView t_subtitulo = (TextView) v.findViewById(R.id.text_subtitulo);
			TextView t_info = (TextView)v.findViewById(R.id.text_informacion);
			
			
						
			t_titulo.setTypeface(face);
			t_subtitulo.setTypeface(face);
			t_info.setTypeface(face);
			
			
			
		return v;
		}else{
			
			View v = inflater.inflate(R.layout.secretaria, container,false);	
			
			Typeface arial=Typeface.createFromAsset(getActivity().getAssets(),"fonts/arial.ttf");
			
			TextView t_face1 = (TextView) v.findViewById(R.id.textView1);
			TextView t_telf1 = (TextView) v.findViewById(R.id.textView2);
			TextView t_email1 = (TextView) v.findViewById(R.id.textView3);
			TextView t_web1 = (TextView) v.findViewById(R.id.textView4);
			TextView t_face2 = (TextView) v.findViewById(R.id.textView5);
			TextView t_telf2 = (TextView) v.findViewById(R.id.TextView01);
			TextView t_email2 = (TextView) v.findViewById(R.id.TextView02);
			TextView t_web2 = (TextView) v.findViewById(R.id.TextView03);
			
			t_face1.setTypeface(arial);
			t_telf1.setTypeface(arial);
			t_email1.setTypeface(arial);
			t_web1.setTypeface(arial);
			t_face2.setTypeface(arial);
			t_telf2.setTypeface(arial);
			t_email2.setTypeface(arial);
			t_web2.setTypeface(arial);
			
		return v;} 		
	}
}
