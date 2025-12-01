package Chapter10;

public class Ex01_MemoryInStack {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//      1. 확인하고 싶은 줄에 브레이크 포인트 설정하기 : 줄 번호 앞에 더블클릭하기
//      2. 디버그 모드로 실행하기 : 실행 버튼 왼쪽의 벌레모양 버튼이 디버그 실행 버튼
		int num1 = 10;
		int num2 = 20;
//      step into 버턴 : 현재 실행하는 줄에 메서드가 있다면 메서드 안으로 들어가서 디버그
//      step over 버튼 : 현재 실행하는 줄에 메서드를 모두 실행하고 다음줄로 넘어가는 기능
		int result = adder(num1, num2);
		System.out.println(result+"----------------------");
	}
	public static int adder(int n1, int n2) {
		int result =  n1 + n2;
		return result;
	}

}
