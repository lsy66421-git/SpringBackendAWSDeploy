package TEST_활용_이순영;

import java.util.Scanner;

public class Q2
{

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		while(true)
		{
			System.out.println("단어를 ,로 구분하여 입력하시오. 종료는 exit 입력");
			String line = sc.nextLine().trim();
			if(line.equals("exit"))
			{
				System.out.println("종료 합니다.");
				break;
			}
			int count = 0;
			for(int i=0; i<line.length(); i++)
			{
				if(line.charAt(i) == ',')
				{
					count ++;
				}
			}
			System.out.println(line);
			System.out.println("어절 개수는 "+count);
		}
	}
}
