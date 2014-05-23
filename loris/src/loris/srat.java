package loris;

import java.util.ArrayList;
import android.graphics.Point;

/*
 * 
 */

public class srat {

	public ArrayList<Point> sratinfo = new ArrayList<Point>();

	public Point sratpoint; // ������״��һ����㡣�����ͼ�ε����ϵ㡣
	public int srattype; // ��ʾ�Ǻ��������ͼ�Ρ�
	public int srattypetype; // ��ʾ������ͼ�ε���һ�������
	
	private static final int vheight=20;
	private static final int vwidth=10;
	
	public int sratspeed; // ���ÿ��ֻ�»�һ����λ��
	public srat(forcast temp) {
		// �����ʱ�򣬵��list ���list ������ͼ�εĹؼ������Ϣ��
		// ������Щ�㣬��������Ļ�ϻ�����Ӧ��ͼ�Ρ�
		sratpoint = temp.spoint;
		srattype = temp.type;
		srattypetype = temp.typetype;
		sratspeed = 1;
		// ��� sratinfo ���顣
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

	// Ŀǰֻ������ �������������䷽��������ǰ�ڲ��ԡ�
	public ArrayList<Point> fillsratinfo(Point s, int type, int typetype) {
		ArrayList<Point> temp = new ArrayList<Point>();
		// �������ͣ�����Ӧ��ͼ������� sratinfo ���顣
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
				System.out.println("������һ���޷������ͼ�Ρ�in "+type);
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
				System.out.println("������һ���޷������ͼ�Ρ�in "+type);
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
				System.out.println("������һ���޷������ͼ�Ρ�in "+type);
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
				System.out.println("������һ���޷������ͼ�Ρ�in "+type);
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
				System.out.println("������һ���޷������ͼ�Ρ�in "+type);
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
				System.out.println("������һ���޷������ͼ�Ρ�in "+type);
			}
		}
		else if(type==7){
			if(typetype==1){
				temp.add(new Point(a,b));
				temp.add(new Point(a+1,b));
				temp.add(new Point(a,b+1));
				temp.add(new Point(a+1,b+1));
			}else {
				System.out.println("������һ���޷������ͼ�Ρ�in "+type);
			}
		}
		else {
			System.out.println("������һ���޷������ͼ�Ρ�in "+type);
		}
		return temp;
	}

	public boolean moveright(int [][]b) {
		// �����ж� �ǲ��ǿ��������ƶ�
		Point temp= new Point(sratpoint.x+1,sratpoint.y);
		ArrayList <Point> tt= fillsratinfo(temp,srattype,srattypetype);
		if (check(tt,b)){
			// ������ԣ����ơ� ��������� ���겻��.
			//prt("���������ƶ�����������ơ�");
			sratinfo = tt;
			sratpoint = temp;
			return true;
		}
		return false;
	}
	
	public void prt(String str){
		System.out.println(str);
	}
	// ��������ƶ��� ����true ���򷵻� false
	public boolean moveleft(int [][] b) {
		// ʵ�ַ��� ͬ moveright
		
		Point temp= null;
		temp = new Point(sratpoint.x-1,sratpoint.y);
		ArrayList <Point> tt= fillsratinfo(temp,srattype,srattypetype);
		if (check(tt,b)){
			// ������ԣ����ơ� ��������� ���겻��.
			//prt("���������ƶ��������������ꡣ");
			sratinfo = tt;
			sratpoint = temp;
			return true;
		}
		return false;
	}

	public Point trychange() {
		// ��������� ���� ���� type ��ȡֵ �� ��Ӧ��ͼ�����˳ʱ����ת��
		// ��Ҫע����ǣ����������ת������ת��
		// ����Ӧ�Ĳ�������������־�С�
		// ע�⣬���ǵ���ת����ֵ�ı������еĵ��ֵ�Ϳ��ԡ�
		// Ҫ���ÿһ����ת��ʽдһ���Ӻ�������������е��á�
		Point t=new Point();
		// ����ֻ���� ��������ת��
		// ������ǰ��վ��������� arrayList ��ֵ��
		// ��һ��ͼ��
		if(srattype ==1){
			if(srattypetype==1){
				t.set(sratpoint.x+1, sratpoint.y-1);
			}
			else if(srattypetype==2){
				t.set(sratpoint.x-1, sratpoint.y+1);
			}
		}
		// �ڶ���ͼ��
		else if(srattype ==2){
			if(srattypetype==1){
				t.set(sratpoint.x, sratpoint.y+1);
			}
			else if(srattypetype==2){
				t.set(sratpoint.x, sratpoint.y);	// �䵽������ ���øı�
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

	// �����Щ���ǲ��ǺϷ��ġ�������������
	// ��Ϸ�������Ѵ�������ˡ� �������ˡ�
	public boolean check(ArrayList<Point> temp,int[][]b) {
		// ��� temp ����һ������ b[][] �У���ô����false��
		// ��� temp ����һ������ 10*15 �������֮�⣬����false
		for(int i=0; i<temp.size();i++){
			int x=temp.get(i).x;
			int y = temp.get(i).y;
			if( x>=vwidth || x<0 || y>=vheight )	return false;
			if( x>=0 && x<vwidth && y<vheight){	
				if(y>=0) if( b[x][y]==1 ) return false;
			}
			else prt("�����������Ļ��ģ���Ϣ���� check in srat.java");
		}
		// ���û�����������������ô����true��
		return true;
	}

	// ���������εĶ���ӿڡ�������Ӧ���ΰ�ť�¼���
	public boolean change(int [][]b) {
		ArrayList<Point> temp = new ArrayList<Point>();
		Point t = trychange(); // �����޸�ͼ�Σ�ֻ�޸�ͼ�ε�������ꡣ
		int trytype = nexttypetype(); // �õ���ת֮���ͼ����̬��
		// ����ת֮���ͼ����ϸ��Ϣ��ԭ�������С�
		temp = fillsratinfo(t, srattype, trytype);
		// �����������������ȷ�Լ��飬���ͨ������ô��ת�������ͨ������ô�����Ρ�
		if (check(temp,b)) {
			sratinfo = temp; // ��������Ա��Σ���ô���Ρ�
			// �޸����� ��̬��ǡ�
			srattypetype = trytype;
			sratpoint.set(t.x, t.y);
			return true;
		} else{
			System.out.println("����ʧ��!");
			return false;
		}
	}

	// �� 7 ��ͼ�Σ���ôһ����̬����һ����̬���ж��塣��
	// ���ص�ǰ��̬����һ����̬��
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
		// level ������Ϸ�ļ�����Ϸ�ļ�������� speed���������
		// ����Ϸ������ʹ�õ�ʱ�������
		Point temp = new Point(sratpoint.x,sratpoint.y+1);
		ArrayList<Point> temparray = new ArrayList<Point>();
		temparray =fillsratinfo(temp,srattype,srattypetype);
		if(check(temparray,b)){
			// ��������ƶ�����ô���µ�ǰ�������ꡣ
			//prt("���������߶������ϸ������ꡣ");
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