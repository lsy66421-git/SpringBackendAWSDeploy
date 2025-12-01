package Practice;

import java.util.Scanner;

public class Q8 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// 음료수 종류와 잔 수를 입력받으면 가격을 알려주는 프로그램을 작성하라. 에스프레소 
				// 는 2000원, 아메리카노 2500원, 카푸치노 3000원, 카페라떼 3500원이다. 
				// 커피 주문하세요>>
				
				Scanner sc = new Scanner(System.in);
				
				System.out.println("커피를 주문하세요 (1.에스프레소는 2000원, 2.아메리카노 2500원, 3.카푸치노 3000원, 4.카페라떼 3500원) >> ");
				System.out.print("주문 커피명 또는 번호 : ");
				String coffee = sc.next();
				System.out.print("주문 수량 : ");
				int count = sc.nextInt();
				
				if(coffee.equals("에스프레소") || coffee.equals("1")) {
					System.out.println("if문: 에스프레소 "+count+"잔은 "+(count*2000)+"원 입니다.");
				}else if(coffee.equals("아메리카노") || coffee.equals("2")) {
					System.out.println("if문: 아메리카노 "+count+"잔은 "+(count*2500)+"원 입니다.");
				}else if(coffee.equals("카푸치노") || coffee.equals("3")) {
					System.out.println("if문: 카푸치노 "+count+"잔은 "+(count*3000)+"원 입니다.");
				}else if(coffee.equals("카페라떼") || coffee.equals("4")) {
					System.out.println("if문: 카페라떼 "+count+"잔은 "+(count*3500)+"원 입니다.");
				}else {
					System.out.println("커피 주문하세요");
				}
				
				switch(coffee) {
				case "에스프레소": case "1":
					System.out.println("switch문: 에스프레소 "+count+"잔은 "+(count*2000)+"원 입니다.");
					break;
				case "아메리카노": case "2":
					System.out.println("switch문: 아메리카노 "+count+"잔은 "+(count*2500)+"원 입니다.");
					break;
				case "카푸치노": case "3":
					System.out.println("switch문: 카푸치노 "+count+"잔은 "+(count*3000)+"원 입니다.");
					break;
				case "카페라떼": case "4":
					System.out.println("switch문: 카페라떼 "+count+"잔은 "+(count*3500)+"원 입니다.");
					break;
				default:
					System.out.println("커피 주문하세요");
					}
				
	}

}
