package components;

import java.util.ArrayList;



public class Main {

	public Main(){
		ArrayList<String> t = new ArrayList<String>();
		t.add("hello");
		t.add("hello1");
		for(String s: t){
			System.out.println(s);
		} 
	}
	
	public static void main(String args[]){
		new Main();
	}
}