package TEST_활용_이순영;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Q3
{

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
        Map<String, Integer> customer = new HashMap<>();
        Scanner sc = new Scanner(System.in);
        System.out.println("<< 통장 관리 프로그램입니다. >>");
        while (true)
        {
            System.out.print("이름과 금액 입력>> ");
            String input = sc.nextLine().trim();
            if (input.equalsIgnoreCase("exit"))
            {
                break;
            }
            String[] inputArr = input.split(" ");
            if (inputArr.length != 2)
            {
                System.out.println("이름 금액 형식으로 입력해주세요.");
                continue;
            }
            String name = inputArr[0];
            int money;
            try
            {
            	money = Integer.parseInt(inputArr[1]);
            } catch (NumberFormatException e) 
            {
                System.out.println("금액은 정수만 입력해야 합니다.");
                continue;
            }
            int balance = customer.getOrDefault(name, 0); 
            int sumBalance = balance + money;
            customer.put(name, sumBalance);
            
            StringBuilder prt = new StringBuilder();
            prt.append("(");      
            boolean first = true;
            for (String key : customer.keySet())
            {
                if (!first) 
                {
              	  prt.append(")(");
                }
                int value = customer.get(key);
                prt.append(key).append(">").append(value).append("원");
                first = false;
            }
            prt.append(")");
            System.out.println(prt.toString());
        }
        sc.close();
        System.out.println("프로그램을 종료합니다...");
    }
}
