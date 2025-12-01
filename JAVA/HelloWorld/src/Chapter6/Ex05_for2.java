package Chapter6;

import java.util.Scanner;

public class Ex05_for2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// for문의 요소 생략하기
		// 1. 초기값 생략하기
		int i = 0;
		for(; i<10; i++) {
			if(i%2 == 0) {
				System.out.print(i+", ");
			}
		}
		System.out.println();
		// 2. 증감식 생략하기
		for(int j=0; j<10;) {
			if(j%2 != 0) {
				System.out.print(j+", ");
			}
			j++;
		}
		System.out.println();
		// 3. 초기값, 증감식 생략
		int k = 0;
		for(;k<10;) {
			System.out.print(k+", ");
			k++;
		}
		System.out.println();
		 // 4. 초기값, 조건식, 증감식 생략
//		for(;;) {
//				System.out.println("무한 반복");
//		}
//		Scanner sc = new Scanner(System.in);
//		while(true) {
//			System.out.println("while무한반복");
//			System.out.println("가위,바위,보 중에 입력해 주세요.");
//			System.out.println("종료를 입력하면 게임이 종료 됩니다.>>");
//			String player = sc.next();
//			if(player.equals("종료")) {
//				System.out.println("가위바위보 게임을 종료했습니다.");
//				break;  // 반복문 종료 시키는 코드
//			}
//			System.out.println("당신은 "+player+"를 냈습니다.");
//		}
		// 반복문에서 사용하는 continue문 :  5와 7의 배수 제외 출력
		for(i=0; i<20; i++) {
			if(i%5==0) {
				continue;
			}
			if(i%7==0) {
				continue;
			}
			System.out.print(i+", ");
		}

	}

}
