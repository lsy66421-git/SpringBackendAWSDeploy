package Chapter15;

public class Ex11_partiallyFilled {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[][] arr = {
				{11},
				{22,33},
				{44,55,66}
		};
		for(int i=0; i<arr.length; i++) {
			for(int j=0; j<arr[i].length; j++) {
				System.out.print(arr[i][j]+" ");
			}
			System.out.println();
		}
	}

}
