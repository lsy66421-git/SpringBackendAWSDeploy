package Chapter21;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Ex06_Set {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Set<String> set = new HashSet<>();
		set.add("orange");
		set.add("apple");
		set.add("banana");
		set.add("apple");
		set.add("melon");
		set.add("pineapple");
		set.add("melon");
		
		System.out.println("객체 수 : " + set.size());
		System.out.println("------------------------");
		
		for(Iterator<String> itr = set.iterator(); itr.hasNext();) {
			System.out.print(itr.next() + '\t');
		}
		
		System.out.println();
		System.out.println("------------------------");
		
		Iterator<String> itr = set.iterator();
		while(itr.hasNext()) {
			System.out.print(itr.next() + '\t');
//			set.remove(0); // Set은 순서가 없는 자료구조이므로 인덱스로 삭제할 수 없다.
		}
		
		set.remove("orange"); // 데이터 삭제할려면 데이터 자체를 넣어야 삭제 가능
		
		System.out.println();
		System.out.println("------------------------");
		System.out.println("객체 수 : " + set.size());

		System.out.println("------------------------");
		
		for(String s: set) {
			System.out.print(s + '\t');
		}
	}

}
