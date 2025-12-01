package TEST_활용_이순영;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Q4
{

	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
        Map<String, Integer> drinks = new HashMap<>();
        drinks.put("밀키스", 700);
        drinks.put("코카콜라", 800);
        drinks.put("펩시", 1000);
        drinks.put("칠성사이다", 1200);
                             
        System.out.println("밀키스, 코카콜라, 펩시, 칠성사이다 있습니다.(종료는 그만 입력");
 
        Scanner sc = new Scanner(System.in);
        while (true)
        {
            System.out.print("선택 >> ");
            String choice = sc.nextLine().trim();

            if (choice.equals("그만"))
            {
                break;
            }

            Integer price = drinks.get(choice);

            if (price != null)
            {
                System.out.printf(choice+"는 "+price+"원 입니다");
            } else 
            {
                System.out.println("해당 음료수 이름을 찾을 수 없습니다.");
            }
        }
        sc.close();
        System.out.println("종료합니다...");
	}
}
