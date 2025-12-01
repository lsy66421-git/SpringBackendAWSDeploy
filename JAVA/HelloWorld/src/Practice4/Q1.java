package Practice4;

import java.util.Scanner;

public class Q1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		1. 영문 소문자를 하나 입력받고 그 문자보다 알파벳 순위가 낮은 모든 문자를 출력하는 프 
//		로그램을 작성하라. [목적-2중 중첩 반복문 연습] [난이도 하] 
//		알파벳 한 문자를 입력하세요>>e 
//		abcde 
//		bcde 
//		cde 
//		de 
//		e 
//		Scanner로 문자를 읽을 때 다음과 같이 하면 된다. 
//		String s = scanner.next(); // 문자열로 읽음 
//		char c = s.charAt(0); // 문자열의 첫 번째 문자 
		System.out.print("영문자 소문자 하나를 입력하세요 >> ");
		Scanner sc = new Scanner(System.in);	
		String s = sc.next(); // 문자열로 읽음
		char c = s.charAt(0); // 문자열의 첫번째 문자
		int ascNum = (int) c;
		for(char i='a'; i<=c; i++) {
			for(char k=i; k<=c; k++) {
				System.out.print(k);
			}
			System.out.println();
		}
	}

}
