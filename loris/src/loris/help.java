package loris;

import com.example.loris.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;

public class help extends Activity{
		
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.faq);
	}
	public boolean onKeyDown(int keyCode, KeyEvent event) {  
	    if (keyCode == KeyEvent.KEYCODE_BACK) {  
	    	Intent intent = new Intent(help.this,start.class);
			startActivity(intent);
			help.this.finish();
	    }  
	    return super.onKeyDown(keyCode, event);  
	}
}
