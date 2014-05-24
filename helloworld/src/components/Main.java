package components;

import java.util.ArrayList;



public class Main {

	public Main(){
		ArrayList t = new ArrayList();
		t.add("hello");
		t.add("hello1");
		for(int i=0; i<t.size(); i++) {
			System.out.println(t.get(i));
		}
	}
	
	public static void main(String args[]){
		new Main();
	}
	
}