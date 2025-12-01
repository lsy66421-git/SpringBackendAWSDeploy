package practice7;

public class Q1
{
	static class Student 
	{
	    int sno;          // 학번
	    String name;      // 이름
	    String dept;      // 학과
	    int[] scores;     // 성적 배열 (성적1, 성적2, 성적3)
	    int total;        // 총점
	    double avg;       // 평균
	    int rank;         // 등수

	    Student(int sno, String name, String dept, int score1, int score2, int score3)
	    {
	        this.sno = sno;
	        this.name = name;
	        this.dept = dept;
	        this.scores = new int[]{score1, score2, score3};
	        this.rank = 1;

	        total(); // 총점
	        average(); // 평균
	    }

	    void total() 
	    {
	        this.total = 0;
	        for (int score : this.scores)
	        {
	            this.total += score;
	        }
	    }

	    void average() 
	    {
	        this.avg = this.total / this.scores.length;
	    }

	    void print()
	    {   
	        System.out.println(" "+this.sno+"  "+this.name+ "  "+this.dept+"   "+this.scores[0]+ "   "+
	                           this.scores[1]+"    "+this.scores[2]+ "  "+this.total+ "  "+this.avg+"  "+
	                           this.rank);
	    }
	}

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
//		1. 다음과 같이 출력이 되도록 프로그램을 작성하라. 
//		클래스 명 : Student
//		필드 : 학번(sno:int), 이름(name:String), 학과(dept:String), 성적1~3(int[] socres),
//		평균(avg:double), 등수(rank: int)
//		생성자(학번, 이름, 학과, 성적1, 성적2, 성적3) 초기화하는 생성자 구현
//		총점, 평균, 석차 구하기 메소드
//		출력 메소드
//		Student[] students=new Student[3]; 사용
//		학번 이름 학과 성적1 성적2 성적3 총점 평균 석차
//		1 홍길동 영문 80 70 80 230 76.66 2
//		2 박경미 컴공 70 80 75 225 75 3
//		3 하성호 전컴 80 85 90 255 85 1
		
        Student[] students = new Student[3];

        students[0] = new Student(1, "홍길동", "영문", 80, 70, 80);
        students[1] = new Student(2, "박경미", "컴공", 70, 80, 75);
        students[2] = new Student(3, "하성호", "전컴", 80, 85, 90);

        for (int i = 0; i < students.length; i++) 
        {
            for (int j = 0; j < students.length; j++) 
            {
                if (students[i].total < students[j].total) 
                {
                    students[i].rank++;
                }
            }
        }
        
        System.out.println("학번  이름  학과  성적1  성적2  성적3  총점  평균   석차");
        for (Student student : students) 
        {
            student.print();
        }
    }
}
