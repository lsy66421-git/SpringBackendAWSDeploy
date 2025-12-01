package Chapter12;

class Unit1
{
	String name;
	int hp;
	void doMove()
	{
		System.out.println("이동축도 10으로 이동");
	}
}
class Marine1 extends Unit1
{
	int attack;
	void doMove()
	{
			//@ 부모 메서드와 같은 이름의
		super.doMove(); // 부모 메서드 호출 
		System.out.println(attack + " 공격");
	}
}
class Medic1 extends Unit1
{
	int heal;
	void doMove()
	{
		System.out.println("이동속도 8으로 이동");
		System.out.println(heal +" 치유");
	}
}

public class Ex01_MyTerran2 
{
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		Marine1 unit1 = new Marine1();
		unit1.name ="마린";
		unit1.hp = 100;
		unit1.attack = 20;
		
		Medic1 unit2 = new Medic1();
		unit2.name ="메딕";
		unit2.hp = 120; 
		unit2.heal = 10;
		
		unit1.doMove();
		System.out.println();
		unit2.doMove();
	}
}
