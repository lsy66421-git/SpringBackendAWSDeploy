package Practice;

import java.util.Scanner;

public class Q7 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// 학점이 A, B이면 “Excellent”, 학점이 C, D이면 “Good”, 학점이 F이면 “Bye”라고 출력하 
		// 는 프로그램을 작성하라. switch와 break를 활용
		// 학점을 입력하세요>>
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("학점을 입력하세요 (A, B, C, D, F)>> ");
		String hakjum = sc.next();
		
		switch(hakjum) {
		case "A": case "B": case "a": case "b":
			System.out.println("Excellent");
			break;
		case "C": case "D": case "c": case "d":
			System.out.println("Good");
			break;
		case "F": case "f":
			System.out.println("Bye");
			break;
		default:
			System.out.println("A, B, C, D, F 학점중 하나를 입력하세요");
		}

	}

}
