package Practice2;

public class Q2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// 2. 구구단을 단보다 곱하는 수가 작거나 같은 경우까지만 출력하는 프로그램 
		// 을 만들어 보세요 (break 사용)
	
		for(int i=1; i<=9; i++) {
			System.out.println();
			for(int j = 1; j<=9; j++) {
				System.out.print(i+"x"+j+"="+i*j+", ");
				if(i<=j) {
					break;
				}
			}
			
		}		
		
		
//		for(int i=1; i<=9; i++) {
//			System.out.println();
//			for(int j = 1; j<=9; j++) {
//				if(i>=j) {
//					System.out.print(i+"x"+j+"="+i*j+", ");
//				}
//			}
//			
//		}
		
	}

}
