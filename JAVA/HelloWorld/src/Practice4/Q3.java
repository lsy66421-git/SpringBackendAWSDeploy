package Practice4;

import java.util.Scanner;

public class Q3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		3. 정수를 입력받아 짝수이면 “짝”, 홀수이면 “홀”을 출력하는 프로그램을 작성하라. 사용자 
//		가 정수를 입력하지 않는 경우에는 프로그램을 종료하라. [목적-자바의 예외 처리 연습] 
//		[난이도 하] 
//		정수를 입력하세요>>352 
//		짝수 
//		정수를 입력하세요>>java 
//		수를 입력하지 않아 프로그램을 종료합니다.
		while(true) {
			try {
				Scanner sc = new Scanner(System.in);
				System.out.print("정수를 입력하세요 (입력완료는 엔트) >> ");
				int num = sc.nextInt();
				if(num%2==0) {
					System.out.println("짝");
				}else {
					System.out.println("홀");
					}
				break;
			}catch(Exception e) {
				System.out.println("잘 못 입력 하셨내요");
			}
		}
		System.out.println("정상실행, catch문이 실행이 끝나고 나면 실행 되는 부분");
	}
}
