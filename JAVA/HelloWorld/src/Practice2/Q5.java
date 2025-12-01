package Practice2;

public class Q5 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// 5. 반복문을 사용하여 다음 모양을 출력하는 프로그램을 만들어 보세요.
		//    * 
		//   *** 
		//  *****
		// *******
		System.out.println();
		int starNum = 21; // 최대 별의 수, 홀수
		for(int num = 1; num<=(starNum-1)/2+1; num++) {
			for(int i=1; i<=(starNum-1)/2-num+1; i++) {
				System.out.print(" ");
				}
			for(int i=1; i<=((num-1)*2+1); i++) {
				System.out.print("*");
			}
			System.out.println();
		}
	}
}
