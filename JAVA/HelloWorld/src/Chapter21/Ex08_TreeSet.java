package Chapter21;

import java.util.Iterator;
import java.util.TreeSet;

public class Ex08_TreeSet {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TreeSet<String> tree = new TreeSet<>();
		// 중복 제거, 글자의 경우 사전순으로 오름차순 정렬됨.
		tree.add("홍길동");
		tree.add("전우치");
		tree.add("손오공");
		tree.add("멀린");
		tree.add("손오공");
		
		System.out.println("객체 수 : " + tree.size());
		
		Iterator<String> itr = tree.iterator();
		while(itr.hasNext())
		{
			System.out.println(itr.next().toString() + '\t');
		}
		System.out.println();
		
		TreeSet<Integer> numTree = new TreeSet<>();
		// 중복 제거, 글자의 경우 사전순으로 오름차순 정렬됨.
		numTree.add(5);
		numTree.add(2);
		numTree.add(3);
		numTree.add(1);
		numTree.add(4);
		
		System.out.println("객체 수 : " + numTree.size());
		
		for(int t: numTree)
		{
			System.out.print(t + '\t');
		}
		System.out.println();
	}

}
