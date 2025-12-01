package Chapter12;

class Animal
{
	// 멤버변수 : name, age
	// 메서드 : eat(), move()
	String name;
	int age;
	void eat()
	{
		System.out.println("먹고 있습니다.");
	}
	void move()
	{
		System.out.println("달려오고 있습니다");
	}
}
class Cat extends Animal
{
	Cat(String name, int age){
		this.name=name;
		this.age=age;
	}
	// 메서드 : meow() : 고양이가 야옹하고 웁니다.
	void meow()
	{
		System.out.println("고양이가 야옹하고 웁니다.");
	}
	@Override
	void eat() {
		System.out.println("천천히 먹습니다.");
	}
	@Override
	void move() {
		System.out.println("고양이는 조용히 움직입니다.");
	}
}
class Dog extends Animal
{
	Dog(String name, int age){
		this.name=name;
		this.age=age;
	}
	// 메서드 : bark() : 강아지가 멍멍하고 짖습니다.
	void bark()
	{
		System.out.println("강아지가 멍멍하고 짖습니다.");
	}
	@Override
	void eat() {
		// TODO Auto-generated method stub
		super.eat();
		System.out.println("주인의 허락이 떨어져야 먹습니다.");
	}
	@Override
	void move() {
		// TODO Auto-generated method stub
		super.move();
		System.out.println("산책할 때는 더 빠르게 움직입니다.");
	}
}

public class Ex01_1_UseAnimal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Cat cat = new Cat("고양이", 2);
		Dog dog = new Dog("강아지", 3);
		System.out.print(cat.age+"살 "+cat.name+"가 사료를 ");
		cat.eat();
		cat.move();
		cat.meow();
		System.out.print(dog.age+"살 "+dog.name+"가 고기를 ");
		dog.eat();
		dog.move();
		dog.bark();	
	}

}
