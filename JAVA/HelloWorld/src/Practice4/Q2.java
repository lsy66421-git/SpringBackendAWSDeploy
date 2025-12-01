package Practice4;

import java.util.Scanner;

public class Q2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		2. 정수를 10개 입력받아 배열에 저장한 후, 배열을 검색하여 3의 배수만 골라 출력하는 프 
//		로그램을 작성하라. [목적-배열과 반복문 연습] [난이도 하] 
//		정수 10개 입력>>2 44 77 6 8 9 12 88 100 2323 
//		6 9 12 
		Scanner sc = new Scanner(System.in);
		int[] numArr = new int[10];
		System.out.print("정수 10개 입력하세요 (입력구분은 스페이스바/입력완료는 엔트) >> ");
		for(int i=0; i<10; i++) {
			numArr[i] = sc.nextInt();
		}
		System.out.print("입력 정수: ");
		for(int i:numArr) {
			System.out.print(i+", ");
		}
		System.out.println();
		System.out.print("3의 배수: ");
		for(int i:numArr) {
			if(i%3==0) {
				System.out.print(i+", ");
			}
		}
	}

}
