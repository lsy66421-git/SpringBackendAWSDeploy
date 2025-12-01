package Chapter9;

public class Npc {

//  멤버변수, 필드 : 클레스 바로 아래에 적는 변수
	String name;
	int hp;
	
	private String privateData;
	
	// 기본 생성자, default 생성자
	Npc(){}
	// 생성자 자동 완성하기
	// 1. source 탭 열기
	// 2. Generate Constructor using fields 클릭
	// 3. 생성자에 추가하고 싶은 멤버변수 설정하기
	// 4. Generate 버튼을 클릭 생성자 자동 생성하기
	public Npc(String name, int hp) {
//			+super();
			this.name = name;
			this.hp = hp;		
		}
	
// 기본 생성자, default 생성자
//	Npc(){}
// 직접 작성한 생성자 : 멤버변수(필드)에 인스턴스 생성시 값을 설정하는 용도로 사용
// 1. 생성자를 하나도 만들지 않으면 기본 생성자가 자동으로 만들어짐.
// 2. 생성자를 직접 만들면 기본 생성자는 만들어지지 않고 기본생성자도
//    사용하고 싶다면 직접 만들어서 사용해야 함.
// 3. 생성자도 메서드처럼 오버롣ㅇ이 가능하여 매개변수의 개수나 자료형을 다르게 하여
//     필요한 만큼 작성할 수 있다.
//	Npc(String name, int hp){
//		// this : 인스턴스(객체) 자신을 의미한다.
//		// 멤버변수name = 매개변수name 
//		this.name = name;
//		// 멤버변수hp = 매개변수hp
//		this.hp = hp;
//	}
	
// 자기소개를 출력하는 메서드
	void say() {
		System.out.println("안녕하세요. 저는 "+name+"입니다.");
	}
//  hp 멤버변수(필드)의 데이터를 변경하는 메서드
	void setHp(int hpData) {
		hp = hpData;
	}
}
