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
        AssetManager mgr1=getAssets();//得到AssetManager
        AssetManager mgr2=getAssets();//得到AssetManager

        fishTF=Typeface.createFromAsset(mgr1, "fonts/viewstub_demo_fish.TTF");//根据路径得到Typeface
        cartoonTF=Typeface.createFromAsset(mgr2, "fonts/viewstub_demo_cartoon.TTF");//根据路径得到Typeface
 //       show.setTypeface(cartoonTF);//设置字体
        
        btn = (Button) findViewById(R.id.btn);  
        btn.setOnClickListener(new OnClickListener() {  
  
            @SuppressLint("NewApi")
			@Override  
            public void onClick(View v) { 
            	
            	if (viewStub == null) {
            		viewStub = (ViewStub) findViewById(R.id.mystub);  
            		view = viewStub.inflate(); //bytim:展开视图
            		btn.setText("隐藏视图");
            		show.setTypeface(cartoonTF);
				}else if (view != null ) {
					if (View.GONE == view.getVisibility()){
						view.setVisibility(View.VISIBLE);
						btn.setText("隐藏视图");
						show.setTypeface(cartoonTF);
					}else {
						view.setVisibility(View.GONE);
						btn.setText("显示视图");
						show.setTypeface(fishTF);
					}
				}
            }  
        });  
    }  
}  