package Chapter6;

import java.util.Scanner;

public class Ex02_Switch {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// switch 문법
		// switch(비교값){
		//    case 값:
		//         switch의 비교값과 case 값이 일치할 때의 실행문;
		//         break;
		//	  case 값:
		//         switch의 비교값과 case 값이 일치할 때의 실행문;
		//         break;
		//    default:
		//           일치하는 값이 없는 경우의 실행문;
		// }  --- switc가 끝나는 부분
		
		Scanner sc = new Scanner(System.in);
		System.out.println("몇일인지 입력해 주세요");
		int num = sc.nextInt();
		switch(num) {
		case 1: case 2: case 3: case 4: case 5: case 6:
		case 7: case 8: case 9: case 10:
			System.out.println(num+"일은 월초 입니다.");
			break;
		case 11: case 12: case 13: case 14: case 15: case 16:
		case 17: case 18: case 19: case 20:
			System.out.println(num+"일은 월중순 입니다.");
			break;
		case 21: case 22: case 23: case 24: case 25: case 26:
		case 27: case 28: case 29: case 30: case 31:
			System.out.println(num+"일은 월말 입니다.");
			break;
		default:
			System.out.println("일자를 입력해 주세요.");
		}
		System.out.println();
		int result = (num - 1) / 10;
		switch(result) {
		case 0:
			System.out.println(num+"일은 월초 입니다.");
			break;
		case 1:
			System.out.println(num+"일은 월중순 입니다.");
			break;
		case 2: case 3:
			System.out.println(num+"일은 월말 입니다.");
			break;
		default:
			System.out.println("일자를 입력해 주세요.");
		}
		System.out.println();
		// switch문을 이용하여 월별 일수를 출력하세요.
		// 1,3,5,7,8,10,12 = 31일
		// 4,6,9,11 = 30일
		// 2 = 29일, 유년인 걍우 28일
		System.out.println("몇월인지 입력해 주세요.");
		int month = sc.nextInt();
		switch(month) {
		case 1: case 3: case 5: case 7: case 8: case 10: case 12:
			System.out.println(month+"월의 일수는 31일 입니다.");
			break;
		case 4: case 6: case 9: case 11:
			System.out.println(month+"월의 일수는 30일 입니다.");
			break;
		case 2:
			System.out.println(month+"월의 일수는 29일이며, 윤년일 경우 28일 입니다.");
			break;
		default:
			System.out.println("1~12 사이의 월을 입력해 주세요.");
		}
		
		
		
	}

}
