package Chapter6;

public class Ex03_for1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// for반복문
		// for(1.초기값; 2/5.조건식; 4/7.증감식){
		//     3/6.조건식이 true일 때 실행할 코드;
		// }
		// 100부터 110까지 반복하는 반복문 작성
		for(int i = 100; i <= 110; i++) {
			System.out.print(i+",");
		}
		System.out.println("반복문이 끝났습니다.");
		// 2부터 9까지 반복하는 반복문 작성
		for(int i = 2; i <= 9; i++) {
			System.out.print(i+",");
		}
		System.out.println("반복문이 끝났습니다.");
		// 10부터 1까지 반복하는 반복문 작성
		for(int i = 10; i >= 1; i--) {
			System.out.print(i+",");
		}
		System.out.println("반복문이 끝났습니다.");
		// 5, 10, 15를 출력하는 반복문 작성
		for(int i = 5; i <= 15; i+=5) {
			System.out.print(i+",");
		}
		System.out.println("반복문이 끝났습니다.");
	}

}
