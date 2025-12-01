package Chapter23;

import java.util.Arrays;
import java.util.List;

public class Ex05_Map {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<String> list = Arrays.asList("Apple","Banana","Orange","Melon");
		list.stream()
		// map(람다식) : 람다식을 실행하여 결과값 반환
			.map(s->s.toUpperCase()) // 대문자로 변환하여 반환
			.forEach(n->System.out.print(n+"\t"));
		System.out.println();
		list.stream()
		// map(람다식) : 람다식을 실행하여 결과값 반환
			.map(s->s.toLowerCase()) // 소문자로 변환하여 반환
			.forEach(n->System.out.print(n+"\t"));
		System.out.println();
		list.stream()
		// map(람다식) : 람다식을 실행하여 결과값 반환
			.map(s->s.length()) // 글자수 반환
			.forEach(n->System.out.print(n+"\t"));
	}

}
