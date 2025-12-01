package Practice3;

public class Q3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//  3. 합계 & 평균 구하기
//		학생 점수가 들어있는 배열이 주어질 때, 점수들의 합과 평균을 구하세요. 
		int[] studentArr = {100,50,70,90,100,60,30,10,50};
		int sum = 0;
		int avg = 0;
		for(int i=1; i<studentArr.length; i++) {
			sum = sum + studentArr[i];
		}
		avg = sum/studentArr.length;
		System.out.println("합 : "+sum+" , 평균 : "+avg);
		
	}

}
