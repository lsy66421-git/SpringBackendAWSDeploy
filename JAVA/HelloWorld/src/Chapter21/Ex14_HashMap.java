package Chapter21;

import java.util.HashMap;

public class Ex14_HashMap {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	//  HashMap<key, value> map = new HashMap<>();
		HashMap<String, String> map = new HashMap<>();
		
		map.put("홍길동", "010-1234-1443");
		map.put("전우치", "010-4321-1446");
		map.put("손오공", "010-9876-1443");
		
		System.out.println("홍길동: " + map.get("홍길동"));
		System.out.println("전우치: " + map.get("전우치"));
		System.out.println("손오공: " + map.get("손오공"));
		System.out.println();
		
		map.remove("손오공");
		
		System.out.println("손오공: " + map.get("손오공"));
	}

}
