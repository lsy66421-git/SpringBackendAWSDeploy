package Chapter4;

public class Ex06_Logical {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// 논리연산자
		// && : and 연산자
		System.out.println("true && true : " + (true && true));
		System.out.println("true && false : " + (true && false));
		System.out.println("false && true : " + (false && true));
		System.out.println("false && false : " + (false && false));
		int age = 11;
		int tall = 140;
		boolean ticket = true;
		System.out.println("롤러코스터 탑승 조건은 나이가 10살 이상, "
				+ "키가 130cm 이상 이고 티켓이 있어야 한다.");
		System.out.println("롤러코스터 탑승 가능 여부 : " 
				+ (age >= 10 && tall >= 130 && ticket));
		
		// || : or 연산자
		System.out.println("true || true : " + (true || true));
		System.out.println("true || false : " + (true || false));
		System.out.println("false || true : " + (false || true));
		System.out.println("false || false : " + (false || false));
		
		// ! : not 연산자
		System.out.println("! true : " + (! true));
		System.out.println("! false : " + (! false));
		System.out.println("1000미만 2000초과 계산식 작성하자");
		int x = 500;
		System.out.println("x<1000 || x>2000 :"+ (x<1000 || x>2000));
		System.out.println("!(x>=1000 && x<=2000) :"+ !(x>=1000 && x<=2000));
		
		// 시험 합격 조건 (or 연사자)
		// 국어, 수학, 영어 중 90점이 넘는 과목이 있다면 합격
		int 국어 = 90;
		int 수학 = 62;
		int 영어 = 70;
		System.out.println("시험 합격 여부 : " + (국어>= 90 || 수학>=90 || 영어>=90));
		
		//비행기 탑승 가능 여부 (and 연산자)
		// 비행기를 타려면 여권, 비행기표, 9시 이전에 도착해야 합니다.
		boolean 여권 = true;
		boolean 비행기표 = true;
		int 현재시간 = 10;
		System.out.println("비행기 탑승 가능 여부 : " + (여권 && 비행기표 && 현재시간<=9));
		
		// 논리 연산자를 사용 시 주의점
		// 최단거리평가(SCE)
		
	}

}
