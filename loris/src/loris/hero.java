package loris;

import java.util.ArrayList;

import com.example.loris.R;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import loris.lorisdb;
import loris.rankitem;
public class hero extends Activity{
	
	
	
	private ListView herolist;
	ArrayList list;
	lorisdb helper;
	SimpleAdapter adapter;
	Button delete;
	Button quit;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.hero);
		helper = new lorisdb(this);
        helper.openDatabase(); 
        list = helper.getUsers();
        herolist = (ListView)findViewById(R.id.hero_list); 
        adapter = new SimpleAdapter(this, 
					list, 
					R.layout.listitem, 
					new String[]{"name","score","date"}, 
					new int[]{R.id.tv_name,R.id.tv_grade,R.id.tv_date});

        herolist.setAdapter(adapter);
        herolist.setClickable(false);
        //herolist.setEnabled(false);
        delete=(Button)findViewById(R.id.hero_delete);
        quit=(Button)findViewById(R.id.quit);
        delete.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				helper.deleteAll();
				refresh();
					
				}
			
			
		});
        quit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(hero.this,start.class);
				startActivity(intent);	
				hero.this.finish();
					
				}
			
		});
        
	}
	void refresh()
	{
		 list = helper.getUsers();
		 adapter = new SimpleAdapter(this, 
					list, 
					R.layout.listitem, 
					new String[]{"name","score","date"}, 
					new int[]{R.id.tv_name,R.id.tv_grade,R.id.tv_date});

		 herolist.setAdapter(adapter);
	}
}
