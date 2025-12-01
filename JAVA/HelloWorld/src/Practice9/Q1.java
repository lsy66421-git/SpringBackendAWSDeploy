package Practice9;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;

class Emp
{
	int empno;
	String ename;
	int deptno;
	int sal;
	int comm;
	public Emp(int empno, String ename,int deptno,int sal,int comm) 
	{
		this.empno = empno;
		this.ename = ename;
		this.deptno = deptno;
		this.sal = sal;
		this.comm = comm;
	}
	
	@Override
	public String toString() 
	{
		return "empno: " + empno 
				+ ", ename: " + ename 
				+ ", deptno: " + deptno 
				+ ", sal: " + sal 
				+ ", comm: " + comm ;
	}
	
	public int getSal() 
	{
		return sal;
	}
	
	public int getDeptno() 
	{
		return deptno;
	}
	
	public int getComm()
	{
		return comm;
	}
	
	public String getEname() 
	{
		return ename;
	}
}

public class Q1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Emp> empList = new ArrayList<>();
//						  사원번호, 이름, 부서번호, 급여, 커미션
		empList.add(new Emp(7369,"SMITH",20,800,0));
		empList.add(new Emp(7499,"ALLEN",30,1600,300));
		empList.add(new Emp(7521,"WARD",30,1250,500));
		empList.add(new Emp(7654,"MARTIN",30,1250,1400));
		empList.add(new Emp(7788,"SCOTT",20,3000,0));

		// stream 실습하기
		// 1. 사원들의 sal 총합을 출력해보자
		// 2. 사원들의 sal 평균을 출력해보자
		// 3. 30번 부서에 있는 사람 수를 출력해보자
		// 4. 사원들 중 가장 급여를 많이 받는 사람을 출력해보자
		// 5. 사원들 중 가장 급여를 적게 받는 사람을 출력해보자
		// 6. 사원 이름의 첫글자만 출력하고 나머지는 *로 출력해보자
		// 7. sal는 한달 급여이고 사원들의 월 평균 근무일수는 21일, 하루 근무시간은 8시간
		// 8. 30번 부서에서 가장 sal를 많이 받는 사람을 출력 해보자
		// 9. sal와 comm을 더하여 2000이상 받는 사람 수를 출력해보자
		
		// 1. 사원들의 sal 총합을 출력해보자
		int totalSal = empList.stream()
				.mapToInt(Emp::getSal)
				.sum();
		System.out.println("1. 사원들의 sal 총합: " + totalSal);

		// 2. 사원들의 sal 평균을 출력해보자
		Double avgSal = empList.stream()
				.mapToDouble(Emp::getSal)
				.average().getAsDouble();
		System.out.println("2. 사원들의 sal 평균: "+avgSal);

		// 3. 30번 부서에 있는 사람 수를 출력해보자
		long count30Dept = empList.stream()
				.filter(emp -> emp.getDeptno() == 30)
				.count();
		System.out.println("3. 30번 부서에 있는 사람 수: " + count30Dept + "명");

		// 4. 사원들 중 가장 급여를 많이 받는 사람을 출력해보자
		Optional<Emp> maxSalEmp = empList.stream()
				.max(Comparator.comparing(Emp::getSal));
		System.out.println("4. 사원들 중 가장 급여를 많이 받는 사람: ");
		maxSalEmp.ifPresent(emp -> System.out.println(emp));

		// 5. 사원들 중 가장 급여를 적게 받는 사람을 출력해보자
		Optional<Emp> minSalEmp = empList.stream()
				.min(Comparator.comparing(Emp::getSal));
		System.out.println("5. 사원들 중 가장 급여를 적게 받는 사람: ");
		maxSalEmp.ifPresent(emp -> System.out.println(emp));
		
		// 6. 사원 이름의 첫글자만 출력하고 나머지는 *로 출력해보자
		System.out.print("6. 사원 이름의 첫글자만 출력하고 나머지는 *로 출력: ");
		empList.stream()
				.map(emp -> emp.getEname().substring(0, 1) + 
						"*".repeat(emp.getEname().length() - 1))
				.forEach(name -> System.out.print(name + " "));
		System.out.println();

		// 7. sal는 한달 급여이고 사원들의 월 평균 근무일수는 21일, 하루 근무시간은 8시간일때 하루 급여와 시급을 출력 해보자
		System.out.println("7. 하루 급여와 시급 출력:");
		final int MONTH = 21;
		final int DAY = 8;
		
		empList.stream()
				.forEach(emp -> {
					double dailySal = (double) emp.getSal() / MONTH;
					double hourlySal = dailySal / DAY;
					System.out.print("이름: "+emp.getEname()+"\t"
					                +"일급: "+(int)dailySal+"\t"
							        +"시급: "+(int)hourlySal);
					System.out.println();
				});

		// 8. 30번 부서에서 가장 sal를 많이 받는 사람을 출력 해보자
		Optional<Emp> maxSal30Dept = empList.stream()
				.filter(emp -> emp.getDeptno() == 30)
				.max(Comparator.comparing(Emp::getSal));
		System.out.println("8. 30번 부서에서 가장 sal를 많이 받는 사람:");
		maxSal30Dept.ifPresent(emp -> System.out.println(emp));

		// 9. sal와 comm을 더하여 2000이상 받는 사람 수를 출력해보자
		long countOver2000 = empList.stream()
				.filter(emp -> (emp.getSal() + emp.getComm()) >= 2000)
				.count();
		System.out.println("9. sal와 comm을 더하여 2000이상 받는 사람 수: " + countOver2000 + "명");

	}
}
