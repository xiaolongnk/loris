package loris;

import java.util.ArrayList;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Shader;
import android.media.AudioManager;
import android.media.SoundPool;
import android.util.AttributeSet;
import android.view.View;
import loris.music;

/*
 * 	游戏的主区域。
 * 
 */

public class gameview extends View {
	ArrayList<Point> info = new ArrayList<Point>();
	public int size =36; // 每个单位 是 30 px， 这个 size 是整个游戏的 size大小
	private static final int vheight = 20; // 屏幕 高 是 15 个单位
	private static final int vwidth = 10; // 屏幕 宽 是 10 个单位
	private boolean drawnow = true;
	private music onmusic;//爆炸音
	int[][] board = new int[vwidth][vheight];
	
	public gameview(Context context, AttributeSet attrs) {
		super(context, attrs);
		// 在这里初始化 size 的大小。
		// 先认为 size = 30; 留待后期完成。
		
		// 这里是初始化view 大小的工作 , 通过 fix 方法， 就确定了 size 的大小
		// mywidth myheight 相当于是对这三个常量的初始化。 
		fix();
		//System.out.println("size of the board is"+size);
		// 将期盘初始化为空。
		for (int i = 0; i < vwidth; i++)
			for (int j = 0; j < vheight; j++) {
				board[i][j] = 0;
			}
		
	}

	public void fix() {
		// 根据传回来的参数来修改这个 view .
		// 从原点开始，根据 view 的 高度 和 宽度，计算出 size 的大小

	}

	// 如果物块停止了，那么调用这个方法，将物块的所有点保存在屏幕数组中。
	// 在每次更新试图的时候，都将已经存在的物块画出来。
	public void updateboard(ArrayList<Point> p) {
		for (int i = 0; i < p.size(); i++) {
			Point temp = p.get(i);
			board[temp.x][temp.y] = 1;
		}
	}

	public void drawpoint(ArrayList<Point> p) {
		info = p;
	}

	public void onDraw(Canvas canvas) {
		
		// 第一件事 是确定屏幕上的边界区域，然后画出填充图形。
		// 这两个 裁剪区域是 有mywidth  和  myheight 来确定的。
		// 得到背景颜色值。
		Paint mpaint = new Paint();
		mpaint.setColor(Color.parseColor("#00000000"));
		canvas.drawRect(size*vwidth,0,this.getWidth(),this.getHeight(), mpaint);
		canvas.drawRect(0,size*vheight,this.getWidth(),this.getHeight(),mpaint);
		
		Shader mShader = new LinearGradient(0,0,600,540
				, new int[] {
				Color.parseColor("#FF1493"), Color.parseColor("#0000FF") }
				, null , Shader.TileMode.REPEAT);
				
		mpaint.setShader(mShader);
			//设置阴影
		mpaint.setShadowLayer(2 , 2 , 2 , Color.GRAY);
		mpaint.setColor(Color.BLUE);
		mpaint.setStrokeCap(Paint.Cap.ROUND);//头尾圆润
        mpaint.setStrokeJoin(Paint.Join.ROUND); //关节处圆润
		// 画屏幕上已经有的小方块
		for (int i = 0; i < vwidth; i++)
			for (int j = 0; j < vheight; j++) {
				if (board[i][j] == 1) {
					int x = i, y = j;
					
					canvas.drawRect(x * size, y * size, (x + 1) * size, (y + 1)
							* size, mpaint);
				}
			}

		// 画屏幕上正在运动的小方块

		if (drawnow) {
			for (int i = 0; i < info.size(); i++) {
				int x = info.get(i).x;
				int y = info.get(i).y;
				canvas.drawRect(x * size, y * size, (x + 1) * size, (y + 1)
						* size, mpaint);
			}
		}
		
		Paint netpen = new Paint();
		netpen.setColor(Color.GRAY);
		
		for( int i=0; i<=vwidth; i++){
			canvas.drawLine(i*size,0, i*size, vheight*size, netpen);
		}
		for( int i=0; i<=vheight; i++){
			canvas.drawLine(0,i*size, vwidth*size, i*size, netpen);
		}
		drawnow = true;
		// 在屏幕上画网格
	}

	// 将 ArrayList p 中的点留作屏幕上的固定的点。这些点在每一次绘制的时候都会被会画出来。
	public void absorb(ArrayList<Point> p) {
		for (int i = 0; i < p.size(); i++) {
			int x = p.get(i).x;
			int y = p.get(i).y;
			if(x>=0 && x<vwidth && y>=0 && y<vheight) board[x][y] = 1;
			else System.out.println("absorb 方法中数组越界，请注意！");
		}
	}

	// 计算分数的函数。
	public int makegrade() {
		int grade = 0;
		for (int i = vheight -1; i >= 0; i--) {
			int sum = 0;
			for (int j = 0; j < vwidth; j++) {
				sum += board[j][i];
			}
			if (sum == vwidth) {
				System.out.println("count for sure!");
				// 如果这一行有 10 个 1，那么对应的得分值 +1
				grade++;
				
				//onmusic.playBombVoice();
				// 同时，将这一行的状态消去。并且形成下落的状态。
				for (int k = i; k >= 0; k--) {
					// 如果是 最上面一行，那么这一行的状态 置 0
					if (k == 0) {
						for (int g = 0; g < vwidth; g++)
							board[g][k] = 0;
					} else {
						// 让棋盘下落。
						for (int g = 0; g < vwidth; g++) {
							board[g][k] = board[g][k - 1];
						}
					}
				}
				i++;
			}
		}
		// 下面是 根据消去的行数 算出本次得分。
		
		// 检测屏幕最上面的一行，如果有一个 为 1， 那么游戏结束，返回 -1
		for( int i=0; i<vwidth; i++){
			if(board[i][0]==1) grade =-1;
		}
		if (grade == 1) grade = 1 * 10;
		else if (grade == 2) grade = 3 * 10;
		else if (grade == 3) grade = 5 * 10;
		else if (grade == 4) grade = 8 * 10;

		// 这是，如果调用 invalidate() 方法引起屏幕的重绘，那么还会绘制出
		// 当前的物块，所以我们还要将当前的物块的对象清楚掉。
		drawnow = false;
		//
		gameview.this.invalidate();
		return grade;
	}

	public float height() {
		return this.getHeight();
	}

	public float width() {
		return this.getWidth();
	}
}