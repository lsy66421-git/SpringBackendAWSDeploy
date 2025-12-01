package practice7;

class Phone
{
	private String phoneName;
	private String maker;
	private int price;
	private String genetation;
	
	public Phone(String phoneName, String maker, int price, String genetation) {
		this.phoneName = phoneName;
		this.maker = maker;
		this.price = price;
		this.genetation = genetation;
	}
	public String getPhoneName() {
		return phoneName;
	}
	public void setPhoneName(String phoneName) {
		this.phoneName = phoneName;
	}
	public String getMaker() {
		return maker;
	}
	public void setMaker(String maker) {
		this.maker = maker;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getGenetation() {
		return genetation;
	}
	public void setGenetation(String genetation) {
		this.genetation = genetation;
	}
}
class SmartPhone extends Phone
{
	private String os;
	private int osVer;
	private int MemoryValue;
	private String cameraOx;
	private String bluetoothOx;

	public SmartPhone(String phoneName, String maker, int price, String genetation, String os, int osVer, int memoryValue,
			String cameraOx, String bluetoothOx) {
		super(phoneName, maker, price, genetation);
		this.os = os;
		this.osVer = osVer;
		MemoryValue = memoryValue;
		this.cameraOx = cameraOx;
		this.bluetoothOx = bluetoothOx;
	}

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public int getOsVer() {
		return osVer;
	}

	public void setOsVer(int osVer) {
		this.osVer = osVer;
	}

	public int getMemoryValue() {
		return MemoryValue;
	}

	public void setMemoryValue(int memoryValue) {
		MemoryValue = memoryValue;
	}

	public String getCameraOx() {
		return cameraOx;
	}

	public void setCameraOx(String cameraOx) {
		this.cameraOx = cameraOx;
	}

	public String getBluetoothOx() {
		return bluetoothOx;
	}

	public void setBluetoothOx(String bluetoothOx) {
		this.bluetoothOx = bluetoothOx;
	}

	@Override
	public String toString() {
		return "========= SmartPhone ========="
				+ "\n폰명 : " + getPhoneName()
				+ "\nMaker : " + getMaker()
				+ "\n가격 : " + getPrice() + "원"
				+ "\n통신방식 : " + getGenetation()
				+ "\n운영체제 : " + os
				+ "\n운영체제 버전 : " + osVer
				+ "\n메모리량 : " + MemoryValue + "GB"
				+ "\n카메라 유/무 (O/X) : " + cameraOx
				+ "\n불루투스 유/무 (O/X) : " + bluetoothOx
				+ "\n=============================="
				;
	}
}

public class Q5 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		5. 일반적인 휴대폰을 나타내는 Phone 클래스를 작성한다. Phone에는 제조사, 가격, 통신타
//		입(2g 또는 3g) 등의 정보가 저장되어 있다. Phone에서 상속받아서 SmartPhone 클래스
//		를 작성하여 보자. SmartPhone 클래스에는 운영체제 타입, 운영체제 버전, 내부 메모리
//		크기, 카메라 장착 여부, 블루투스 지원 여부 등의 필드가 추가된다. 생성자, 접근자, 설
//		정자를 포함하여서 각각의 클래스를 작성한다. 이들 클래스들의 객체를 만들고 각 객체
//		의 모든 정보를 출력하는 테스트 클래스를 작성하라. 
		
		SmartPhone smartPhone1 = new SmartPhone("캘럭시25울트라","삼성잔자",300000,"5G","안드로이드",15,64,"O","O");
		System.out.println(smartPhone1);
		SmartPhone smartPhone2 = new SmartPhone("캘럭시25폴드","삼성잔자",2500000,"5G","안드로이드",15,64,"O","O");
		System.out.println(smartPhone1);
	}

}
