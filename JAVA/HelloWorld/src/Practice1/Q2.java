package Practice;

import java.util.Scanner;

public class Q2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//한 층의 높이가 5m일 때, 건물이 몇 층인지 입력받아 높이를 출력하라.
		// 몇 층인지 입력하세요>>
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("몇 층인지 입력하세요 >> ");
		int height = sc.nextInt();
		System.out.println("이 건물의 높이는 "+(height*5)+"m 입니다.");
	}

}
