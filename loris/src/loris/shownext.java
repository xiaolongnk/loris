package loris;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.View;

public class shownext extends View {
	ArrayList<Point> info = new ArrayList<Point>();
	public int size; 
	private static final int vheight = 5;
	private static final int vwidth = 5; 
	private Paint greypen;
	private Paint bluepen;
	
	private boolean isfirst = true;
	
	public shownext(Context context, AttributeSet attrs) {
		super(context, attrs);
		greypen = new Paint();
		greypen.setColor(Color.GRAY);
		
		bluepen = new Paint();
		bluepen.setColor(Color.BLUE);
		
	}

	public void drawpoint(ArrayList<Point> p) {
		info = p;
		shownext.this.invalidate();
	}
	
	public void caculateSize(int x, int y){
		size = (int)(x<y?x:y)/6;
	}
	
	public void onDraw(Canvas canvas) {
		if(isfirst) {
			caculateSize(this.getWidth(),this.getHeight());
			isfirst = false;
		}
		
		for (int i = 0; i < info.size(); i++) {
			int x = info.get(i).x-3;
			int y = info.get(i).y+3;
			canvas.drawRect(x * size, y * size, (x + 1) * size, (y + 1)* size, bluepen);
		}
		
		for( int i=0; i<=vwidth; i++){
			canvas.drawLine(i*size,0, i*size, vheight*size, greypen);
		}
		for( int i=0; i<=vheight; i++){
			canvas.drawLine(0,i*size, vwidth*size, i*size, greypen);
		}
	}
	
}