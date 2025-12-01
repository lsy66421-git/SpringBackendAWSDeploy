package Chapter12_5;

abstract class Animal{
	abstract void doMove();
}
class Tiger extends Animal{
	@Override
	void doMove() {
		System.out.println("호랑이은 산을 달립니다.");
	}
	void swimming() {
		System.out.println("호랑이는 수영을 합니다.");
	}
}
class Lion extends Animal{
	@Override
	void doMove() {
		// TODO Auto-generated method stub
		System.out.println("사자는 들판을 달립니다.");
	}
	void hunting() {
		System.out.println("사자는 밤에 사냥을 합니다.");
	}
}

public class Ex04_Polymorphism3 {
	public static void animalChoose(Animal obj) {
		if (obj instanceof Tiger) {
		// 캐스팅을 이용하여 부모클래스 obj를 자식클래스로 변경
			Tiger tiger = (Tiger)obj;
			tiger.doMove();
			tiger.swimming();
		} else {
			Lion lion = (Lion)obj;
			lion.doMove();
			lion.hunting();
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Tiger tiger = new Tiger();
		animalChoose(tiger);
		
		Lion lion = new Lion();
		animalChoose(lion);
	}

}
