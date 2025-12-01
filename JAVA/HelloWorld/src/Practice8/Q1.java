package Practice8;

import java.util.HashMap;
import java.util.Vector;

public class Q1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		1. 학점(‘A’, ‘B’, ‘C’, ‘D’, ‘F’)을 컬렉션에 저장하라. 그러고 나서 컬렉션을 검색하여 학점을 점수
//		(A=4.0, B=3.0, C=2.0, D=1.0, F=0.0)로 변환하여 출력하는 프로그램을 작성하라.
//		1) Vector 컬렉션을 이용
		
//		2) HashMap 컬렉션을 이용
		
		Vector<Character> list =new Vector<>();
		
		list.add('A');
		list.add('B');
		list.add('C');
		list.add('D');
		list.add('F');
		
		System.out.println(list);
		System.out.println();
		System.out.println("------Vector------");
		for (char grade : list) 
		{
            double score = 0.0;
            switch (grade)
            {
                case 'A':
                    score = 4.0;
                    break;
                case 'B':
                    score = 3.0;
                    break;
                case 'C':
                    score = 2.0;
                    break;
                case 'D':
                    score = 1.0;
                    break;
                case 'F':
                    score = 0.0;
                    break;
            }
            System.out.println("학점 " + grade + " = 점수 " + score);
        }
		
		System.out.println();
		System.out.println("------HashMap------");
		
		HashMap<Character, Double> map = new HashMap<>();
		
		map.put('A', 4.0);
		map.put('B', 3.0);
		map.put('C', 2.0);
		map.put('D', 1.0);
		map.put('F', 0.0);
		
		for (char key : map.keySet()) {
            
            double value = map.get(key);
            
            System.out.println("학점 " + key + " = 점수 " + value);
        }
	}

}
