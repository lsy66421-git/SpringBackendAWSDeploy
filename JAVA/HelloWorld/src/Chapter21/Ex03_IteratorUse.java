package Chapter21;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Ex03_IteratorUse {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<String> list = new LinkedList<>();
		
		list.add("orange");
		list.add("apple");
		list.add("apple");
		list.add("bnana");
		
		System.out.println(list.size());
		
		for(String str : list) {
			System.out.print(str + "\t");
		}
		System.out.println();
		
		Iterator<String> itr = list.iterator();
		
		String str;
		while(itr.hasNext()) {
			str = itr.next();
			System.out.print(str + '\t');
			
			if(str.equals("apple"))
				itr.remove();
		}
		System.out.println();
		
		System.out.println(list.size());
		
		itr = list.iterator();
		
		while(itr.hasNext()) {
			System.out.print(itr.next() + '\t');
		}
		System.out.println();
	}

}
