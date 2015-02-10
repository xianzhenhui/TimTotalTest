package com.tim.creativework;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tim.totaltest.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class ListViewDemo extends Activity implements OnTouchListener,OnItemClickListener{
	private ListView lv;
	private MyAdapter adapter;
	private List<Map<String, Object>> data;
	private float x , y , upx, upy;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listview_demo);
		lv = (ListView) findViewById(R.id.listview);
		// ��ȡ��Ҫ�󶨵��������õ�data��
		data = getData();
		adapter = new MyAdapter(this);
		lv.setLayoutAnimation(getListAnim()); //bytim:����Ч��
		lv.setOnTouchListener(this);//bytim:����ɾ��Ч��
	//	lv.setOnItemClickListener(this);//bytim:���ɾ��
		lv.setCacheColorHint(Color.TRANSPARENT); //bytim:���ñ���͸���� 
		lv.setAdapter(adapter);
	}

	private LayoutAnimationController getListAnim() {
		AnimationSet set = new AnimationSet(true);
		Animation animation = new AlphaAnimation(0.0f, 1.0f);
		animation.setDuration(150);
		set.addAnimation(animation);

		animation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,
				Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
				-1.0f, Animation.RELATIVE_TO_SELF, 0.0f);
		animation.setDuration(200);
		set.addAnimation(animation);
		LayoutAnimationController controller = new LayoutAnimationController(
				set, 0.5f);
		return controller;
	}
		  
	private List<Map<String, Object>> getData() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map;
		for (int i = 0; i < 10; i++) {
			map = new HashMap<String, Object>();
			map.put("img", R.drawable.ic_launcher);
			map.put("title", "ListViewʵ��"+i);
			map.put("info", "�������Ч��������ɾ��Ч����");
			list.add(map);
		}
		return list;
	}

	// ViewHolder��̬��
	static class ViewHolder {
		public ImageView img;
		public TextView title;
		public TextView info;
	}

	public class MyAdapter extends BaseAdapter {
		private LayoutInflater mInflater = null;

		private MyAdapter(Context context) {
			// ����context�����ļ��ز��֣��������Demo17Activity������this
			this.mInflater = LayoutInflater.from(context);
		}

		@Override
		public int getCount() {
			// How many items are in the data set represented by this Adapter.
			// �ڴ�������������������ݼ��е���Ŀ��
			return data.size();
		}

		@Override
		public Object getItem(int position) {
			// Get the data item associated with the specified position in the
			// data set.
			// ��ȡ���ݼ�����ָ��������Ӧ��������
			return position;
		}

		@Override
		public long getItemId(int position) {
			// Get the row id associated with the specified position in the
			// list.
			// ��ȡ���б�����ָ��������Ӧ����id
			return position;
		}

		// Get a View that displays the data at the specified position in the
		// data set.
		// ��ȡһ�������ݼ���ָ����������ͼ����ʾ����
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder = null;
			// �������convertViewΪ�գ�����Ҫ����View
			if (convertView == null) {
				holder = new ViewHolder();
				// �����Զ����Item���ּ��ز���
				convertView = mInflater.inflate(R.layout.listview_item, null);
				holder.img = (ImageView) convertView.findViewById(R.id.img);
				holder.title = (TextView) convertView.findViewById(R.id.titleshow);
				holder.info = (TextView) convertView.findViewById(R.id.info);
				// �����úõĲ��ֱ��浽�����У�������������Tag��Ա���淽��ȡ��Tag
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}
			holder.img.setBackgroundResource((Integer) data.get(position).get("img"));
			holder.title.setText((String) data.get(position).get("title"));
			holder.info.setText((String) data.get(position).get("info"));
			return convertView;
		}

	}
	
	@Override  
	public boolean onTouch(View v, MotionEvent event) {  
	          
	     if (event.getAction() == MotionEvent.ACTION_DOWN) {   
	            x = event.getX();   
	            y = event.getY();               
	        }   
	        if (event.getAction() == MotionEvent.ACTION_UP) {   
	            upx = event.getX();   
	            upy = event.getY();   
	            int position1 = ((ListView) v).pointToPosition((int) x, (int) y);   
	            int position2 = ((ListView) v).pointToPosition((int) upx,(int) upy);               
	            int FirstVisiblePosition = lv.getFirstVisiblePosition();               
	            if (position1 == position2 && (upx - x) > 80) { //bytim���������º��ɿ�����һ��item �� �����������80
	                View view = ((ListView) v).getChildAt(position1);                   
	                if (view == null) {                    
	                 view = ((ListView) v).getChildAt(position1 - FirstVisiblePosition);  
	                }                   
	                removeListItem(view, position1);   
	            }   
	        }   
	      
	    return false;  
	} 
	
	@Override  
    public void onItemClick(AdapterView<?> parent, View view, final int position,  
            long id) {  
        new AlertDialog.Builder(ListViewDemo.this)
        .setMessage("��ͼɾ�� "+((ViewHolder)view.getTag()).title.getText()+" ��?")//bytim:((ViewHolder)view.getTag()).title.getText()
        .setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {  
            public void onClick(DialogInterface dialog, int whichButton) {  
                data.remove(position);  
                adapter.notifyDataSetChanged();  
            }
        })
        .setNegativeButton("ȡ��", new DialogInterface.OnClickListener() {  
            public void onClick(DialogInterface dialog, int which) {  
                System.out.println("ȡ��ɾ��Item");
            }  
        }).show();  
    }
	
	protected void removeListItem(View rowView, final int positon) {        
        final Animation animation = (Animation) AnimationUtils.loadAnimation(rowView.getContext(), R.anim.item_anim); 
        animation.setAnimationListener(new AnimationListener() { 
            public void onAnimationStart(Animation animation) {} 
            public void onAnimationRepeat(Animation animation) {} 
            public void onAnimationEnd(Animation animation) { 
            	data.remove(positon); 
            	adapter.notifyDataSetChanged(); 
                animation.cancel(); 
            } 
        }); 
         
        rowView.startAnimation(animation); 
    }

}