package Chapter3;

public class Ex01_var {

	public static void main(String[] args) {
		// 표기법 3가지
		// 카멜표기법(변수,메서드) : stringValue
		// 파스칼표기법(클래스) : StringValue
		// 언더스코어표기법(상수) : STRING_VALUE
		
		// TODO Auto-generated method stub
		// 변수 작성 방법 : 자료형 변수이름 = 저장할 값;
		// 1. int 자료형에 5를 저장하세요.
		// 2. lonh자료형에 123456780123을 저장하세요.
		// 3. String 자료형에 오늘의 날씨는 맑음을 저장하세요.
		// 5. boolean 자료형에 false를 저장하세요.
		
		int intValue = 5;
		long longValue = 123456780123L;
		String stringValue = "오늘의 날씨는 맑음";
		double doubleValue = 3.14;
		boolean booleanValue = doubleValue > intValue;
		System.out.println(intValue);
		System.out.println(longValue);
		System.out.println(stringValue);
		System.out.println(doubleValue);
		System.out.println(booleanValue);
		
	}

}
