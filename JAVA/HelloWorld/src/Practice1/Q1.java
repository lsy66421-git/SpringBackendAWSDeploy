package Practice;

import java.util.Scanner;

public class Q1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// 두 정수를 입력받아 합을 구하여 출력하는 프로그램을 작성하라. 키보드 입력은 Scanner 
		// 클래스를 이용하라.
		// 두 정수를 입력하세요>>
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("두 정수를 입력하세요 >> ");
		System.out.print("정수1 : ");
		int num1 = sc.nextInt();
		System.out.print("장수2 : ");
		int num2 = sc.nextInt();
		System.out.println("정수1("+num1+") 더하기 "+"정수2("+num2+")은 "+(num1 + num2)+"입니다.");
		
	}

}
