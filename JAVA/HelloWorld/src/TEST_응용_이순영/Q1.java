package TEST_응용_이순영;

import java.util.Scanner;

public class Q1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("=== 숫자맞추기 게임을 시작합니다. ===");	
		int pcNum = 0;
		int userNum = 0;	
		pcNum = (int) (Math.random() * 100) + 1;
		System.out.println("컴퓨터가 숫자를 생각했습니다.");	
		if(pcNum % 2 == 0)
		{
			System.out.println("힌트는 짝수입니다.");
		}else
		{
			System.out.println("힌트는 홀수입니다.");
		}
		int inputNum = 0;
		while(true)
		{
			Scanner sc = new Scanner(System.in);
        	while (true)
        	{
                System.out.print("컴퓨터가 생각한 1~100 사이 정수 1개를 입력하세요: ");
                if (sc.hasNextInt())
                {
                    userNum = sc.nextInt();
                    if (userNum >= 1 && userNum <= 100)
                    {
                        break;
                    } else 
                    {
                        System.out.println("입력 범위(1~100)를 벗어났습니다. 다시 입력해주세요.");
                    }
                } else 
                {
                    System.out.println("정수만 입력할 수 있습니다. 다시 입력해주세요.");
                    sc.next(); 
                }
            }
            if(pcNum > userNum)
            {
            	System.out.println("더 큰 수입니다.");
            	inputNum ++;
            }else if (pcNum < userNum)
            {
            	System.out.println("더 작은 수입니다.");
            	inputNum ++;
            }else
            {
            	break;
            }             
		}
		System.out.println(inputNum+"회 만에 맞췄습니다.");
        System.out.println("=== 게임을 종료합니다. ===");
	}
}
