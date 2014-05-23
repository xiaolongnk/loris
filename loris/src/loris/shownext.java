package loris;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.View;

/*
 * 	游戏的主区域。
 * 
 */

public class shownext extends View {
	ArrayList<Point> info = new ArrayList<Point>();
	public int size = 20; 
	private static final int vheight = 6;
	private static final int vwidth = 5; 
	
	public shownext(Context context, AttributeSet attrs) {
		super(context, attrs);
	
	}


	public void drawpoint(ArrayList<Point> p) {
		info = p;
		shownext.this.invalidate();
	}

	public void onDraw(Canvas canvas) {
		

		
		Paint pen = new Paint();
		
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