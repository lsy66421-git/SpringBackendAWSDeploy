package Practice3;

import java.util.Scanner;

public class Q1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
// 1. 배열 입력 & 출력
//		크기 5의 정수 배열을 만들고, 사용자에게 5개의 정수를 입력받아 배열에 저장한 뒤 출력하세요.
		
		int[] intArr = new int[5];
		Scanner sc = new Scanner(System.in);
		for(int i=0; i<5; i++) {
			System.out.println("정수 5개를 입력하시오."+(i+1)+"번째 >>");
			intArr[i] = sc.nextInt();
		}
		System.out.print("배열: ");
		for(int i:intArr) {
			System.out.print(i+", ");
		}
	}

}
