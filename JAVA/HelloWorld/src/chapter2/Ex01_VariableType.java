package chapter2;

public class Ex01_VariableType {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		byte b = 127;
		short s = 32767;
		int i = 214748647;
		// 21억이 넘는 값의 경우 대문자 L을 끝에 붙인다.
		long l = 214748648L;
		System.out.println(b);
		System.out.println(s);
		System.out.println(i);
		System.out.println(l);
		
		// 실수 자료형 : float(4바이트), double(8바이트)
		double num1 = 1.0000001;
		System.out.println(num1);
		double num2 = 2.0000001;
		System.out.println(num2);
		// 자바의 부동 소수점 방식은 소수점 숫자 계산이 정확하지 않다.
		double result = num1 + num2;
		System.out.println(result);
	}

}
