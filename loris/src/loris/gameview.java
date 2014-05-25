package loris;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class gameview extends View {
	ArrayList<Point> info = new ArrayList<Point>();
	public int size = 0;
	public static String LOGTYPE = "loris";
	private static final int vheight = 20;
	private static final int vwidth = 10;
	int[][] board = new int[vwidth][vheight];
	private Paint mpaint;
	private Paint netpen;
	private Paint whitePen;
	private boolean drawnow = true;

	public gameview(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public void init() {
		for (int i = 0; i < vwidth; i++)
			for (int j = 0; j < vheight; j++) {
				board[i][j] = 0;
			}
		mpaint = new Paint();
		mpaint.setColor(Color.BLUE);
		netpen = new Paint();
		netpen.setColor(Color.GRAY);
		whitePen = new Paint();
		whitePen.setColor(Color.WHITE);
	}

	public void fix(Canvas canvas) {
		canvas.drawRect(size * vwidth, 0, this.getWidth(), this.getHeight(),
				whitePen);
		canvas.drawRect(0, size * vheight, this.getWidth(), this.getHeight(),
				whitePen);
	}


	public void drawpoint(ArrayList<Point> p) {
		info = p;
	}

	public void caculateSize(float w, float h) {
		size = (int) ((w / vwidth) < (h / vheight) ? w / vwidth : h / vheight);
		isfirst = true;
	}

	private boolean isfirst = false;

	public void onDraw(Canvas canvas) {
		
		//caculate size when it is first time.
		if (!isfirst)
			caculateSize(this.getWidth(), this.getHeight());

		for (int i = 0; i < vwidth; i++)
			for (int j = 0; j < vheight; j++) {
				if (board[i][j] == 1) {
					int x = i, y = j;
					canvas.drawRect(x * size, y * size, (x + 1) * size, (y + 1)
							* size, mpaint);
				}
			}
		if(drawnow){
			for (int i = 0; i < info.size(); i++) {
				int x = info.get(i).x;
				int y = info.get(i).y;
				canvas.drawRect(x * size, y * size, (x + 1) * size, (y + 1) * size,
					mpaint);
			}
		}else {
			drawnow = true;
		}
		for (int i = 0; i <= vwidth; i++) {
			canvas.drawLine(i * size, 0, i * size, vheight * size, netpen);
		}
		for (int i = 0; i <= vheight; i++) {
			canvas.drawLine(0, i * size, vwidth * size, i * size, netpen);
		}
		
		fix(canvas);
	}

	public void absorb(ArrayList<Point> p) {
		for (int i = 0; i < p.size(); i++) {
			int x = p.get(i).x;
			int y = p.get(i).y;
			if (x >= 0 && x < vwidth && y >= 0 && y < vheight)
				board[x][y] = 1;
		}
	}

	public int makegrade(ArrayList<Point> p) {

		this.absorb(p);
		int grade = 0;

		for (int i = vheight - 1; i >= 0; i--) {
			int sum = 0;
			for (int j = 0; j < vwidth; j++) {
				sum += board[j][i];
			}
			if (sum == vwidth) {
				grade++;
				for (int k = i; k >= 0; k--) {
					if (k == 0) {
						for (int g = 0; g < vwidth; g++)
							board[g][k] = 0;
					} else {
						for (int g = 0; g < vwidth; g++) {
							board[g][k] = board[g][k - 1];
						}
					}
				}
				i++;
			}
		}
		for (int i = 0; i < vwidth; i++) {
			if (board[i][0] == 1) {
				Log.d(LOGTYPE, "gamestop!");
				return -1;
			}
		}
		
		if(grade == 0) return 0;
		Log.d(LOGTYPE,"makegrade is: "+grade);
		
		if (grade == 1)
			grade = 1 * 10;
		else if (grade == 2)
			grade = 3 * 10;
		else if (grade == 3)
			grade = 5 * 10;
		else if (grade == 4)
			grade = 8 * 10;
		drawnow = false;
		return grade;
	}

}