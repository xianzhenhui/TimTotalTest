package com.tim.totaltest;

import com.tim.creativework.TimCreativeWork;
import com.tim.samplecode.SimplePreference;

import android.os.Bundle;
import android.app.LauncherActivity;
import android.content.Intent;
import android.widget.ArrayAdapter;

public class TestTemp extends LauncherActivity {

	// ��������Activity��Ӧ��ʵ����
	Class<?>[] clazzs = { TimCreativeWork.class, SimplePreference.class };

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, getResources()
						.getStringArray(R.array.item_name_list));
		// ���øô�����ʾ���б������Adapter
		setListAdapter(adapter);
	}

	// �����б���������ָ��Activity��Ӧ��Intent
	@Override
	public Intent intentForPosition(int position) {
		return new Intent(TestTemp.this, clazzs[position]);
	}

}
