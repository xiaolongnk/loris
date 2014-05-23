package loris;

import com.example.loris.R;



import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class start extends Activity{
	Button begin;
	Button hero;
	Button help;
	Button author;
	Button quit;
	
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.start);
		init();
		dealwithbt();
		
	}
	void init()
	{
		begin=(Button)findViewById(R.id.start_begin);
		hero=(Button)findViewById(R.id.start_hero);
		help=(Button)findViewById(R.id.start_help);
		author=(Button)findViewById(R.id.start_author);
		quit=(Button)findViewById(R.id.start_quit);
	}
	public void dealwithbt() {
		
		
		begin.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(start.this,MainActivity.class);
				startActivityForResult(intent,3);
				start.this.finish();
			}
		});

		hero.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(start.this,hero.class);
				startActivityForResult(intent,3);
				start.this.finish();
			}
		});
		help.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(start.this,help.class);
				startActivityForResult(intent,3);
				start.this.finish();
			}
		});
		author.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				
				Intent intent = new Intent(start.this,author.class);
				startActivityForResult(intent,3);	
				start.this.finish();
				
			}});
		quit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				 new AlertDialog.Builder(start.this).
					setPositiveButton("确定",new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
								
							start.this.finish();
						}
					}).setNegativeButton("取消", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							
						}
					}).setTitle("是否要退出?").create().show();
			}
		});
		
	}

}
