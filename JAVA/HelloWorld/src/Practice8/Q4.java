package Practice8;

import java.util.HashMap;
import java.util.Scanner;

class Student {
	String name;
	String dept;
	int sno;
	double score;
	public Student(String name, String dept, int sno, double score) {
		super();
		this.name = name;
		this.dept = dept;
		this.sno = sno;
		this.score = score;
	
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public int getSno() {
		return sno;
	}

	public void setSno(int sno) {
		this.sno = sno;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	@Override
	public String toString() {
		return name + ", " + dept + ", " + sno + ", " + score;
	}
}

public class Q4 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		4. 학생정보를 나타내는 Student클래스에 이름, 학과, 학번, 학점을 저장하는 필드를 작성하라.  
//		(1) 학생 객체를 생성하고 5명을 학생정보를 ArrayList<Student>컬렉션에 저장한 후에, 
//		ArrayList<Student>의 모든학생(5명) 정보를 출력하고 학생의 이름을 입력받아 해당 학생의 학생정
//		보를 출력하는 프로그램을 작성하라. 
//		(2) ArrayList<Student> 대신, HashMap<String, Student> 해시맵을 이용하여 다시 작성하라. 해시
//		맵의 키(key)는 학생이름으로 한다. 
//		-------------------------------------------- 
//		학생이름, 학과, 학번, 학점을 입력하세요.  
//		>> 홍길동 모바일 1 4.1  
//		>> 이재문 안드로이드 2 3.9 
//		>> 김남윤 웹공학 3 3.5 
//		>> 최찬미 빅데이터 4 4.25 
//		>> 정영웅 컴퓨터공학 5 3.0 
//		---------------------------------------- 
//		이름 : 홍길동 
//		학과 : 모바일 
//		학번 : 1 
//		학점 : 4.1 
//		----------------------------- 
//		이름 : 이재문 
//		학과 : 안드로이드  
//		학번 : 2  
//		학점 : 3.9 
//		------------------------------ 
//		이름 : 김남윤 
//		학과 : 웹공학 
//		학번 : 3 
//		학점 : 3.5 
//		---------------------------- 
//		이름 : 정영웅 
//		학과 : 컴퓨터공학 
//		학번 : 5 
//		학점 : 3.0 
//		---------------------------- 
//		학생 이름 >> 최찬미 
//		최찬미, 빅데이터, 4, 4.25 
//		학생 이름 >> 이재문 
//		이재문, 안드로이드, 2, 3.9 
//		학생 이름 >> 그만 

//		List<Student> studentList = new ArrayList<>();
		HashMap<String, Student> studentMap = new HashMap<>();
		System.out.print("학생이름, 학과, 학번 및 학점을 차례로 입력하고 완료하려면 종료를 기입하세요 >> ");
		Scanner sc = new Scanner(System.in);
		while(true) {
			String name =sc.next();
			if(name.equals("종료")) {
				break;
			}
			String dept =sc.next();
			int sno = sc.nextInt();
			double score = sc.nextDouble();
			
			Student s = new Student(name,dept,sno,score);
//			studentList.add(s);
			studentMap.put(name, s);
		}
	
		System.out.println("-----------학생정보----------------");

//		for(Student s : studentList)
		for(Student s : studentMap.values())
		{
			System.out.println("이름 : " + s.name);
			System.out.println("학과 : " + s.dept);
			System.out.println("학번 : " + s.sno);
			System.out.println("학점 : " + s.score);
			System.out.println("----------------------------");
		}	
		
		Scanner scanner = new Scanner(System.in);
		while(true) {
			System.out.print("학생 이름 >> ");
			String inputName = scanner.nextLine();
			
			if(inputName.equals("그만")) {
				break;
			}
			
//			for(Student s : studentList)
			for(Student s : studentMap.values())
			{
				if(studentMap.containsKey(inputName)) {
					System.out.println(s.toString());
				}else {
					System.out.println(inputName + " 학생의 정보가 없습니다.");
				}
			}
		}
		
		System.out.println("종료 합니다.");

	}
}
