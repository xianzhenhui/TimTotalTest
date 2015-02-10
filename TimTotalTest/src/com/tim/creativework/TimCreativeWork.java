package com.tim.creativework;

import com.tim.totaltest.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class TimCreativeWork extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tim_creative_work);
		
		initViews();
	}

	private void initViews() {
		Button phoneInfoBtn = (Button) findViewById(R.id.phoneinfo);
		Button customEdittextBtn = (Button) findViewById(R.id.customedittext);
		Button viewstubdemoBtn = (Button) findViewById(R.id.viewstubdemo);
		Button listviewdemoBtn = (Button) findViewById(R.id.listviewdemo);
		Button viewswitcherBtn = (Button) findViewById(R.id.viewswitcherdemo);
		Button launcherpackagenameBtn = (Button) findViewById(R.id.launcherpackagename);
		
		OnClickListener btnListener = new OnClickListener() {
			@Override
			public void onClick(View v) {

				switch (v.getId()) {
				
				case R.id.phoneinfo:
					Intent infoIntent = new Intent(TimCreativeWork.this, PhoneInfo.class);
					startActivity(infoIntent);
					break;
					
				case R.id.customedittext:
					Intent editIntent = new Intent(TimCreativeWork.this, CustomEditText.class);
					startActivity(editIntent);
					break;
					
				case R.id.viewstubdemo:
					Intent viewstubIntent = new Intent(TimCreativeWork.this, ViewStubDemo.class);
					startActivity(viewstubIntent);
					break;
					
				case R.id.listviewdemo:
					Intent listviewIntent = new Intent(TimCreativeWork.this, ListViewDemo.class);
					startActivity(listviewIntent);
					break;
					
				case R.id.viewswitcherdemo:
					Intent viewswitcherIntent = new Intent(TimCreativeWork.this, ViewSwitcherDemo.class);
					startActivity(viewswitcherIntent);
					break;
					
				case R.id.launcherpackagename:
					Intent launcherpackagenameIntent = new Intent(TimCreativeWork.this, LauncherPackageName.class);
					startActivity(launcherpackagenameIntent);
					break;
//				case R.id.TODO:
//					//TODO
//					break;
				}
			}
		};

		phoneInfoBtn.setOnClickListener(btnListener);
		customEdittextBtn.setOnClickListener(btnListener);
		viewstubdemoBtn.setOnClickListener(btnListener);
		listviewdemoBtn.setOnClickListener(btnListener);
		viewswitcherBtn.setOnClickListener(btnListener);
		launcherpackagenameBtn.setOnClickListener(btnListener);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.tim_test_temp, menu);
		return true;
	}

}
