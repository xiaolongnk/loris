package loris;

import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

import com.example.loris.R;

import java.text.SimpleDateFormat;   

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Point;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import loris.lorisdb;
import loris.rankitem;
public class MainActivity extends Activity {

	private gameview loris;
	private shownext smallloris;
	private ImageButton bt_left;
	private ImageButton bt_right;
	private ImageButton bt_down;
	private ImageButton bt_change;
	private ImageButton bt_stop;
	private ImageButton bt_quit;
	private EditText input;
	
	private String txt_ok;
	private String txt_onboard;
	private String txt_levelup;
	private String txt_level;
	private String txt_score;
	private String txt_over;
	private String txt_timeformater;
	
	
	private TextView tv_score;
	private TextView tv_level;
	private ArrayList<Point> tempinfo;
	
	private ArrayList<Point> pointmine;

	private static int gamespeed = 600;
	private int gamescore=0;
	private int oldgamespeed=50;
	private int gamestate = 1;
	private boolean stop = false;
	private static final int refresh = 0x7777;
	private static final int makegrade = 0x8888;

	private int gamelevel = 1;
	private int scoreinc = 0;
	private forcast cur;
	private forcast next;
	
	private srat mrat;
	private srat nrat;

	private lorisdb db;
	String background;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ini();
		dealwithbt();
		gamethread();
		
	}

	public void ini() {

		tempinfo = new ArrayList<Point>();
		pointmine = new ArrayList<Point>();
		
		cur = new forcast();
		next = new forcast();
		
		loris = (gameview) this.findViewById(R.id.loris);
		smallloris = (shownext) this.findViewById(R.id.smallloris);
		
		bt_left = (ImageButton) this.findViewById(R.id.bt_left);
		bt_right = (ImageButton) this.findViewById(R.id.bt_right);
		bt_change = (ImageButton) this.findViewById(R.id.bt_change);
		bt_down = (ImageButton) this.findViewById(R.id.bt_down);
		bt_stop = (ImageButton) this.findViewById(R.id.bottom);
		tv_score = (TextView) this.findViewById(R.id.tv_score);
		tv_level = (TextView) this.findViewById(R.id.tv_level);
		bt_quit = (ImageButton) this.findViewById(R.id.bt_quit);
		
		txt_ok = getResources().getString(R.string.txt_ok);
		txt_onboard = getResources().getString(R.string.txt_onboard);
		txt_level = getResources().getString(R.string.txt_level);
		txt_levelup = getResources().getString(R.string.txt_levelup);
		txt_over = getResources().getString(R.string.txt_over);
		txt_score = getResources().getString(R.string.txt_score);
		txt_timeformater = getResources().getString(R.string.txt_timeformater);
		
		tv_level.setText(txt_level+" "+gamelevel);
		tv_score.setText(txt_score+" "+gamescore);
		inipointmine();
	}

	public void dealwithbt() {
			
		bt_change.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				if (mrat.change(loris.board)) {
					tempinfo = mrat.getdata();
					loris.drawpoint(tempinfo);
					loris.invalidate();
				}
			}
		});

		bt_left.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (mrat.moveleft(loris.board)) {
					tempinfo = mrat.getdata();
					loris.drawpoint(tempinfo);
					loris.invalidate();
				}

			}
		});
		bt_right.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (mrat.moveright(loris.board)) {
					tempinfo = mrat.getdata();
					loris.drawpoint(tempinfo);
					loris.invalidate();					
				}
			}
		});
		bt_down.setOnTouchListener(new OnTouchListener(){

			@Override
			public boolean onTouch(View arg0, MotionEvent arg1) {
				
				if(arg1.getAction()==MotionEvent.ACTION_DOWN){
					oldgamespeed = gamespeed;
					gamespeed = 30;
				}
				else if(arg1.getAction() == MotionEvent.ACTION_UP){
					gamespeed = oldgamespeed;
				}
				return false;
			}});
		bt_stop.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				if (gamestate == 1) {
					gamestate = 2;
					bt_stop.setImageResource(R.drawable.bottom);
					bt_left.setEnabled(false);
					bt_right.setEnabled(false);
					bt_change.setEnabled(false);
					bt_down.setEnabled(false);
					//player.pause();
				} else if (gamestate == 2) {
					gamestate = 1;
					bt_stop.setImageResource(R.drawable.pau);
					bt_left.setEnabled(true);
					bt_right.setEnabled(true);
					bt_change.setEnabled(true);
					bt_down.setEnabled(true);
				}
			}
		});
		
		bt_quit.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				stop = true;
				Intent intent = new Intent(MainActivity.this,start.class);
				startActivity(intent);
				MainActivity.this.finish();
			}
			
		});


	}
	void end(final int score)
	{
		db=new lorisdb(this);
		db.openDatabase();
		final Builder builder = new AlertDialog.Builder(this);
		builder.setIcon(R.drawable.debug);
		builder.setTitle(txt_onboard);
		LinearLayout dlg=(LinearLayout) getLayoutInflater().inflate(R.layout.dialog, null);
		builder.setView(dlg);
		input=(EditText)dlg.findViewById(R.id.editText2);
		
		builder.setPositiveButton(txt_ok, new DialogInterface.OnClickListener(){

			@Override
			public void onClick(DialogInterface dialog,
					int which) {
				rankitem user=new rankitem();
                user.name=input.getText().toString();
                SimpleDateFormat formatter = new SimpleDateFormat(txt_timeformater,Locale.CHINA);       
                Date curDate  = new Date(System.currentTimeMillis());    
                String str = formatter.format(curDate);  
                user.date= str;
                user.score=score;
                
                db.insert(user);
				Intent intent = new Intent(MainActivity.this,start.class);
				startActivity(intent);
                MainActivity.this.finish();
			}
		});

		 builder.create().show();
	}

	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void inipointmine() {
		Point t = new Point();
		t.set(4, -1);
		pointmine.add(t);
		t.set(4, -1);
		pointmine.add(t);
		t.set(3, -1);
		pointmine.add(t);
		t.set(4, 0);
		pointmine.add(t);
		t.set(4, -1);
		pointmine.add(t);
		t.set(4, 0);
		pointmine.add(t);
		t.set(4, -1);
		pointmine.add(t);
	}

	public forcast newsrat() {

		forcast temp = new forcast();
		int atype=0,atypetype=1;
		Random random = new Random();
		atype = random.nextInt(8);
		
		// assign the first value for srat;
		if( atype >0 && atype<=pointmine.size()) 
			temp.spoint = pointmine.get(atype-1);
		else{
			atype = 7;
			temp.spoint = pointmine.get(6);
		}
		temp.type = atype;
		
		// assign value for atypetype;
		if(atype==1 || atype==4 || atype==5){
			atypetype = 1 + random.nextInt(2);
		}else if(atype==2 || atype==3 || atype==6){
			atypetype = 1 + random.nextInt(4);
		}else {
			atypetype = 1;
		}
		temp.typetype = atypetype;
		return temp;
	}

	public void gamethread() {
		cur = newsrat();
		next = newsrat();
		mrat = new srat(cur);
		nrat = new srat(next);
		smallloris.drawpoint(nrat.getdata());
		new Thread(new Runnable() {
			public void run() {
				while (true) {
					try {
						if (stop)	break;
						else if (gamestate == 2) {
							
						} 
						else if (mrat.movedown(loris.board)) {
							Message m = new Message();
							m.what = MainActivity.refresh;
							MainActivity.this.myhandler.sendMessage(m);
						} else {
							Message m = new Message();
							m.what = MainActivity.makegrade;
							MainActivity.this.myhandler.sendMessage(m);
						}
						Thread.sleep(gamespeed);

					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			};
		}).start();
	}

	Handler myhandler = new Handler() {
		public void handleMessage(Message msg) {
			if (msg.what == MainActivity.makegrade) {
				
				final int score =loris.makegrade(mrat.getdata());
				if(score == -1) {
					stop = true;
					Toast.makeText(MainActivity.this,txt_over,Toast.LENGTH_LONG).show();
					end(gamescore);
					Thread.currentThread().yield();
				}
				else if(score>0){
					gamescore += score;
					scoreinc += score;
					if(scoreinc>=200){
						gamelevel++;
						scoreinc=0;
						Toast.makeText(MainActivity.this,txt_levelup,
								Toast.LENGTH_SHORT).show();
						tv_level.setText(txt_level+gamelevel);
						if(gamespeed>=300){
							gamespeed -=50;
							oldgamespeed -=50;
						}
					}
					tv_score.setText(txt_score+gamescore);
					loris.invalidate();
				}
				cur.copy(next);
				mrat = new srat(cur);
				next = newsrat();
				nrat = new srat(next);
				smallloris.drawpoint(nrat.getdata());
				
			} else if (msg.what == MainActivity.refresh) {
				tempinfo = mrat.getdata();
				loris.drawpoint(tempinfo);
				loris.invalidate();
			}
			super.handleMessage(msg);
		}
	};
}
