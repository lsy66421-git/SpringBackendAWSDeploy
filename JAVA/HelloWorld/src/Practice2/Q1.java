package Practice2;

public class Q1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// 구구단을 짝수단만 출력하도록 프로그램을 만들어 보세요 (continue 사용)

		for(int j=1; j<=9; j++) {
			if(j%2==0) {
				for(int i=1; i<=9; i++) {
						System.out.print(i*j+", ");
				continue;
				}
		System.out.println();
			}
		}
		
//		for(int j=1; j<=9; j++) {
//			for(int i=1; i<=9; i++) {
//				if(j%2==0) {
//					System.out.print(i*j+", ");
//				}
//			}
//		System.out.println();
//		}
	}

}
