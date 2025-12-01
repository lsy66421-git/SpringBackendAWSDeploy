package Practice4;

import java.util.Scanner;

public class Q5 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		5.정수를 10개 입력받아 배열에 저장하고 증가 순으로 정렬하여 출력하라. [목적-배열과 for문 연습] [난이도 중] 
//		 정수 10개 입력>>17 3 9 -6 77 234 5 23 -3 1 
//		   -6 -3 1 3 5 9 17 23 77 234 
		Scanner sc = new Scanner(System.in);
		int[] numArr = new int[10];
		int num =0;
		System.out.print("정수 10개 입력하세요 (입력구분은 스페이스바/입력완료는 엔트) >> ");
		for(int i=0; i<10; i++) {
			numArr[i] = sc.nextInt();
		}
		for(int i:numArr) {
			System.out.print(i+", ");
		}
		for(int i=0; i<numArr.length; i++) {
			for(int j=i+1; j<numArr.length; j++) {
				if(numArr[i]<=numArr[j]) {
					continue;
				}else {
					num = numArr[i];
					numArr[i] = numArr[j];
					numArr[j] = num;
				}
			}
		}
		System.out.println();
		for(int i:numArr) {
			System.out.print(i+", ");
		}
	}

}
