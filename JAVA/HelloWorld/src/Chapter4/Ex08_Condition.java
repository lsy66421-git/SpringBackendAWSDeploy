package Chapter4;

public class Ex08_Condition {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// 조건연산자, 삼항연산자
		// 조건식 ? 참일 때 실행 : 거짓일 때 실행
		int age = 10;
		System.out.println(age >= 10 ? "참" : "거짓");
		
		int num1 = 100;
		int num2 = 200;
		int big = num1 > num2 ? num1 : num2;
		System.out.println("더 큰 수 : " + big);
		
		int small = num1 > num2 ? num2 : num1;
		System.out.println("더 작은 수 : " + small);
		
		// 두 숫자의 차이를 구하는 삼항 연산자, 마이너스 값이 나오지 않도록
		int diff = num1 > num2 ? num1-num2 : num2-num1;
		System.out.println("두 수의 차이 : "+diff);
		
		
		
	}
}
