package Chapter6;

public class Ex006_do_while {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int num = 0;
		int count = 0;
		do {
			num++;
			if(num%5 != 0) {
				continue;
			}
			if(num%7 != 0) {
				continue;
			}
			count++;
			System.out.println(num);
		}while(num<100);
		System.out.println("count: "+count);
		
		num = 0;
		count = 0;
		do {
			num++;
			if(num%5 != 0 || num%7 != 0) {
				continue;
			}
			count++;
			System.out.println(num);
		}while(num>100);
		System.out.println("count: "+count);
	}

}
