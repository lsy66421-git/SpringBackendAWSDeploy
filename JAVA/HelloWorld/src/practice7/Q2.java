package practice7;

class Person
{
	private String name;
	private String address;
	private String tel;

	
	Person(String name, String address, String tel)
	{
		this.name = name;
		this.address = address;
		this.tel = tel;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}
}

class Customer extends Person
{
	private String customerNum;
	private int customerMile;
	
	Customer(String name, String address, String tel, String customerNum, int customerMile)
	{
		super(name, address, tel);
		this.customerNum =customerNum;
		this.customerMile = customerMile;
	}

	public String getCustomerNum() {
		return customerNum;
	}

	public void setCustomerNum(String customerNum) {
		this.customerNum = customerNum;
	}

	public int getCustomerMile() {
		return customerMile;
	}

	public void setCustomerMile(int customerMile) {
		this.customerMile = customerMile;
	}
	
}

public class Q2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		2. Person 클래스를 설계하라. Person 클래스는 이름, 주소, 전화 번호를 필드로 가진다. 
//		하나 이상의 생성자를 정의하고 각 필드에 대하여 접근자와 설정자 메소드를 작성하라. 
//		이어서 Person을 상속받아서 Customer를 작성하여 보자. Customer는 고객 번호와 
//		마일리지를 필드로 가지고 있다. 한 개 이상의 생성자를 작성하고 적절한 접근자 메소드와 
//		설정자 메소드를 작성한다. 이들 클래스들의 객체를 만들고 각 객체의 모든 정보를 
//		출력하는 테스트 클래스를 작성하라.
		
		Person person = new Person("홍길동","부산진구 동성로","010-1234-4567");
		Customer customer = new Customer("홍길동","부산진구 동성로","010-1234-4567","12345",98765);
		System.out.println("이름      주소           전화번호     고객번호  마일리지");	
		System.out.println(person.getName()+"  "+person.getAddress()+"  "+
		                   person.getTel()+"  "+customer.getCustomerNum()+"  "+customer.getCustomerMile());
	}
}
