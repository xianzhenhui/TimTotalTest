package com.tim.creativework;

import com.tim.totaltest.R;

import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.graphics.Color;

public class CustomEditText extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.custom_edittext);
		
		TextView showText = (TextView)findViewById(R.id.showtext);  
		
		SpannableString spanString = new SpannableString(showText.getText());  
		spanString.setSpan(new BackgroundColorSpan(getResources().getColor(R.color.greenyellow)), 0, 2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE); //bytim:红色高亮  
		spanString.setSpan(new BackgroundColorSpan(getResources().getColor(R.color.greenyellow)), 13, 15, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE); //bytim:红色高亮
		spanString.setSpan(new UnderlineSpan(), 2, 5,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE); //bytim:下划线  
		spanString.setSpan(new UnderlineSpan(), 15, 18,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE); //bytim:下划线  
		spanString.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), 19, 21, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE); //bytim:粗体  
		spanString.setSpan(new StyleSpan(android.graphics.Typeface.ITALIC), 5, 13, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE); //bytim:斜体，中文不支持斜体
		spanString.setSpan(new ForegroundColorSpan(Color.RED), 5, 13, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		showText.setText(spanString); 
		
		Button showDialog = (Button)findViewById(R.id.custom_dialog);
		showDialog.setOnClickListener(new View.OnClickListener() {
            
            @Override
            public void onClick(View v) {
                Builder dialog = new AlertDialog.Builder(CustomEditText.this);
                LayoutInflater inflater = (LayoutInflater) CustomEditText.this
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.ota_dialog, null);
                TextView showView = (TextView) layout.findViewById(R.id.idTvOtaDLgMsg);
                showView.append("如果你是想做一个通用工具来处理所有的未知的 class，那就用反射，" +
                		"或 Java Beans 的 Property 方式，如果你只是想为你认为有必要的 class 引入这" +
                		"个更详细的 toString 过程，那就请为它提供一个 toString() 方法，覆盖掉从 Object " +
                		"类继承过来的 toString方法。关于如何打印ListView的解决方法作者:长江支流关键字:.NET、" +
                		"打印、ListView打印、二维数据日 期:2005-12-12 经常很多使用MIS金质打印通的网友来信问如何打印ListView.如果你是想做一个通用工具来处理所有的未知的 class，那就用反射，" +
                        "或 Java Beans 的 Property 方式，如果你只是想为你认为有必要的 class 引入这" +
                        "个更详细的 toString 过程，那就请为它提供一个 toString() 方法，覆盖掉从 Object " +
                        "类继承过来的 toString方法。关于如何打印ListView的解决方法作者:长江支流关键字:.NET、" +
                        "打印、ListView打印、二维数据日 期:2005-12-12 经常很多使用MIS金质打印通的网友来信问如何打印ListView.如果你是想做一个通用工具来处理所有的未知的 class，那就用反射，" +
                        "或 Java Beans 的 Property 方式，如果你只是想为你认为有必要的 class 引入这" +
                        "个更详细的 toString 过程，那就请为它提供一个 toString() 方法，覆盖掉从 Object " +
                        "类继承过来的 toString方法。关于如何打印ListView的解决方法作者:长江支流关键字:.NET、" +
                        "打印、ListView打印、二维数据日 期:2005-12-12 经常很多使用MIS金质打印通的网友来信问如何打印ListView");
                dialog.setView(layout);
                dialog.show();
            }
        });
	}

}
