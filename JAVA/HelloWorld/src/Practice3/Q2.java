package Practice3;

public class Q2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//  2. 최대값 & 최소값 찾기
//		 정수 배열이 주어졌을 때, 배열 안에서 최댓값과 최솟값을 찾아 출력하세요. 
		int[] intArr = {10,70,50,90,60,30,20,100,5};
		int maxNum = intArr[0];
		int minNum = intArr[0];
		for(int i=0; i<(intArr.length-1); i++) {
			if(maxNum<intArr[i+1]) {
				maxNum = intArr[i+1];
			}
			if(minNum>intArr[i+1]){
				minNum = intArr[i+1];
			}
		}
		System.out.println("최대값 : "+maxNum);
		System.out.println("최소값 : "+minNum);
		
	}
}
