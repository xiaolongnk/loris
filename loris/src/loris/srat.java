package loris;

import java.util.ArrayList;
import android.graphics.Point;

/*
 * 
 */

public class srat {

	public ArrayList<Point> sratinfo = new ArrayList<Point>();

	public Point sratpoint; // 这种形状的一个起点。起点是图形的左上点。
	public int srattype; // 表示是何种情况的图形。
	public int srattypetype; // 表示是这种图形的哪一种情况。
	
	private static final int vheight=20;
	private static final int vwidth=10;
	
	public int sratspeed; // 物块每次只下滑一个单位。
	public srat(forcast temp) {
		// 构造的时候，点的list 这个list 包含了图形的关键点的信息。
		// 利用这些点，可以在屏幕上画出相应的图形。
		sratpoint = temp.spoint;
		srattype = temp.type;
		srattypetype = temp.typetype;
		sratspeed = 1;
		// 填充 sratinfo 数组。
		sratinfo = fillsratinfo(temp.spoint, temp.type, temp.typetype);
		//printlist(sratinfo);
	}

	void printlist(ArrayList<Point> p) {
		for (int i = 0; i < p.size(); i++) {
			prt(p.get(i));
		}
	}

	void prt(Point t) {
		System.out.println("(" + t.x + "," + t.y + ")");
	}

	// 目前只包含了 长条的两种扩充方案。用来前期测试。
	public ArrayList<Point> fillsratinfo(Point s, int type, int typetype) {
		ArrayList<Point> temp = new ArrayList<Point>();
		// 按照类型，给对应的图形天充满 sratinfo 数组。
		int a=0, b=0;
		a = s.x;
		b = s.y;
		if(type==1){
			if(typetype==1){
				temp.add(new Point(a, b));
				temp.add(new Point(a + 1, b));
				temp.add(new Point(a + 2, b));
				temp.add(new Point(a + 3, b));
			}
			else if(typetype==2)
			{
				temp.add(new Point(a, b + 1));
				temp.add(new Point(a, b + 2));
				temp.add(new Point(a, b + 3));
				temp.add(new Point(a, b + 4));
			}
			else {
				System.out.println("出现了一种无法处理的图形。in "+type);
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
				System.out.println("出现了一种无法处理的图形。in "+type);
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

	public boolean moveright(int [][]b) {
		// 首先判断 是不是可以往左移动
		Point temp= new Point(sratpoint.x+1,sratpoint.y);
		ArrayList <Point> tt= fillsratinfo(temp,srattype,srattypetype);
		if (check(tt,b)){
			// 如果可以，右移。 如果不可以 坐标不变.
			//prt("可以向右移动，下面就右移。");
			sratinfo = tt;
			sratpoint = temp;
			return true;
		}
		return false;
	}
	
	public void prt(String str){
		System.out.println(str);
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
		// 这个函数对 根据 参数 type 的取值 对 对应的图像进行顺时针旋转。
		// 需要注意的是，如果不能旋转，则不旋转。
		// 将对应的操作情况输出在日志中。
		// 注意，我们的旋转操作值改变数组中的点的值就可以。
		// 要求对每一种旋转方式写一个子函数，在这里进行调用。
		Point t=new Point();
		// 现在只讨论 长条的旋转。
		// 下面就是按照具体的情况给 arrayList 赋值。
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
		else if(srattype ==2){
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

	// 检查这些点是不是合法的。检查包括两方面
	// 游戏主屏上已存在物块了。 物块出界了。
	public boolean check(ArrayList<Point> temp,int[][]b) {
		// 如果 temp 中有一个点在 b[][] 中，那么返回false；
		// 如果 temp 中有一个点在 10*15 这个区域之外，返回false
		for(int i=0; i<temp.size();i++){
			int x=temp.get(i).x;
			int y = temp.get(i).y;
			if( x>=vwidth || x<0 || y>=vheight )	return false;
			if( x>=0 && x<vwidth && y<vheight){	
				if(y>=0) if( b[x][y]==1 ) return false;
			}
			else prt("这个点是在屏幕外的，信息来自 check in srat.java");
		}
		// 如果没有上述两条情况，那么返回true；
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
			if(srattypetype==1) value=srattypetype+1;
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
		// level 表明游戏的级别，游戏的级别决定了 speed，这个参数
		// 由游戏主方在使用的时候给出。
		Point temp = new Point(sratpoint.x,sratpoint.y+1);
		ArrayList<Point> temparray = new ArrayList<Point>();
		temparray =fillsratinfo(temp,srattype,srattypetype);
		if(check(temparray,b)){
			// 如果可以移动，那么更新当前物块的坐标。
			//prt("可以向下走动，马上更新坐标。");
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