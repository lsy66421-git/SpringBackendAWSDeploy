package Practice8;

import java.util.OptionalDouble;
import java.util.Scanner;
import java.util.Vector;

public class Q3 {

    public static void main(String[] args) {
        
        // 3. Vector 컬렉션을 이용하여 강수량의 평균을 유지 관리하는 프로그램을 작성하라.
        
        Vector<Integer> rainData = new Vector<>();
        Scanner sc = new Scanner(System.in);
        
        int sum = 0;

        while (true) {
            System.out.print("강수량 입력(0 입력시 종료)>>");
            int rainfall = sc.nextInt();

            if (rainfall == 0) {
                System.out.println("End");
                break;
            }
            
            rainData.add(rainfall);
            
//            sum += rainfall;
            sum = rainData.stream().mapToInt(Integer::intValue).sum();

//            for (int i = 0; i < rainData.size(); i++) {
//                System.out.print(rainData.get(i) + ", ");
//            }
            
            System.out.println(rainData);
            System.out.println();

//            int average = sum / rainData.size();
            OptionalDouble average = rainData.stream().mapToInt(Integer::intValue).average();
            System.out.println("현재 평균 : " + average);
        }
    }
}
