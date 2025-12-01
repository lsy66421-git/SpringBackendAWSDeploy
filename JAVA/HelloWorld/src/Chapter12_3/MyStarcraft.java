package Chapter12_3;
// 추상 메서드를 포함하는 클래스는 추상클래스로 작성해야 함.
abstract class Unit // 추상클래스
{
	String name;
	int hp;
	Unit(String name, int hp)
	{
		this.name = name;
		this.hp = hp;
	}
	void printUnit()
	{
		System.out.println("이름: "+name+", HP: "+hp);
	}
	abstract void doMove(); // 추상 메서드 : 실행코드를 작성하지 않고 상속을 했을 때
	// 실제 코드를 자식클래스에서 각각 오버라이드하여 실행코드를 구현하도록 만드는 메서드
}
class Marine extends Unit
{
	Marine(String name, int hp)
	{
		super(name,hp);
	}
	@Override
	void doMove() 
	{
		System.out.println("마린은 두발로 이동합니다.");
	}
}
class Zergling extends Unit
{
	Zergling(String name, int hp)
	{
		super(name,hp);
	}
	@Override
	void doMove() 
	{
		System.out.println("저글링은 네 발로 이동 합니다.");
	}
}

public class MyStarcraft 
{
	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		Marine unit1 = new Marine("마린", 50);
		unit1.doMove();
		unit1.printUnit();
		Zergling unit2 = new Zergling("저글링", 40);
		unit2.doMove();
		unit2.printUnit();
	}
}
