package com.tim.totaltest;

import com.tim.creativework.TimCreativeWork;
import com.tim.samplecode.SimplePreference;

import android.os.Bundle;
import android.app.LauncherActivity;
import android.content.Intent;
import android.widget.ArrayAdapter;

public class TestTemp extends LauncherActivity {

	// 定义两个Activity对应的实现类
	Class<?>[] clazzs = { TimCreativeWork.class, SimplePreference.class };

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, getResources()
						.getStringArray(R.array.item_name_list));
		// 设置该窗口显示的列表所需的Adapter
		setListAdapter(adapter);
	}

	// 根据列表项来返回指定Activity对应的Intent
	@Override
	public Intent intentForPosition(int position) {
		return new Intent(TestTemp.this, clazzs[position]);
	}

}
