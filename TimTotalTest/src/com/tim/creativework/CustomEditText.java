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
		spanString.setSpan(new BackgroundColorSpan(getResources().getColor(R.color.greenyellow)), 0, 2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE); //bytim:��ɫ����  
		spanString.setSpan(new BackgroundColorSpan(getResources().getColor(R.color.greenyellow)), 13, 15, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE); //bytim:��ɫ����
		spanString.setSpan(new UnderlineSpan(), 2, 5,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE); //bytim:�»���  
		spanString.setSpan(new UnderlineSpan(), 15, 18,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE); //bytim:�»���  
		spanString.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), 19, 21, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE); //bytim:����  
		spanString.setSpan(new StyleSpan(android.graphics.Typeface.ITALIC), 5, 13, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE); //bytim:б�壬���Ĳ�֧��б��
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
                showView.append("�����������һ��ͨ�ù������������е�δ֪�� class���Ǿ��÷��䣬" +
                		"�� Java Beans �� Property ��ʽ�������ֻ����Ϊ����Ϊ�б�Ҫ�� class ������" +
                		"������ϸ�� toString ���̣��Ǿ���Ϊ���ṩһ�� toString() ���������ǵ��� Object " +
                		"��̳й����� toString������������δ�ӡListView�Ľ����������:����֧���ؼ���:.NET��" +
                		"��ӡ��ListView��ӡ����ά������ ��:2005-12-12 �����ܶ�ʹ��MIS���ʴ�ӡͨ��������������δ�ӡListView.�����������һ��ͨ�ù������������е�δ֪�� class���Ǿ��÷��䣬" +
                        "�� Java Beans �� Property ��ʽ�������ֻ����Ϊ����Ϊ�б�Ҫ�� class ������" +
                        "������ϸ�� toString ���̣��Ǿ���Ϊ���ṩһ�� toString() ���������ǵ��� Object " +
                        "��̳й����� toString������������δ�ӡListView�Ľ����������:����֧���ؼ���:.NET��" +
                        "��ӡ��ListView��ӡ����ά������ ��:2005-12-12 �����ܶ�ʹ��MIS���ʴ�ӡͨ��������������δ�ӡListView.�����������һ��ͨ�ù������������е�δ֪�� class���Ǿ��÷��䣬" +
                        "�� Java Beans �� Property ��ʽ�������ֻ����Ϊ����Ϊ�б�Ҫ�� class ������" +
                        "������ϸ�� toString ���̣��Ǿ���Ϊ���ṩһ�� toString() ���������ǵ��� Object " +
                        "��̳й����� toString������������δ�ӡListView�Ľ����������:����֧���ؼ���:.NET��" +
                        "��ӡ��ListView��ӡ����ά������ ��:2005-12-12 �����ܶ�ʹ��MIS���ʴ�ӡͨ��������������δ�ӡListView");
                dialog.setView(layout);
                dialog.show();
            }
        });
	}

}
