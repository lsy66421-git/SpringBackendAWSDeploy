package TEST_활용_이순영;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;

public class Q5
{

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		Map<String, Integer> nations = new HashMap<>();
		Scanner sc = new Scanner(System.in);
		System.out.println("나라이름과 인구를 입력하세요.(예: Korea 5000) (입력종료: 그만)");
		while (true) 
		{
			System.out.print("입력 >> ");
			String inputLine = sc.nextLine().trim();
			if (inputLine.equals("그만")) 
			{
				System.out.println("입력을 종료합니다.");
				break;
			}
			String[] parts = inputLine.split(" ");
			if (parts.length != 2) {
				System.out.println("'나라이름 인구수' 형식으로 입력해주세요.");
				continue;
			}
			String countryName = parts[0];
			int population;
			try 
			{
				population = Integer.parseInt(parts[1]);
			} catch (NumberFormatException e)
			{
				System.out.println("인구는 정수만 입력해야 합니다.");
				continue;
			}
			nations.put(countryName, population);
		}
		sc.close();
        
		Optional<Map.Entry<String, Integer>> minEntry = nations.entrySet().stream()
			.min(Map.Entry.comparingByValue()); 
		if (minEntry.isPresent()) 
		{
			String minCountry = minEntry.get().getKey();
			int minPop = minEntry.get().getValue();
			System.out.println("제일 인구가 적은 나라는 (" + minCountry + " " + minPop + ")");
		} else {
			System.out.println("제일 인구가 적은 나라는 (데이터 없음)");
		}
	}
}