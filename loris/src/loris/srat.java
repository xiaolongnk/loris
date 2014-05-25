package loris;

import java.util.ArrayList;

import android.graphics.Point;
import android.util.Log;



/*
 * 
 */

public class srat {

	public ArrayList<Point> sratinfo = new ArrayList<Point>();

	public Point sratpoint; 
	public int srattype;
	public int srattypetype;
	public static String LOGTYPE="loris";
	private static final int vheight=20;
	private static final int vwidth=10;
	
	
	public srat(forcast temp) {
		sratpoint = temp.spoint;
		srattype = temp.type;
		srattypetype = temp.typetype;
		sratinfo = fillsratinfo(temp.spoint, temp.type, temp.typetype);
	}

	public ArrayList<Point> fillsratinfo(Point s, int type, int typetype) {
		
		ArrayList<Point> temp = new ArrayList<Point>();
		
		int a=0, b=0;
		a = s.x;
		b = s.y;
		if(type==1){
			// 
			if(typetype==1){
				temp.add(new Point(a, b));
				temp.add(new Point(a + 1, b));
				temp.add(new Point(a + 2, b));
				temp.add(new Point(a + 3, b));
			}
			else if(typetype==2)
			{
				temp.add(new Point(a, b));
				temp.add(new Point(a, b + 1));
				temp.add(new Point(a, b + 2));
				temp.add(new Point(a, b + 3));
			}
			else {
				Log.d(LOGTYPE,"出现了一种无法处理的图形。"+type);
			}
		}
		else if(type==2){
			if(typetype==1){
				temp.add(new Point(a,b));
				temp.add(new Point(a,b+1));
				temp.add(new Point(a,b+2));
				temp.add(new Point(a+1,b+2));
			}
			else if(typetype==2){
				temp.add(new Point(a,b));
				temp.add(new Point(a,b+1));
				temp.add(new Point(a+1,b));
				temp.add(new Point(a+2,b));
			}
			else if(typetype==3){
				temp.add(new Point(a,b));
				temp.add(new Point(a+1,b));
				temp.add(new Point(a+1,b+1));
				temp.add(new Point(a+1,b+2));
			}
			else if(typetype ==4){
				temp.add(new Point(a,b));
				temp.add(new Point(a+1,b));
				temp.add(new Point(a+2,b));
				temp.add(new Point(a+2,b-1));
			}else {
				Log.d(LOGTYPE,"出现了一种无法处理的图形。"+type);
			}
		}
		else if(type ==3 ){
			if(typetype==1){
				temp.add(new Point(a,b));
				temp.add(new Point(a+1,b));
				temp.add(new Point(a+1,b-1));
				temp.add(new Point(a+1,b-2));
			}
			else if(typetype==2){
				temp.add(new Point(a,b));
				temp.add(new Point(a,b+1));
				temp.add(new Point(a+1,b+1));
				temp.add(new Point(a+2,b+1));
			}
			else if(typetype==3){
				temp.add(new Point(a,b));
				temp.add(new Point(a+1,b));
				temp.add(new Point(a,b+1));
				temp.add(new Point(a,b+2));
			}
			else if(typetype==4){
				temp.add(new Point(a,b));
				temp.add(new Point(a+1,b));
				temp.add(new Point(a+2,b));
				temp.add(new Point(a+2,b+1));
			}else {
				System.out.println("出现了一种无法处理的图形。in "+type);
			}
		}
		else if(type==4){
			if(typetype==1){
				temp.add(new Point(a,b));
				temp.add(new Point(a,b+1));
				temp.add(new Point(a+1,b));
				temp.add(new Point(a+1,b-1));
			}
			else if(typetype==2){
				temp.add(new Point(a,b));
				temp.add(new Point(a+1,b));
				temp.add(new Point(a+1,b+1));
				temp.add(new Point(a+2,b+1));
			}else {
				System.out.println("出现了一种无法处理的图形。in "+type);
			}
		}
		else if(type==5){
			if(typetype==1){
				temp.add(new Point(a,b));
				temp.add(new Point(a,b+1));
				temp.add(new Point(a+1,b+1));
				temp.add(new Point(a+1,b+2));
			}
			else if(typetype==2){
				temp.add(new Point(a,b));
				temp.add(new Point(a+1,b));
				temp.add(new Point(a+1,b-1));
				temp.add(new Point(a+2,b-1));
			}else {
				System.out.println("出现了一种无法处理的图形。in "+type);
			}
		}
		else if(type==6){
			if(typetype ==1){
				temp.add(new Point(a,b));
				temp.add(new Point(a+1,b));
				temp.add(new Point(a+1,b-1));
				temp.add(new Point(a+2,b));
			}
			else if(typetype==2){
				temp.add(new Point(a,b));
				temp.add(new Point(a,b+1));
				temp.add(new Point(a,b+2));
				temp.add(new Point(a+1,b+1));
			}
			else if(typetype==3){
				temp.add(new Point(a,b));
				temp.add(new Point(a+1,b));
				temp.add(new Point(a+2,b));
				temp.add(new Point(a+1,b+1));
			}
			else if(typetype==4){
				temp.add(new Point(a,b));
				temp.add(new Point(a+1,b));
				temp.add(new Point(a+1,b-1));
				temp.add(new Point(a+1,b+1));
			}else {
				System.out.println("出现了一种无法处理的图形。in "+type);
			}
		}
		else if(type==7){
			if(typetype==1){
				temp.add(new Point(a,b));
				temp.add(new Point(a+1,b));
				temp.add(new Point(a,b+1));
				temp.add(new Point(a+1,b+1));
			}else {
				System.out.println("出现了一种无法处理的图形。in "+type);
			}
		}
		else {
			System.out.println("出现了一种无法处理的图形。in "+type);
		}
		return temp;
	}
	
	// if can moveright, then move right;
	public boolean moveright(int [][]b) {
		Point temp= new Point(sratpoint.x+1,sratpoint.y);
		ArrayList <Point> tt= fillsratinfo(temp,srattype,srattypetype);
		if (check(tt,b)){
			sratinfo = tt;
			sratpoint = temp;
			return true;
		}
		return false;
	}
	
	// 如果可以移动， 返回true 否则返回 false
	public boolean moveleft(int [][] b) {
		// 实现方法 同 moveright
		
		Point temp= null;
		temp = new Point(sratpoint.x-1,sratpoint.y);
		ArrayList <Point> tt= fillsratinfo(temp,srattype,srattypetype);
		if (check(tt,b)){
			// 如果可以，左移。 如果不可以 坐标不变.
			//prt("可以向左移动。即将更新坐标。");
			sratinfo = tt;
			sratpoint = temp;
			return true;
		}
		return false;
	}

	public Point trychange() {
		Point t=new Point();

		// 第一种图形
		if(srattype ==1){
			if(srattypetype==1){
				t.set(sratpoint.x+1, sratpoint.y-1);
			}
			else if(srattypetype==2){
				t.set(sratpoint.x-1, sratpoint.y+1);
			}
		}
		// 第二种图形
		else if(srattype ==2 ){
			if(srattypetype==1){
				t.set(sratpoint.x, sratpoint.y+1);
			}
			else if(srattypetype==2){
				t.set(sratpoint.x, sratpoint.y);	// 变到第三种 不用改变
			}else if(srattypetype==3){
				t.set(sratpoint.x-1,sratpoint.y+1);
			}
			else if(srattypetype==4){
				t.set(sratpoint.x+1,sratpoint.y-2);
			}
		}
		else if(srattype ==3){
			if(srattypetype==1){
				t.set(sratpoint.x+1,sratpoint.y-1);
			}
			else if(srattypetype==2){
				t.set(sratpoint.x,sratpoint.y);
			}
			else if(srattypetype==3){
				t.set(sratpoint.x-1, sratpoint.y);
			}
			else if(srattypetype==4){
				t.set(sratpoint.x,sratpoint.y+1);
			}
		}
		else if(srattype ==4){
			if(srattypetype==1){
				t.set(sratpoint.x,sratpoint.y);
			}
			else if(srattypetype ==2){
				t.set(sratpoint.x,sratpoint.y);
			}
		}
		else if(srattype ==5 ){
			if(srattypetype ==1 ){
				t.set(sratpoint.x-1,sratpoint.y+2);
			}
			else if(srattypetype ==2 ){
				t.set(sratpoint.x+1,sratpoint.y-2);
			}
		}
		else if(srattype==6){
			if(srattypetype ==1 ){
				t.set(sratpoint.x+1,sratpoint.y-1);
			}
			else if(srattypetype ==2){
				t.set(sratpoint.x-1,sratpoint.y+1);
			}
			else if(srattypetype ==3){
				t.set(sratpoint.x,sratpoint.y);
			}
			else if(srattypetype ==4){
				t.set(sratpoint.x,sratpoint.y);
			}
		}
		else if(srattype ==7){
			if(srattypetype ==1){
				t.set(sratpoint.x,sratpoint.y);
			}
		}
		return t;
	}

	
	public boolean check(ArrayList<Point> temp,int[][]b) {
		for(int i=0; i<temp.size();i++){
			int x=temp.get(i).x;
			int y = temp.get(i).y;
			
			if( x>= vwidth || x<0 ||  y>=vheight ){
				Log.d(LOGTYPE,"出界了，拒绝移动 ！  "+x+"  "+y);
				return false;
			}
			if( x>=0 && x<vwidth && y< vheight){	
				if(y>=0) if( b[x][y]==1 ){
					Log.d(LOGTYPE,"被物块挡住了，拒绝移动！"+x+"  "+y);
					return false;
				}
			}
		}
		return true;
	}

	// 这是物块变形的对外接口。用来响应变形按钮事件。
	public boolean change(int [][]b) {
		ArrayList<Point> temp = new ArrayList<Point>();
		Point t = trychange(); // 尝试修改图形，只修该图形的起点坐标。
		int trytype = nexttypetype(); // 得到旋转之后的图形姿态。
		// 将旋转之后的图形详细信息还原到数组中。
		temp = fillsratinfo(t, srattype, trytype);
		// 下面对这个数组进行正确性检验，如果通过，那么旋转，如果不通过，那么不变形。
		if (check(temp,b)) {
			sratinfo = temp; // 如果物块可以变形，那么变形。
			// 修改物块的 姿态标记。
			srattypetype = trytype;
			sratpoint.set(t.x, t.y);
			return true;
		} else{
			System.out.println("变形失败!");
			return false;
		}
	}

	// 对 7 中图形，对么一种姿态的下一种姿态进行定义。、
	// 返回当前姿态的下一种姿态。
	public int nexttypetype() {
		int value = 0;
		if(srattype ==1 || srattype==4||srattype==5){
			if(srattypetype==1) value= 2;
			else value=1;
		}
		else if(srattype ==2||srattype==3||srattype==6 ){
			if(srattypetype<=3) value=srattypetype+1;
			else value=1;
		}
		else if(srattype==7){
			value =1;
		}
		return value;
	}

	public boolean movedown(int [][]b) {
		Point temp = new Point(sratpoint.x,sratpoint.y+1);
		ArrayList<Point> temparray = new ArrayList<Point>();
		temparray = fillsratinfo(temp,srattype,srattypetype);
		if(check(temparray,b)){
			sratpoint = temp;
			sratinfo = temparray;
			return true;	
		}
		return false;
	}

	public ArrayList<Point> getdata() {
		return sratinfo;
	}
}