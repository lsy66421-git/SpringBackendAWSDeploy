package Practice;

import java.util.Scanner;

public class Q3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// x 값을 입력받아 y = x²-3x+7 식을 계산하여 y 값을 출력하는 프로그램을 작성하라. 
		// x 값을 입력하세요>>
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("x 값을 입력하세요 >> ");
		int x = sc.nextInt();
		System.out.println("x="+x+", "+"y="+(x*x-3*x+7));
	}

}
