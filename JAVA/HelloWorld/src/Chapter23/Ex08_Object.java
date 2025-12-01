package Chapter23;

import java.util.ArrayList;
import java.util.List;

class Student
{
	int sno;
	String name;
	int math;
	int kor;
	int eng;
	public Student(int sno, String name, int math, int kor, int eng) {
		this.sno = sno;
		this.name = name;
		this.math = math;
		this.kor = kor;
		this.eng = eng;
	}
	
}

public class Ex08_Object {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Student> studentList = new ArrayList<>();
		studentList.add(new Student(1,"이순신",90,95,100));
		studentList.add(new Student(2,"홍길동",80,75,90));
		studentList.add(new Student(1,"전우치",100,90,80));
		
		// 수학 점수의 총점 구하기
		int mathSum = studentList.stream()
				.mapToInt(haksang -> haksang.math)
				.sum();
		System.out.println("수학 점수의 합계 : " + mathSum);
		
		// 수학, 국어, 영어를 더한 값 출력하기
		int sum = studentList.stream()
				.mapToInt(person -> person.math+person.kor+person.eng)
				.sum();
		System.out.println("국영수 모든 점수의 합 : " + sum);
		
		studentList.stream()
			.mapToInt(person -> person.math+person.kor+person.eng)
			.forEach(total -> System.out.println(total));
	}

}
