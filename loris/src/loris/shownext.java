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
 * 	��Ϸ��������
 * 
 */

public class shownext extends View {
	ArrayList<Point> info = new ArrayList<Point>();
	public int size = 20; // ÿ����λ �� 30 px�� ��� size ��������Ϸ�� size��С
	private static final int vheight = 6; // ��Ļ �� �� 15 ����λ
	private static final int vwidth = 5; // ��Ļ �� �� 10 ����λ
	
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
		
		// ��һ���� ��ȷ����Ļ�ϵı߽�����Ȼ�󻭳����ͼ�Ρ�
		// ������ �ü������� ��mywidth  ��  myheight ��ȷ���ġ�
		// �õ�������ɫֵ��
		
		
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