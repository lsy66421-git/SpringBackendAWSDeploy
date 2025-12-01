package Chapter22;

class Outer1
{
	private int speed = 10;
	class MemberInner1
	{
		public void move()
		{
			System.out.println("인간형 유닛이 " + speed + "속도로 이동 합니다.");
		}
	}
	// 외부 클래스의 메서드
	public void getMarine()
	{
		// 내부 클래스를 사용할려면 선언해야 함
		MemberInner1 inner = new MemberInner1();
		// 내부 클래스 메서드 실행
		inner.move();
	}
}

public class Ex01_MemberInner {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// 일반 클래스 선언
		Outer1 out = new Outer1();
		
		out.getMarine();
		
		Outer1.MemberInner1 inner = out.new MemberInner1();
		
		inner.move();
	}

}
