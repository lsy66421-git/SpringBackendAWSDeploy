package Practice8;

import java.util.HashMap;
import java.util.Scanner;

public class Q2 {

    public static void main(String[] args) {
//   	나라 이름, 수도>> Korea 서울
//		나라 이름, 수도>> USA 워싱턴
//		나라 이름, 수도>> England 런던
//		나라 이름, 수도>> France 파리
//		나라 이름, 수도>> 그만
//		수도 검색 >> France
// 		2. “그만”이 입력될 때까지 나라의 이름과 수도를 입력받아 저장하고 다시 나라의 이름을 입력하면
// 		수도를 출력하는 프로그램을 작성하시오. 다음의 해시맵을 이용하라.
    	
        HashMap<String, String> nations = new HashMap<String, String>();
        
        Scanner scanner = new Scanner(System.in);

        System.out.println("나라이름과 수도를 입력하세요. (예 : Korea 서울)");
        
        while (true) {
            System.out.print("나라 이름, 수도>> ");
            
            String country = scanner.next();
            
            if (country.equals("그만")) {
                break;
            }
            
            String capital = scanner.next();
            
            nations.put(country, capital);
        }

        while (true) {
            System.out.print("수도 검색 >> ");
            
            String searchCountry = scanner.next();
            
            if (searchCountry.equals("그만")) {
                System.out.println("End");
                break;
            }
            
            if (nations.containsKey(searchCountry)) {
                System.out.println(searchCountry + "의 수도는 " + nations.get(searchCountry));
            } else {
                System.out.println(searchCountry + " 나라는 없습니다.");
            }
        }
    }
}