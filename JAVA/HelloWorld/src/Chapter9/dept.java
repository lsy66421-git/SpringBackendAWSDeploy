package Chapter9;

public class dept {
	// 부서 번호
	private int deptno;
	// 부서 이름
	private String danme;
	// 부서 위치
	private String loc;
	// getter,setter 자동완성
	// 1.source탭 열기
	// 2. Genarate Getter and Setter 열기
	// 3. getter와 setter를 생성할 멤버뱐수를 체크
	// 4. Genetate버튼으로 생성
	public int getDeptno() {
		return deptno;
	}
	public void setDeptno(int deptno) {
		if(deptno<0 || deptno>99) {
			this.deptno = 0;
			return;
		}
		this.deptno = deptno;
	}
	public String getDanme() {
		return danme;
	}
	public void setDanme(String danme) {
		if(danme.length()>14) {
			System.out.println("14글자 미만으로 입력하세요.");
			this.danme = "";
			return;
		}
		this.danme = danme;
	}
	public String getLoc() {
		return loc;
	}
	public void setLoc(String loc) {
		if(loc.length()>13) {
			System.out.println("13글자 미만으로 입력하세요.");
			this.loc = "";
			return;
		}
		this.loc = loc;
	}
}
