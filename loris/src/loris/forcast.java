package loris;

import android.graphics.Point;

public class forcast{
	public int type;
	public int typetype;
	public Point spoint;
	public forcast(){
		type =0;
		typetype =0;
		spoint = new Point(0,0);
	}
	// 自定义的一个 对象之间的赋值函数。
	public void copy(forcast other){
		this.type = other.type;
		this.typetype = other.typetype;
		this.spoint.set(other.spoint.x,other.spoint.y);
	}
}