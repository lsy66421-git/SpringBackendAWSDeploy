package Chapter21;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Ex04_Converter {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<String> list = Arrays.asList("홍길동","손오공","전우치");
		list = new ArrayList<>(list);
		list.add("해리포터");
		
		for(Iterator<String> itr = list.iterator(); itr.hasNext();)
			System.out.print(itr.next() + '\t');
		System.out.println();
		
		list = new LinkedList<>(list);
		
		for(String s : list)
			System.out.print(s + '\t');
	}

}
