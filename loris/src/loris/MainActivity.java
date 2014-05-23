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

	// 下面是两个消息的 映射。
	private int gamescore=0;
	private int oldgamespeed=50;
	private int gamestate = 1;
	private boolean stop = false;
	private static final int refresh = 0x7777;
	private static final int makegrade = 0x8888;

	// 下面的信息用来保存一个新生成的点。这三个值是 srat 初始化所需要的。
	private int gamelevel = 1;
	private int scoreinc = 0;
	private forcast cur;	// 保存当前的物块的信息
	private forcast next;	// 保存下一次将要出现的物块的信息
	
	private srat mrat;
	private srat nrat;
	private SoundPool sp;//声明一个SoundPool   
    private int music;//定义一个整型用load（）；来设置suondID  
    private int music2;
	//private music onmusic;//按键音
//	private int vheight;
//	private int vwidth;
	private lorisdb db;
	String background;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		sp= new SoundPool(10, AudioManager.STREAM_SYSTEM, 5);//第一个参数为同时播放数据流的最大个数，第二数据流类型，第三为声音质量   
	    music = sp.load(this, R.raw.move, 1); //把你的声音素材放到res/raw里，第2个参数即为资源文件，第3个为音乐的优先级 
	    music2 = sp.load(this, R.raw.bomb, 1); //把你的声音素材放到res/raw里，第2个参数即为资源文件，第3个为音乐的优先级 
		ini();
		player = MediaPlayer.create(this, R.raw.background); 
		player.setLooping(true);//设置循环播放
		player.start();
		dealwithbt();
		gamethread();
		
	}

	// 初始化界面中所有的控件。
	public void ini() {

		tempinfo = new ArrayList<Point>();
		// 用来存放 7 种 图形。每种图形是一个 Arraylist。
		pointmine = new ArrayList<Point>();
		
		// 对两个物块信息的初始化。
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
				// 如果可以向左移动，那么想左移动。
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
					// 如果可以享有移动，那么向右移动。
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
					// 进入加速状态
					oldgamespeed = gamespeed;
					gamespeed = 30;
					sp.play(music, 1, 1, 0, 0, 1);	
				}
				else if(arg1.getAction() == MotionEvent.ACTION_UP){
					// 退出加速状态
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
					// 改变图片的样式为 启动
				} else if (gamestate == 2) {
					gamestate = 1;
					bt_stop.setImageResource(R.drawable.pau);
					bt_left.setEnabled(true);
					bt_right.setEnabled(true);
					bt_change.setEnabled(true);
					bt_down.setEnabled(true);
					player.start();
					// 改变图片的样式 为 暂停
				}
			}
		});
		
		bt_quit.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// 结束当前的 activity ， 等有了主界面之后，是返回到主界面。
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
		//设置对话框的标题
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
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss",Locale.CHINA);       
                Date curDate  = new Date(System.currentTimeMillis());//获取当前时间       
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
		
		// 在这个随机一个图形， 用来存放生成的图形。
		Random random = new Random();
		// 只产生 长条 横向的。
		atype = random.nextInt(8);
		//if( type ==0 ) type +=3;
		// 为三个属性生成值就可以。
		// int type = random.nextInt()%NUM;
		atypetype = 1;
		//if(type <1) type ++;
		// if type >=1 && type <=7 ==>> 0--6
		if( atype >0 && atype<=pointmine.size()) temp.spoint = pointmine.get(atype-1);
		else{
			// 还有可能是 这个数 ==0 那么和第 7 种图形相对应。
			atype = 7;
			temp.spoint = pointmine.get(6);
		}
		// 将生成的图形返回即可。
		temp.type = atype;
		temp.typetype = atypetype;
		return temp;
	}

	public void gamethread() {

		// 在线程启动之前，必须要有 两个物块。一个是当前的物块，另一个是下一次要出现的物块。
		cur = newsrat();
		next = newsrat(); // 用来进行预报。
		mrat = new srat(cur); // 利用当前的物块开始游戏。
		nrat = new srat(next);
		smallloris.drawpoint(nrat.getdata());
		// 在进入 线程部分，这里需要做的还有 更新 游戏的相关区域。

		// newsrat(); // 为 3 个属性赋值。 // 生成一个新物块。
		new Thread(new Runnable() {
			public void run() {
				while (true) {
					try {
						if (stop)
							break; // 如果累加到顶点，那么本局游戏结束。这个线程结束。
						else if (gamestate == 2) {
							// do nothing!
							// 只有在 不等于 2 的时候 才会继续运行。
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
					// 更新相应的区域。
					tv_score.setText(txt_score+gamescore);
					
				}
				cur.copy(next);		// 用预报的图像重新初始化我们的 物块对象。
				mrat = new srat(cur);	// 初始化当前的物块。
				next = newsrat();	// 重新生成 一个物块，放在 预备箱中。
				nrat = new srat(next);
				smallloris.drawpoint(nrat.getdata());// 进行信息的预报。
				
			} else if (msg.what == MainActivity.refresh) {
				// 这说明游戏物块正在下落，实际上不需要做什么。
				tempinfo = mrat.getdata();
				loris.drawpoint(tempinfo);
				loris.invalidate();
			}
			super.handleMessage(msg);
		}
	};
}
