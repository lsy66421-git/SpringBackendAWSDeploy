package Chapter12;

class Unit
{
	String name; 
	int hp;
	void printUnit()
	{
		System.out.println("이름 :"+ name); 
		System.out.println("HP: "+ hp);
	}
}

class Marine extends Unit
{
	int attack;	
	void printMarine()
	{
		printUnit(); 
		System.out.println("공격력 :"+ attack);
	}
}

class Medic extends Unit
{
	int heal;
	void printMedic()
	{
		printUnit();
		System.out.println("치유량 : " + heal);
	}
}

class Firebat extends Unit
{
	int fireAttack;
	void printFirebat()
	{
		printUnit();
		System.out.println("화염량 : " + fireAttack);
	}
}

public class Ex01_MyTerran {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Marine unit1 = new Marine();
		unit1.name ="마린";
		unit1.hp = 100;
		unit1.attack = 20;
		
		Medic unit2 = new Medic();
		unit2.name ="메딕";
		unit2.hp = 120; 
		unit2.heal = 10;
		
		Firebat unit3 = new Firebat();
		unit3.name ="파이어뱃";
		unit3.hp = 130; 
		unit3.fireAttack = 30;
		
		unit1.printMarine();
		System.out.println(); 
		unit2.printMedic();
		System.out.println();
		unit3.printFirebat();
	}
}
