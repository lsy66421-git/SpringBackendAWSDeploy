package Chapter14;

public class Ex03_StringBuffer {
	
	public static void main(String[] args) {
		// currentTimeMillis() : 1970년1월1일부터 현재까지의 밀리초 반환
		long startTime = System.currentTimeMillis();
		System.out.println("프로그램이 시작되었습니다.");
		// StringBuffer 클래스 : 문자열을 변경하는 코드를 많이 실행해야 하는 경우 실행시간을 단축하기 위해 사용하는 클래스
		// StringBuffer는 스레드 사용 시 안전하게 변경가능
		// StringBuilder는 스레드 사용 시 데이터가 이상하게 변경되는 경우가 생김
		StringBuffer str = new StringBuffer("abcd");
		for(int i=0; i<100000; i++) {
			str.append("ab");
		}
		System.out.println("프로그램이 종료되었습니다.");
		long endTime = System.currentTimeMillis();
		long time = (endTime - startTime);
		System.out.println(time + "밀리초 동안 실행했습니다.");
	}
}
