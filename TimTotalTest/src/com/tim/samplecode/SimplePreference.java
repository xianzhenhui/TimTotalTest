/**
 * 
 */
package com.tim.samplecode;


import com.tim.totaltest.R;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.util.Log;

public class SimplePreference extends PreferenceActivity
{
	@SuppressWarnings("deprecation")
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		// 设置显示参数设置布局。
		//bytim:android 3.0之前使用PreferenceActivity，之后版本为PreferenceFragment
		addPreferencesFromResource(R.xml.preferences);
		//获取Preference的值
		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
		String value = prefs.getString("gender", "unset");
		Log.e("gender:", value);
		
		boolean value2 = prefs.getBoolean("autoSave", true);
		Log.e("autoSave:", ""+value2);
		
	}
}
