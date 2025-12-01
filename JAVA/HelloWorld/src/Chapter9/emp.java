package Chapter9;

import java.time.LocalDate;

public class emp {
	// 고용일
	private LocalDate hireDate;
	private int EMPNO;
	private int MGR;
	private double SAL;
	private double COMM;
	private int DEPTNO;
	private String ENAME;
	private String JOB;
	
	public LocalDate getHireDate() {
		return hireDate;
	}
	public void setHireDate(LocalDate hireDate) {
		this.hireDate = hireDate;
	}
	public int getEMPNO() {
		return EMPNO;
	}
	public void setEMPNO(int eMPNO) {
		if(eMPNO<0 || eMPNO>9999) {
			System.out.println("사원번호는 정수부 4자리까지만 입력 가능합니다.");
			this.EMPNO = 0;
			return;
		}
		EMPNO = eMPNO;
	}
	public int getMGR() {
		return MGR;
	}
	public void setMGR(int mGR) {
		System.out.println("상사의 번호는 정수부 4자리까지만 입력 가능합니다.");
		if(mGR<0 || mGR>9999) {
			this.MGR = 0;
			return;
		}
		MGR = mGR;
	}
	public double getSAL() {
		return SAL;
	}
	public void setSAL(Double sAL) {
		// sAL를 문자열 자료형으로 변경, 클래스형 Double 사용 필요
		String salToStr = sAL.toString();
		// sAL에 있는 소수점의 위치를 index에 저장
		int index = salToStr.indexOf(".");
		// sAL에 소수점 뒤에 있는 문자열만 잘라서 저장
		salToStr = salToStr.substring(index+1);
		if(sAL<0 || sAL>9999999.99 || salToStr.length()>2) {
			System.out.println("급여는 정수부 7자리 실수부 2자리까지만 입력 가능합니다.");
			this.SAL = 0.0;
			return;
		}
		SAL = sAL;
	}
	public double getCOMM() {
		return COMM;
	}
	public void setCOMM(Double cOMM) {
		// cOMM를 문자열 자료형으로 변경, 클래스형 Double 사용 필요
		String cOMMToStr = cOMM.toString();
		// cOMM에 있는 소수점의 위치를 index에 저장
		int index = cOMMToStr.indexOf(".");
		// cOMM에 소수점 뒤에 있는 문자열만 잘라서 저장
		cOMMToStr = cOMMToStr.substring(index+1);
		if(cOMM<0 || cOMM>9999999.99 || cOMMToStr.length()>2) {
			System.out.println("보너스는 정수부 7자리 실수부 2자리까지만 입력 가능합니다.");
			this.COMM = 0.0;
			return;
		}
		COMM = cOMM;
	}
	public int getDEPTNO() {
		return DEPTNO;
	}
	public void setDEPTNO(int dEPTNO) {
		System.out.println("부서번호는 정수부 2자리까지만 입력 가능합니다.");
		if(dEPTNO<0 || dEPTNO>99) {
			this.DEPTNO = 0;
			return;
		}
		DEPTNO = dEPTNO;
	}
	public String getENAME() {
		return ENAME;
	}
	public void setENAME(String eNAME) {
		if(eNAME.length()>10) {
			System.out.println("사원이름은 10글자 미만으로 입력하세요.");
			this.ENAME = "";
			return;
		}
		ENAME = eNAME;
	}
	public String getJOB() {
		return JOB;
	}
	public void setJOB(String jOB) {
		if(jOB.length()>9) {
			System.out.println("직업은 9글자 미만으로 입력하세요.");
			this.JOB = "";
			return;
		}
		JOB = jOB;
	}
	
}
