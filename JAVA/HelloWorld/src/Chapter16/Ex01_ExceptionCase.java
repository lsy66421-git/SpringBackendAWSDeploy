package Chapter16;

import java.util.Scanner;

public class Ex01_ExceptionCase {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		try catch 문
		try{
//			예외가 일어날 수 있는 코드 작성
			Scanner sc = new Scanner(System.in);
			int num1 = sc.nextInt();
			int num2 = 10 / num1;
			System.out.println(num2);
		}catch(InputMismatchException e) {
//			catch문은 예외 종류수에 따라 무제한 가능
//			InputMismatchException이 발생했을 때 실행하는 코드
			System.out.println("문자를 입력하거나 21억이상의 숫자를 입력하지 마세요.");
		}catch(Exception e) {
//			우에서 catch문으로 처리하지 않은 예외일 경우 실행할 코드
			System.out.println("그외의 예외가 발생했습니다.");
		}final{
//			예외 관계 없이 무조건 실행할 코드
			System.out.println("무조건 실행되는 코드 입니다.");
		}
		System.out.println("try catch문의 끝난 후 실행됩니다.");

//      컴파일 에러 : 컴파일 실행 시 발생하는 예외, 이클립스의 경우 빨간색 줄로 표시함.
//		MyBook book1 = new myBook();
	}

}

