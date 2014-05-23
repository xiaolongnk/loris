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
import loris.sound;
import loris.lorisdb;
import loris.rankitem;
public class MainActivity extends Activity {

	public gameview loris;
	public shownext smallloris;
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
	
	
	
	private TextView tv_score;
	private TextView tv_level;
	private ArrayList<Point> tempinfo;
	
	
	public MediaPlayer player;

	private ArrayList<Point> pointmine;

	private static int gamespeed = 600;

	// ������������Ϣ�� ӳ�䡣
	private int gamescore=0;
	private int oldgamespeed=50;
	private int gamestate = 1;
	private boolean stop = false;
	private static final int refresh = 0x7777;
	private static final int makegrade = 0x8888;

	// �������Ϣ��������һ�������ɵĵ㡣������ֵ�� srat ��ʼ������Ҫ�ġ�
	private int gamelevel = 1;
	private int scoreinc = 0;
	private forcast cur;	// ���浱ǰ��������Ϣ
	private forcast next;	// ������һ�ν�Ҫ���ֵ�������Ϣ
	
	private srat mrat;
	private srat nrat;
	private SoundPool sp;//����һ��SoundPool   
    private int music;//����һ��������load������������suondID  
    private int music2;
	//private music onmusic;//������
//	private int vheight;
//	private int vwidth;
	private lorisdb db;
	String background;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		sp= new SoundPool(10, AudioManager.STREAM_SYSTEM, 5);//��һ������Ϊͬʱ���������������������ڶ����������ͣ�����Ϊ��������   
	    music = sp.load(this, R.raw.move, 1); //����������زķŵ�res/raw���2��������Ϊ��Դ�ļ�����3��Ϊ���ֵ����ȼ� 
	    music2 = sp.load(this, R.raw.bomb, 1); //����������زķŵ�res/raw���2��������Ϊ��Դ�ļ�����3��Ϊ���ֵ����ȼ� 
		ini();
		player = MediaPlayer.create(this, R.raw.background); 
		player.setLooping(true);//����ѭ������
		player.start();
		dealwithbt();
		gamethread();
		
	}

	// ��ʼ�����������еĿؼ���
	public void ini() {

		tempinfo = new ArrayList<Point>();
		// ������� 7 �� ͼ�Ρ�ÿ��ͼ����һ�� Arraylist��
		pointmine = new ArrayList<Point>();
		
		// �����������Ϣ�ĳ�ʼ����
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
		getResources().getString(R.string.txt_cancel);
		txt_onboard = getResources().getString(R.string.txt_onboard);
		txt_level = getResources().getString(R.string.txt_level);
		txt_level = getResources().getString(R.string.txt_level);
		txt_over = getResources().getString(R.string.txt_over);
		txt_score = getResources().getString(R.string.txt_score);
		
		//loris.fix();
//		vheight = loris.getHeight();
//		vwidth = loris.getWidth();
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
					//mu.playMoveVoice();
					sp.play(music, 1, 1, 0, 0, 1);
				}
			}
		});

		bt_left.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// ������������ƶ�����ô�����ƶ���
				if (mrat.moveleft(loris.board)) {
					tempinfo = mrat.getdata();
					loris.drawpoint(tempinfo);
					loris.invalidate();
					//mu.playMoveVoice();
					sp.play(music, 1, 1, 0, 0, 1);
				}

			}
		});
		bt_right.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (mrat.moveright(loris.board)) {
					// ������������ƶ�����ô�����ƶ���
					tempinfo = mrat.getdata();
					loris.drawpoint(tempinfo);
					loris.invalidate();
					sp.play(music, 1, 1, 0, 0, 1);
					
				}
			}
		});
		bt_down.setOnTouchListener(new OnTouchListener(){

			@Override
			public boolean onTouch(View arg0, MotionEvent arg1) {
				
				if(arg1.getAction()==MotionEvent.ACTION_DOWN){
					// �������״̬
					oldgamespeed = gamespeed;
					gamespeed = 30;
					sp.play(music, 1, 1, 0, 0, 1);	
				}
				else if(arg1.getAction() == MotionEvent.ACTION_UP){
					// �˳�����״̬
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
					player.pause();
					// �ı�ͼƬ����ʽΪ ����
				} else if (gamestate == 2) {
					gamestate = 1;
					bt_stop.setImageResource(R.drawable.pau);
					bt_left.setEnabled(true);
					bt_right.setEnabled(true);
					bt_change.setEnabled(true);
					bt_down.setEnabled(true);
					player.start();
					// �ı�ͼƬ����ʽ Ϊ ��ͣ
				}
			}
		});
		
		bt_quit.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// ������ǰ�� activity �� ������������֮���Ƿ��ص������档
				stop = true;
				player.stop();
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
		//���öԻ���ı���
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
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy��MM��dd�� HH:mm:ss",Locale.CHINA);       
                Date curDate  = new Date(System.currentTimeMillis());//��ȡ��ǰʱ��       
                String str = formatter.format(curDate);  
                user.date= str;
                user.score=score;
                
                db.insert(user);
                player.stop();
				Intent intent = new Intent(MainActivity.this,start.class);
				startActivity(intent);
                MainActivity.this.finish();
			}
		});

		 builder.create().show();
	}

	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void inipointmine() {
		Point t = new Point();
		t.set(4, 0);
		pointmine.add(t);
		t.set(4, 0);
		pointmine.add(t);
		t.set(3, 2);
		pointmine.add(t);
		t.set(4, 1);
		pointmine.add(t);
		t.set(4, 0);
		pointmine.add(t);
		t.set(4, 1);
		pointmine.add(t);
		t.set(4, 0);
		pointmine.add(t);

	}

	public forcast newsrat() {

		forcast temp = new forcast();
		int atype=0,atypetype=0;
		
		// ��������һ��ͼ�Σ� ����������ɵ�ͼ�Ρ�
		Random random = new Random();
		// ֻ���� ���� ����ġ�
		atype = random.nextInt(8);
		//if( type ==0 ) type +=3;
		// Ϊ������������ֵ�Ϳ��ԡ�
		// int type = random.nextInt()%NUM;
		atypetype = 1;
		//if(type <1) type ++;
		// if type >=1 && type <=7 ==>> 0--6
		if( atype >0 && atype<=pointmine.size()) temp.spoint = pointmine.get(atype-1);
		else{
			// ���п����� ����� ==0 ��ô�͵� 7 ��ͼ�����Ӧ��
			atype = 7;
			temp.spoint = pointmine.get(6);
		}
		// �����ɵ�ͼ�η��ؼ��ɡ�
		temp.type = atype;
		temp.typetype = atypetype;
		return temp;
	}

	public void gamethread() {

		// ���߳�����֮ǰ������Ҫ�� ������顣һ���ǵ�ǰ����飬��һ������һ��Ҫ���ֵ���顣
		cur = newsrat();
		next = newsrat(); // ��������Ԥ����
		mrat = new srat(cur); // ���õ�ǰ����鿪ʼ��Ϸ��
		nrat = new srat(next);
		smallloris.drawpoint(nrat.getdata());
		// �ڽ��� �̲߳��֣�������Ҫ���Ļ��� ���� ��Ϸ���������

		// newsrat(); // Ϊ 3 �����Ը�ֵ�� // ����һ������顣
		new Thread(new Runnable() {
			public void run() {
				while (true) {
					try {
						if (stop)
							break; // ����ۼӵ����㣬��ô������Ϸ����������߳̽�����
						else if (gamestate == 2) {
							// do nothing!
							// ֻ���� ������ 2 ��ʱ�� �Ż�������С�
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
						Thread.sleep(gamespeed);// wait for 1000 second

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
				loris.absorb(mrat.getdata());
				final int score =loris.makegrade();
				//grade=score;
				if(score == -1) {
					stop = true;
					Toast.makeText(MainActivity.this,txt_over,Toast.LENGTH_LONG).show();
					end(gamescore);
					Thread.currentThread().yield();					
				}
				else if(score!=0){
					sp.play(music2, 1, 1, 0, 0, 1);
					gamescore += score;
					scoreinc += score;
					if(scoreinc>=200){
						gamelevel ++;
						scoreinc=0;
						Toast.makeText(MainActivity.this,txt_levelup,Toast.LENGTH_SHORT).show();
						if(gamespeed>=300){
							gamespeed -=50;
							oldgamespeed -=50;
							tv_level.setText(txt_level+gamelevel);
						}
					}
					// ������Ӧ������
					tv_score.setText(txt_score+gamescore);
					
				}
				cur.copy(next);		// ��Ԥ����ͼ�����³�ʼ�����ǵ� ������
				mrat = new srat(cur);	// ��ʼ����ǰ����顣
				next = newsrat();	// �������� һ����飬���� Ԥ�����С�
				nrat = new srat(next);
				smallloris.drawpoint(nrat.getdata());// ������Ϣ��Ԥ����
				
			} else if (msg.what == MainActivity.refresh) {
				// ��˵����Ϸ����������䣬ʵ���ϲ���Ҫ��ʲô��
				tempinfo = mrat.getdata();
				loris.drawpoint(tempinfo);
				loris.invalidate();
			}
			super.handleMessage(msg);
		}
	};
}
