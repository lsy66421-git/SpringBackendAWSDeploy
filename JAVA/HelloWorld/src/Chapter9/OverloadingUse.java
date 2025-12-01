package Chapter9;

class Calc{
// 오버로딩 : 매개변수나 개수나 자료형을 다르게 하여
// 같은 이름의 메서드를 여러개 만드는 방식
	int add(int a, int b) {
		return a+b;
	}
	int add(int a) {
		return a+1;
	}
	double add(double a, double b) {
		return a+b;
	}
}

public class OverloadingUse {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(true);
		System.out.println("true");
		System.out.println(1);
		System.out.println(3.14);
		
		Calc calc = new Calc();
		System.out.println(calc.add(4,3.14));
	}

}
