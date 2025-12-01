package Practice;

import java.util.Scanner;

public class Q5 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// 다음과 같이 AND와 OR의 논리 연산을 입력받아 결과를 출력하는 프로그램을 작성하라.  
		// 예를 들어 ‘true AND false’의 결과로 false를, ‘true OR false’의 결과로 true를 출력하면 된 
		// 단. if문 대신 switch문을 이용하라.
		//논리 연산을 입력하세요>>
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("논리 연산을 다음의 순서대로 입력하세요(boolean 논리연산자(and/or) boolean) >> ");
		boolean a = sc.nextBoolean();
		String op = sc.next();
		boolean b = sc.nextBoolean();
		
		switch(op) {
		case "and": case "AND": case "&&": case "&":
			System.out.println("결과 : "+(a && b));
			break;
		case "or": case "OR": case "||": case "|":
			System.out.println("결과 : "+(a || b));
			break;
		default:
			System.out.println("논리 연산을 입력하세요(boolea 논리연산자 boolea) >> ");
		}
		
	}

}
