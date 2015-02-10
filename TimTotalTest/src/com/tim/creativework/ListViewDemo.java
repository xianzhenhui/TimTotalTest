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
		// 获取将要绑定的数据设置到data中
		data = getData();
		adapter = new MyAdapter(this);
		lv.setLayoutAnimation(getListAnim()); //bytim:飞入效果
		lv.setOnTouchListener(this);//bytim:滑动删除效果
	//	lv.setOnItemClickListener(this);//bytim:点击删除
		lv.setCacheColorHint(Color.TRANSPARENT); //bytim:设置背景透明度 
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
			map.put("title", "ListView实例"+i);
			map.put("info", "逐个飞入效果，滑动删除效果…");
			list.add(map);
		}
		return list;
	}

	// ViewHolder静态类
	static class ViewHolder {
		public ImageView img;
		public TextView title;
		public TextView info;
	}

	public class MyAdapter extends BaseAdapter {
		private LayoutInflater mInflater = null;

		private MyAdapter(Context context) {
			// 根据context上下文加载布局，这里的是Demo17Activity本身，即this
			this.mInflater = LayoutInflater.from(context);
		}

		@Override
		public int getCount() {
			// How many items are in the data set represented by this Adapter.
			// 在此适配器中所代表的数据集中的条目数
			return data.size();
		}

		@Override
		public Object getItem(int position) {
			// Get the data item associated with the specified position in the
			// data set.
			// 获取数据集中与指定索引对应的数据项
			return position;
		}

		@Override
		public long getItemId(int position) {
			// Get the row id associated with the specified position in the
			// list.
			// 获取在列表中与指定索引对应的行id
			return position;
		}

		// Get a View that displays the data at the specified position in the
		// data set.
		// 获取一个在数据集中指定索引的视图来显示数据
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder = null;
			// 如果缓存convertView为空，则需要创建View
			if (convertView == null) {
				holder = new ViewHolder();
				// 根据自定义的Item布局加载布局
				convertView = mInflater.inflate(R.layout.listview_item, null);
				holder.img = (ImageView) convertView.findViewById(R.id.img);
				holder.title = (TextView) convertView.findViewById(R.id.titleshow);
				holder.info = (TextView) convertView.findViewById(R.id.info);
				// 将设置好的布局保存到缓存中，并将其设置在Tag里，以便后面方便取出Tag
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
	            if (position1 == position2 && (upx - x) > 80) { //bytim：都在摁下和松开都在一个item 且 滑动距离大于80
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
        .setMessage("试图删除 "+((ViewHolder)view.getTag()).title.getText()+" 吗?")//bytim:((ViewHolder)view.getTag()).title.getText()
        .setPositiveButton("确定", new DialogInterface.OnClickListener() {  
            public void onClick(DialogInterface dialog, int whichButton) {  
                data.remove(position);  
                adapter.notifyDataSetChanged();  
            }
        })
        .setNegativeButton("取消", new DialogInterface.OnClickListener() {  
            public void onClick(DialogInterface dialog, int which) {  
                System.out.println("取消删除Item");
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