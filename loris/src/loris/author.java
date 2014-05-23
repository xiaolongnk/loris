package loris;

import com.example.loris.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;

public class author extends Activity{
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.author);
		
	}
	public boolean onKeyDown(int keyCode, KeyEvent event) {  
	    if (keyCode == KeyEvent.KEYCODE_BACK) {  
	    	Intent intent = new Intent(author.this,start.class);
			startActivity(intent);
			author.this.finish();
	    }  
	    return super.onKeyDown(keyCode, event);  
	}
	private void startActivity(Intent intent, int i) {
		// TODO Auto-generated method stub
		
	}  

}
