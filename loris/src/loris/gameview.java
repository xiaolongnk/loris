package loris;

import java.util.ArrayList;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;


public class gameview extends View {
	ArrayList<Point> info = new ArrayList<Point>();
	public int size =36; 
	private static final int vheight = 20;
	private static final int vwidth = 10; 
	private boolean drawnow = true;
	int[][] board = new int[vwidth][vheight];
	private Paint mpaint;
	private Shader mShader;
	private Paint netpen;
	
	public gameview(Context context, AttributeSet attrs) {
		super(context, attrs);
		fix();
		init();
	}
	
	public void init(){
		for (int i = 0; i < vwidth; i++)
			for (int j = 0; j < vheight; j++) {
				board[i][j] = 0;
			}
		mpaint = new Paint();
		mpaint.setColor(Color.parseColor("#00000000"));
		mShader = new LinearGradient(0,0,600,540
				, new int[] {
				Color.parseColor("#FF1493"), Color.parseColor("#0000FF") }
				, null , Shader.TileMode.REPEAT);
		netpen = new Paint();
		netpen.setColor(Color.GRAY);
	}
	
	public void fix() {
		// ���ݴ������Ĳ������޸���� view .
		// ��ԭ�㿪ʼ������ view �� �߶� �� ��ȣ������ size �Ĵ�С

	}

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
		
		canvas.drawRect(size*vwidth,0,this.getWidth(),this.getHeight(), mpaint);
		canvas.drawRect(0,size*vheight,this.getWidth(),this.getHeight(),mpaint);
		
				
		mpaint.setShader(mShader);
		mpaint.setShadowLayer(2 , 2 , 2 , Color.GRAY);
		mpaint.setColor(Color.BLUE);
		mpaint.setStrokeCap(Paint.Cap.ROUND);//ͷβԲ��
        mpaint.setStrokeJoin(Paint.Join.ROUND); //�ؽڴ�Բ��
		// ����Ļ���Ѿ��е�С����
		for (int i = 0; i < vwidth; i++)
			for (int j = 0; j < vheight; j++) {
				if (board[i][j] == 1) {
					int x = i, y = j;
					canvas.drawRect(x * size, y * size, (x + 1) * size, (y + 1)
							* size, mpaint);
				}
			}

		// ����Ļ�������˶���С����

		if (drawnow) {
			for (int i = 0; i < info.size(); i++) {
				int x = info.get(i).x;
				int y = info.get(i).y;
				canvas.drawRect(x * size, y * size, (x + 1) * size, (y + 1)
						* size, mpaint);
			}
		}
		
		int i;
		for( i=0; i<=vwidth;i++){
			canvas.drawLine(i*size,0, i*size, vheight*size, netpen);
		}
		for( i=0; i<=vheight; i++){
			canvas.drawLine(0,i*size, vwidth*size, i*size, netpen);
		}
		drawnow = true;
	}

	// �� ArrayList p �еĵ�������Ļ�ϵĹ̶��ĵ㡣��Щ����ÿһ�λ��Ƶ�ʱ�򶼻ᱻ�ử������
	public void absorb(ArrayList<Point> p) {
		for (int i = 0; i < p.size(); i++) {
			int x = p.get(i).x;
			int y = p.get(i).y;
			if(x>=0 && x<vwidth && y>=0 && y<vheight) board[x][y] = 1;
			else System.out.println("absorb ����������Խ�磬��ע�⣡");
		}
	}

	// ��������ĺ�����
	public int makegrade() {
		int grade = 0;
		for (int i = vheight -1; i >= 0; i--) {
			int sum = 0;
			for (int j = 0; j < vwidth; j++) {
				sum += board[j][i];
			}
			if (sum == vwidth) {
				System.out.println("count for sure!");
				// �����һ���� 10 �� 1����ô��Ӧ�ĵ÷�ֵ +1
				grade++;
				
				//onmusic.playBombVoice();
				// ͬʱ������һ�е�״̬��ȥ�������γ������״̬��
				for (int k = i; k >= 0; k--) {
					// ����� ������һ�У���ô��һ�е�״̬ �� 0
					if (k == 0) {
						for (int g = 0; g < vwidth; g++)
							board[g][k] = 0;
					} else {
						// ���������䡣
						for (int g = 0; g < vwidth; g++) {
							board[g][k] = board[g][k - 1];
						}
					}
				}
				i++;
			}
		}
		// ������ ������ȥ������ ������ε÷֡�
		
		// �����Ļ�������һ�У������һ�� Ϊ 1�� ��ô��Ϸ���������� -1
		for( int i=0; i<vwidth; i++){
			if(board[i][0]==1) grade =-1;
		}
		if (grade == 1) grade = 1 * 10;
		else if (grade == 2) grade = 3 * 10;
		else if (grade == 3) grade = 5 * 10;
		else if (grade == 4) grade = 8 * 10;

		// ���ǣ�������� invalidate() ����������Ļ���ػ棬��ô������Ƴ�
		// ��ǰ����飬�������ǻ�Ҫ����ǰ�����Ķ����������
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