package Practice3;

public class Q5 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//  5. 짝수/홀수 개수 세기
//		정수 배열에서 짝수와 홀수의 개수를 각각 구하세요. 
		int[] intArr = {1,3,12,16,11,50,36,27,60,89,100,5,6,24,55};
		
		int num = 0;
		for(int i=0; i<intArr.length; i++) {
			if(intArr[i]%2 == 0) {
				num++;
			}
		}
		System.out.println("짝수 : "+num+"개, 홀수 : "+(intArr.length-num)+"개");
	}

}
