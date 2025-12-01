package Chapter22;
// 함수형 인터페이스 : 람다식을 선언하는 전용 인터페이스
// @FunctionalInterface : 함수형 인터페이스인 것을 설정하는 어노테이션
// 메서드를 단 한개만 설정할 수 있음
@FunctionalInterface
interface Unit8
{
	void move(String s);
// FunctionalInterface 어노테이션을 했기 때문에 메서드를 하나만 작성 가능
//	void run();
}
interface Calculator
{
	int add(int a, int b);
}
interface Calculator2
{
	int minus(int a, int b);
}
interface Calculator3
{
	int multi(int a, int b);
}
interface Calculator4
{
	double devide(double a, double b);
}

public class Ex08_LambdaRule1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Unit8 unit;
		// 람다식을 문법을 하나도 생략하지 않은 방식
		unit = (String s) -> {System.out.println(s);};
		// 실행할 코드 한줄이면 중괄호 { };  생략 가능
		unit = (String s) -> System.out.println(s);
		// 매개변수의 자료형 생략 가능
		unit = (s) -> System.out.println(s);
		// 매개변수가 하나이면 매개변수 괄호도 생략 가능
		unit = s -> System.out.println(s);
		
		Calculator  calc;
		// 매개변수의 자료형만 생략
		calc = (a,b) -> {return a+b;};
		// 실행코드가 한줄일 경우 중괄호오 retur을 생략
		calc = (a,b) -> a+b;
		
		Calculator2 minus = (a,b) -> a-b;
		Calculator3 multi = (a,b) -> a*b;
		Calculator4 devide = (a,b) -> a/b;
		
		System.out.println(calc.add(10, 20));
		System.out.println(minus.minus(100, 50));
		System.out.println(multi.multi(10, 100));
		System.out.println(devide.devide(2, 5));
		
	}
}
