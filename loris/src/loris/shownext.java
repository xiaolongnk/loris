package loris;

import java.util.ArrayList;

import android.R;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

/*
 * 	游戏的主区域。
 * 
 */

public class shownext extends View {
	ArrayList<Point> info = new ArrayList<Point>();
	public int size = 20; // 每个单位 是 30 px， 这个 size 是整个游戏的 size大小
	private static final int vheight = 6; // 屏幕 高 是 15 个单位
	private static final int vwidth = 5; // 屏幕 宽 是 10 个单位
	
	private int mywidth;
	private int myheight;
	

	public shownext(Context context, AttributeSet attrs) {
		super(context, attrs);
	
	}


	public void drawpoint(ArrayList<Point> p) {
		info = p;
		shownext.this.invalidate();
	}

	public void onDraw(Canvas canvas) {
		
		// 第一件事 是确定屏幕上的边界区域，然后画出填充图形。
		// 这两个 裁剪区域是 有mywidth  和  myheight 来确定的。
		// 得到背景颜色值。
		
		
		Paint pen = new Paint();
		
		
		//Shader mShader = new LinearGradient(300,100,500,200
		//		, new int[] {
		//		Color.parseColor("#FF1493"), Color.parseColor("#0000FF") }
		//		, null , Shader.TileMode.REPEAT);
				
		//pen.setShader(mShader);
		pen.setColor(Color.parseColor("#FF1493"));
		for (int i = 0; i < info.size(); i++) {
			int x = info.get(i).x-3;
			int y = info.get(i).y+3;
			canvas.drawRect(x * size, y * size, (x + 1) * size, (y + 1)
					* size, pen);
		}
		pen.setColor(Color.BLUE);
		for( int i=0; i<=vwidth; i++){
			canvas.drawLine(i*size,0, i*size, vheight*size, pen);
		}
		for( int i=0; i<=vheight; i++){
			canvas.drawLine(0,i*size, vwidth*size, i*size, pen);
		}
	}
	
	public float height() {
		return this.getHeight();
	}

	public float width() {
		return this.getWidth();
	}
}