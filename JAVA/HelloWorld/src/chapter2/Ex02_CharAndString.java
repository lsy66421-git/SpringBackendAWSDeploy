package chapter2;

public class Ex02_CharAndString {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// char 자료형 : 한글자만 저장할 수 있는 기본 자료형
		// 반드시 '(작은 따옴표)로 감싸서 값을 저장
		// 한글자씩 확인해야 하는 경우 사용
		char c = 'A'; // 문자로 저장
		char numChar = 65+1; // 숫자로 저장
		System.out.println(c);
		System.out.println(numChar);
		// String 자료형 : 여러 문자를 저장하는 class 자료형, 반드시 "(큰따옴표)를 사용
		String str = "안녕하세요";
		System.out.println(str);
	    char resultCharAt = str.charAt(3);
	    System.out.println(resultCharAt);
	    
	}

}
