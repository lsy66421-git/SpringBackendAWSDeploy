package Chapter7;

public class Ex01_MethodType {
// main 는 프로구램 실행시 가장 처음 실행되는 부분으로 클래스와 안전히 다른 영역으로
// 실행되기 때문에 클래스 사용시 반드시 클래스를 선언하여 사용해야 함.
	public static void main(String[] args) {
		// TODO Auto-generated method stub
// 메서드를 실행하는 방법
// 1. 클래스를 선언하여 클래스 안에 작성한 메서드를 실행
//      클래스 선언		
		Ex01_MethodType mt = new Ex01_MethodType();
//		클래스 안에 있는 메서드를 실행		
		mt.print_addNum();
		System.out.println(mt.addNum(2,mt.addNum(6,7)));
// static 메서드는 class를 선언 없이 사용 가능
		showName();
	}
// 반환타입 메서드이름(매개변수){
//     메서드 실행 코드;
//     return 반환타입에 맞는 변수 혹은 값;
// }
// void : 아무것도 반환하지 않는 메서드 작성
// int, long, double, String 같은 기본자료형 및 클래스 자료형 사용
	void print_addNum() {
		System.out.println(addNum(1,5));
//      return "a";  반환타입을 void로 설정해서 return을 사용할 수 없음.
	}
// 반환타입 : int
// 매개변수 : int num1, int num2
	int addNum(int num1, int num2) {
		int result = num1 + num2;
		return result;
//		System.out.println("return보다 아래에 존재하는 코드는 에러 발생 함");
	}
// static을 메서드에 사용하면 main과 비슷하게 프로그램 시작시 메모리에 탑재하여
// 클래스에 선언하지 않아도 사용할 수 있는 메서드가 됨. 메모리 ram에 올라가 있게 도어 메모리 차지해서 가능한 사용 안함.
	static void showName() {
		System.out.println("제 이름은 홍길동 아닙니다.");
	}
}
