package Chapter12_5;

abstract class Human
{
	abstract void print();
}

class Man extends Human
{
	int a = 1;
	void a() {}
	void print()
	{
		System.out.println("남자 생성");
	}
}

class Woman extends Human
{
	int b = 2;
	void b() {}
	void print()
	{
		System.out.println("여자 생성");
	}
}

public class Ex01_Polymorphism2 {

	public static Human humanCreate(int kind)
	{
		if(kind == 1)
		{
//			Human m = new Man();
//			return m;
			return new Man();
		}else
		{
//			Human m = new Woman();
//			return m;
			return new Woman();
		}
	}

	public static void main(String[] args)
	{
		
		Human h1 = humanCreate(1); // Human h1 = new Man();
		h1.print();
//		System.out.println(h1.a); a변수는 부모클래스에 없기 때문에 사용할 수 없다.
//		h1.a(); a메서드는 부모클래스에 없기 때문에 사용할 수 없다.
		Human h2 = humanCreate(2); // Human h2 = new Woman();
		h2.print();
//		System.out.println(h2.b); b변수는 부모클래스에 없기 때문에 사용할 수 없다.
//		h2.b(); b메서드는 부모클래스에 없기 때문에 사용할 수 없다.
	}
}
