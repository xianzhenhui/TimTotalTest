package com.tim.creativework;


import com.tim.totaltest.R;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.os.Bundle;  
import android.view.View;  
import android.view.View.OnClickListener;  
import android.view.ViewStub;  
import android.widget.Button;  
import android.widget.TextView;
  
public class ViewStubDemo extends Activity {  
    private Button btn; 
    private View view;
    private TextView show;
    private Typeface fishTF;
    private Typeface cartoonTF;
    private ViewStub viewStub;  
  
    @Override  
    public void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);  
        setContentView(R.layout.viewstub_demo);  
        
        show = (TextView) findViewById(R.id.showtext);
        AssetManager mgr1=getAssets();//�õ�AssetManager
        AssetManager mgr2=getAssets();//�õ�AssetManager

        fishTF=Typeface.createFromAsset(mgr1, "fonts/viewstub_demo_fish.TTF");//����·���õ�Typeface
        cartoonTF=Typeface.createFromAsset(mgr2, "fonts/viewstub_demo_cartoon.TTF");//����·���õ�Typeface
 //       show.setTypeface(cartoonTF);//��������
        
        btn = (Button) findViewById(R.id.btn);  
        btn.setOnClickListener(new OnClickListener() {  
  
            @SuppressLint("NewApi")
			@Override  
            public void onClick(View v) { 
            	
            	if (viewStub == null) {
            		viewStub = (ViewStub) findViewById(R.id.mystub);  
            		view = viewStub.inflate(); //bytim:չ����ͼ
            		btn.setText("������ͼ");
            		show.setTypeface(cartoonTF);
				}else if (view != null ) {
					if (View.GONE == view.getVisibility()){
						view.setVisibility(View.VISIBLE);
						btn.setText("������ͼ");
						show.setTypeface(cartoonTF);
					}else {
						view.setVisibility(View.GONE);
						btn.setText("��ʾ��ͼ");
						show.setTypeface(fishTF);
					}
				}
            }  
        });  
    }  
}  