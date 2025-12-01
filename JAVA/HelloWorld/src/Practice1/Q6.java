package Practice;

import java.util.Scanner;

public class Q6 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//  돈의 액수를 입력받아 오만원권, 만원권, 천원권, 500원짜리 동전, 100원짜리 동전, 10원 
		// 짜리 동전, 1원짜리 동전 각 몇 개로 변환되는지 출력하라. 힌트 참조. [난이도 중] 
		// 돈의 액수를 입력하세요>>
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("돈의 액수를 입력하세요>> ");
		
		int money = sc.nextInt();
		int m50000 = money/50000;
		int m10000 = (money%50000)/10000;
		int m1000 = ((money%50000)%10000)/1000;
		int m500 = (((money%50000)%10000)%1000)/500;
		int m100 = ((((money%50000)%10000)%1000)%500)/100;
		int m10 = (((((money%50000)%10000)%1000)%500)%100)/10;
		int m1 = (((((money%50000)%10000)%1000)%500)%100)%10;
		int comp = m50000*50000+m10000*10000+m1000*1000+m500*500+m100*100+m10*10+m1;
		
		System.out.print("오만원 "+m50000+"개, 만원 "+m10000+"개, 천원 "+m1000+"개, 500원 "+m500+"개, ");
		System.out.println("100원 "+m100+"개, 10원 "+m10+"개, 1원 "+m1+"개");
		System.out.println("검증 : "+comp);
		if(money == comp) {
			System.out.println("맞습니다 OK ^$^");			
		}else {System.out.println("틀립니다 NO @$@");}
	}

}
