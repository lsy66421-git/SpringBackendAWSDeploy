package Chapter9;

public class UseNpc {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// 클래스명 인스턴스명 = new 생성자();
		// => 객체를 생성한다 혹은 인스턴스화 한다.
		Npc npc1 = new Npc();
		// 멤버변수(필드) name에 경비를 저장
		npc1.name = "경비";
		// 멤버변수(필드) hp에 100을 저장
		npc1.hp = 100;
//      접근제한자를 private으로 설정되어 있어 에러 발생
//		npc1.privateData = "이순영"
		System.out.println(npc1.name+":"+npc1.hp);
		npc1.say();
		npc1.name = "요리사";
		npc1.setHp(200);
		System.out.println(npc1.name+":"+npc1.hp);
		npc1.say();
		
		Npc npc3 = new Npc("상점 주인",80);
		System.out.println(npc3.name+":"+npc3.hp);
		npc3.say();
	}

}
