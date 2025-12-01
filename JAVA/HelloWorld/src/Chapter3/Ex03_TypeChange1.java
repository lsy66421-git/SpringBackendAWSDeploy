package Chapter3;

public class Ex03_TypeChange1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int num1 = 1;
		short num2 = 2;
		// 작은 자료형에 큰 자료형을 저장할 수 없음
		num2 = (short)num1; // 강제 형변환 : 앞에 ( )를 붙여 캐스팅 한다.
		// 큰 자료형에 작은 자료형을 저장할 수 있음
		num1 = num2;
		System.out.println(num1 + num2);
		
		// 연산 시의 형변환
		int a = 10;
		byte b = 1;
		// int = int+byte : 자동형 변환 , byte -> int
		int result = a+b;
		// byte = int+byte : 자동형 변환 안됨 , int -> byte 불가
		byte result2 = (byte)(a+b); // 산술연산 하는 순간 int로 변환됨으로 괄호로 묶어서 형변환 필요

		// 실수 자료형 연산 시 형변환
		float num3 = 1.1f;
		double num4 = 2.2;
		double result3 = num3+num4;
		float result4 = (float)(num3+num4);
		System.out.println(result4);
	}

}
