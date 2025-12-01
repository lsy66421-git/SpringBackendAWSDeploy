package Chapter22;

interface Unit3
{
	void move();
}

class HumanCamp3
{
	private int speed = 10;
	
	// 클래스를 생성하여 돌려주는 메서드
	public Unit3 getMarine()
	{
		// Unit3의 move 추상 메서드를 구현 -> Marine3의 경우 지역 내부 클레스라서 밨에서는 절대 쓰이지 않음
		// -> 익명 내부 클래스로 변경하여 이름을 생략 가능
		class Marine3 implements Unit3
		{
			public void move()
			{
				System.out.println("인간형 유닛이 " + speed + "속도로 이동 합니다.");
			}
		}
		// Marine3이라는 객체를 생성하여 돌려줌
		return new Marine3();
	}
	
	public Unit3 getMarine2()
	{
		// 익명 내부 클래스를 사용하여 이름을 작성하지 않고 설정
		return new Unit3()
		{
			@Override
			public void move()
			{
				System.out.println("인간형 유닛이 " + speed + "속도로 이동 합니다.");
			}
		};
	}
	
}

public class Ex03_AnonymousInner1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HumanCamp3 hc = new HumanCamp3();
		Unit3 unit = hc.getMarine();
		unit.move();
		
		// 익명 내부 클래스를 변수 선언 시 작성하는 방식
		Unit3 unit3 = new Unit3() {
			
			@Override
			public void move() {
				// TODO Auto-generated method stub
				System.out.println("인간형 유닛이 10 속도로 이동 합니다.");
			}
		};
		unit3.move();
		
		
	}

}
