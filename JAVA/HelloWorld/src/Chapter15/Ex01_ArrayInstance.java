package Chapter15;

public class Ex01_ArrayInstance {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//      배열 선언 방법
//		자료형[] 변수이름 = new 자료형[개수];
		int[] array = new int[5];
		long[] longArray = new long[5];
		double[] doubleArray = new double[5];
		String[] stringArray = new String[5];
//		배열이름[위치값] = 저장할값;
//		위치값은 0부터 시작하고 개수가 5라면 4까지 5개 위치값 사용할 수 있다.
		array[0] = 10;
		array[1] = 20;
		array[2] = 30;
		array[3] = 40;
		array[4] = 50;
//		array[5] = 60; // 5번은 존재하지 않으므로 에러가 발생
//		배열 데이터 출력하기
		System.out.println(array[0]);
		System.out.println(array[1]);
		System.out.println(array[2]);
		System.out.println(array[3]);
		System.out.println(array[4]);
//		System.out.println(array[5]); // 에러 발생
		
//		배열의 선언과 동시에 데이터 저장하기
		int[] intArr = {5,10,15,20,25};
		String[] strArr = {"홍길동","전우치","이순신"};
		System.out.println(intArr[0]);
		System.out.println(intArr[2]);
		System.out.println(intArr[4]);
		System.out.println(strArr[0]+","+strArr[1]+","+strArr[2]);
		
//		배열을 사용하는 향상된 for문 (for each문)
		for(String i:strArr) {
			System.out.println(i+" ");
		}
	}	

}
