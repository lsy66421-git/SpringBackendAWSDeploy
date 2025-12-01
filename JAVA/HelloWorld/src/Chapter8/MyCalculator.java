package Chapter8;

import java.util.Scanner;

public class MyCalculator {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyCalculator mc = new MyCalculator();
		Scanner sc = new Scanner(System.in);
		while(true) {
			mc.showMenu();
			char myChar = sc.nextLine().charAt(0);
//         myChar가 0~9사이가 아닌 경우 contine를 실행			
			if(!mc.checkNum(myChar)) {
				System.out.println("메뉴를 잘못 선택했습니다.");
				continue;
			}
//         문자데이터 : '1' - '0'
//         아스키코드 : 49 - 48 = 1
//         int로 저장하면 숫자가 저장됨			
			int num = myChar - '0';
			if(num==0) {
				break;
			}else {
				if(num>4) {
					System.out.println("메뉴를 잘못 선택했습니다.");
					continue;
				}
				System.out.println();
				System.out.print("첫 번째 숫자: ");
				int num1 = sc.nextInt();
				System.out.print("두 번째 숫자: ");
				int num2 = sc.nextInt();
//              next, nextInt 같은 뛰어쓰기로 데이터를 받는 메서드를 사용한 후
//				nextLine을 한번 실행하여 오류가 나지 않도록 설정				
				sc.nextLine();
				
				if(num==1) {
					mc.addNum(num1, num2);
				}else if(num==2) {
					mc.minusNum(num1, num2);
				}else if(num==3) {
					mc.multiplyNum(num1, num2);
				}else if(num==4) {
					mc.divideNum(num1, num2);
				}
			}
		}
		System.out.println("계산기를 종료 합니다.");
	}
	void showMenu() {
		System.out.println();
		System.out.println("메뉴를 선택해주세요");
		System.out.println("1.더하기");
		System.out.println("2.빼기");
		System.out.println("3.곱하기");
		System.out.println("4.나누기");
		System.out.println("0.끝내기");
	}
	void addNum(int num1, int num2) {
		int result = num1 + num2;
		System.out.println(num1+"+"+num2+"="+result);
	}
	void minusNum(int num1, int num2) {
		int result = num1 - num2;
		System.out.println(num1+"-"+num2+"="+result);
	}
	void multiplyNum(int num1, int num2) {
		int result = num1 * num2;
		System.out.println(num1+"X"+num2+"="+result);
	}
	void divideNum(int num1, int num2) {
		int result = num1 / num2;
		int result2 = num1 % num2;
		System.out.println(num1+"/"+num2+"="+result+"(목)");
		System.out.println(num1+"%"+num2+"="+result2+"(나머지)");
	}
	boolean checkNum(char ch) {
// 매개변수 ch가 0~9 사이의 글자인지 확인하는 if문		
		if(ch>='0' && ch<='9') {
			return true;
		}else {
			return false;
		}
		
	}
}
