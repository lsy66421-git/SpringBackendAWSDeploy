package Chapter6;

import java.util.Scanner;

public class Ex01_if1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// if문의 문법
		// if(조건식1){
		//   조건식1이 true일 때 실행할 코드;
		// }else if(조건식2){
		//         조건식2이 true일 때 실행할 코드;
		// }else if(조건식3){
		//         조건식3이 true일 때 실행할 코드;
		// }else if(     ) {
		// } -
		// } -
		// }else{
		//   위의 조건식 모두가 false일 때 실행할 코드
		// }
		
		Scanner sc =new Scanner(System.in); // ctrl + shift + o
		System.out.print("점수를 입력해 주세요 >> ");
		int num = sc.nextInt();
		if(num>=90) {
			System.out.println("A");
		}else if(num >= 80) {
			System.out.println("B");
		}else if(num >= 70) {
			System.out.println("C");
		}else if(num >= 60) {
			System.out.println("D");
		}else {
			System.out.println("F");
		}
		
		
		
		
	}

}
