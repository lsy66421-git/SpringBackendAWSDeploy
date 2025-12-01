package Chapter5;

import java.util.Scanner;

public class Ex04 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc =new Scanner(System.in);
		
		System.out.println("** 이름을 입력해 주세요.");
		String name = sc.nextLine();
		System.out.println("** 반갑습니다. 당신의 이름은 "+name+"님 입니다.");
		System.out.println();
		System.out.println("** 국어, 수학, 영어 점수를 입력해 주세요.");
		System.out.println();
		System.out.print("** 국어 점수 : ");
		int kor = sc.nextInt();
//		System.out.println();
		System.out.print("** 수학 점수 : ");
		int math = sc.nextInt();
//		System.out.println();
		System.out.print("** 영어 점수 : ");
		int eng = sc.nextInt();
		System.out.println();
		System.out.println("** "+name+"님은 국어:"+kor+"점 / 수학:"+math+"점 / 영어:"+eng+"점 입니다.");
		System.out.println();
		int total = kor+math+eng;
		System.out.println("** "+name+"님의 합계는 "+total+"점 입니다.");
		double age = total/3;
		System.out.println("** "+name+"님의 평균은 "+age+"점 입니다.");
		System.out.println();
		String 결과 = age >= 90 ? "** "+name+"님 합격 입니다." : "** "+name+"님 불합격 입니다.";
		System.out.println(결과);
	}

}
