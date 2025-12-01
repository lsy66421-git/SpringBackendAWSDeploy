package Chapter21;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Ex19_CollectionsSearch {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<String> list = new ArrayList<>();
		list.add("홍길동");
		list.add("전우치");
		list.add("손오공");
		
		Collections.sort(list);
		
		int idx1 = Collections.binarySearch(list, "홍길동");
		System.out.println(idx1);
		
		int idx2 = Collections.binarySearch(list, "전우치");
		System.out.println(idx2);
		
		int idx3 = Collections.binarySearch(list, "멀린");
		System.out.println(idx3);
		
		// contains(찾을 데이터) : list 안에 데이터가 있으면 true, 없으면 false 반환
		System.out.println(list.contains("전우치"));
		System.out.println(list.contains("테스트"));
		
	}

}
