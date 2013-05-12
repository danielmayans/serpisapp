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
			Typeface face_bold=Typeface.createFromAsset(getActivity().getAssets(),"fonts/ByPeopleBold.ttf");
			
			TextView t_titulo = (TextView) v.findViewById(R.id.text_titulo);
			TextView t_subtitulo = (TextView) v.findViewById(R.id.text_subtitulo);
			
			t_titulo.setTypeface(face_bold);
			t_subtitulo.setTypeface(face);
			
			
		return v;
		}else{
			
			View v = inflater.inflate(R.layout.secretaria, container,false);	
			
			Typeface face_bold=Typeface.createFromAsset(getActivity().getAssets(),"fonts/ByPeopleBold.ttf");
			
			
			TextView titulo_facebook = (TextView)v.findViewById(R.id.textView6);
			TextView titulo_telefono = (TextView)v.findViewById(R.id.textView7);
			TextView titulo_email = (TextView)v.findViewById(R.id.textView8);
			TextView titulo_web = (TextView)v.findViewById(R.id.textView9);
			
			titulo_facebook.setTypeface(face_bold);
			titulo_telefono.setTypeface(face_bold);
			titulo_email.setTypeface(face_bold);
			titulo_web.setTypeface(face_bold);
			
		return v;} 		
	}
}
