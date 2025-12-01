package practice7;

class Book
{
	private String title;
	private int pageNum;
	private String author;
	
	Book(String title,int pageNum,String author)
	{
		this.title =title;
		this.pageNum = pageNum;
		this.author = author;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}
}
	
class Magazine extends Book
{
	private String publicDate;
	
	Magazine(String title,int pageNum,String author,String publicDate) 
	{
		super(title, pageNum, author);
		this.publicDate = publicDate;
	}

	public String getPublicDate() {
		return publicDate;
	}

	public void setPublicDate(String publicDate) {
		this.publicDate = publicDate;
	}
	
	void print() 
	{
		System.out.println("책제목 : "+getTitle());
		System.out.println("책페이지수 : "+getPageNum()+"page");
		System.out.println("책저자 : "+getAuthor()+" 님");
		System.out.println("책발매일 : "+getPublicDate());
	}
}


public class Q3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		3. 일반적인 책을 나타내는 Book 클래스를 상속받아서 잡지를 나타내는 Magazine 클래스
//		를 작성하여 보자. Book 클래스는 제목, 페이지수, 저자 등의 정보를 가진다. Magazine
//		클래스는 추가로 발매일 정보를 가진다. 생성자, 접근자, 설정자를 포함하여서 각각의 클
//		래스를 작성한다. 이들 클래스들의 객체를 만들고 각 객체의 모든 정보를 출력하는 테스
//		트 클래스를 작성하라. 
		System.out.println("=======출력결과=======");
		Magazine maga1 = new Magazine("나의 일기",300,"홍길동","2025-10-16");
		maga1.print();
		Magazine maga2 = new Magazine("난중 일기",1000,"이순신","1598-11-17");
		maga2.print();
		System.out.println("====================");
	}

}
