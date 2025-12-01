package Chapter6;

public class Ex04_while1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// while 반복문
		// 초기값;
		// while(조건식){
		//      증감식;
		//      조건식이 참일 때 실행할 코드;
		// }
		int i = 1;
		while(i<=5) {
			System.out.print(i+", ");
			i ++;
		}
		System.out.println();
		int j = 0;
		while(j<5) {
			j ++;
			System.out.print(j+", ");
		}
		System.out.println();
		// 100부터 110까지 반복하는 반복문 작성
		int j1 = 99;
		while(j1<110) {
			j1 ++;
			System.out.print(j1+", ");
		}
		System.out.println();
		// 2부터 9까지 반복하는 반복문 작성
		int j2 = 1;
		while(j2<9) {
			j2 ++;
			System.out.print(j2+", ");
		}
		System.out.println();
		// 10부터 1까지 반복하는 반복문 작성
		int j3 = 11;
		while(j3>1) {
			j3 --;
			System.out.print(j3+", ");
		}
		System.out.println();
		// 5, 10, 15를 출력하는 반복문 작성
		int j4 = 0;
		while(j4<15) {
			j4 +=5;
			System.out.print(j4+", ");
		}
	}

}
