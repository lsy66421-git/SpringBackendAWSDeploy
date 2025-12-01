package Chapter14;

public class Ex04_String {

	public static void main(String[] args) {
		// currentTimeMillis() : 1970년1월1일부터 현재까지의 밀리초 반환
				long startTime = System.currentTimeMillis();
				System.out.println("프로그램이 시작되었습니다.");
				String str = "abcd";
				for(int i=0; i<100000; i++) {
					str += "ab";
				}
				System.out.println("프로그램이 종료되었습니다.");
				long endTime = System.currentTimeMillis();
				long time = (endTime - startTime);
				System.out.println(time + "밀리초 동안 실행했습니다.");
	}

}
