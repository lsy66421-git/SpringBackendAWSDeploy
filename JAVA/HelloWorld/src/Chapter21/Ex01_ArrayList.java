package Chapter21;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Ex01_ArrayList {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		List<String> list = new ArrayList<>();
		List<String> list = new LinkedList<>();
		
		list.add("orange");
		list.add("apple");
		list.add("apple");
		list.add("bnana");
		
		System.out.println(list.size());
		
		for(int i=0; i < list.size(); i++) {
			System.out.print(list.get(i) + "\t");
		}
		System.out.println();
		
		list.remove(0);
		
		System.out.println(list.size());
		
		for(String str : list) {
			System.out.print(str + "\t");
		}
		System.out.println();
	}

}
