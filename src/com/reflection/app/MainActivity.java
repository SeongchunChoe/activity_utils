package com.reflection.app;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.reflection.dto.ComponentDto;
import com.reflection.utils.ComponentMgr;

public class MainActivity extends Activity {

	TextView result;
	Button btn;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		final View parent = getLayoutInflater().inflate(R.layout.activity_main, null);
		setContentView(parent);
		result = (TextView)findViewById(R.id.result);
		btn = (Button)findViewById(R.id.btn);
		
		btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				ComponentDto dto = (ComponentDto)ComponentMgr.getValues((ViewGroup)parent, ComponentDto.class);
				result.setText(dto.getAa() + ":" + dto.getBb() + ":" + dto.getCc() + ":" + dto.isDd() + ":" + dto.isEe() + ":" + dto.isFf());
			}
		});
	}


}
