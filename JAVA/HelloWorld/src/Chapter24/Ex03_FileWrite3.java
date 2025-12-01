package Chapter24;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class Ex03_FileWrite3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		파일 관련 스트림 사용시 반드시 예외처리가 필요함.
//		try ~ with ~ resource 문
//		try 괄호안에서 선언한 클래스는 자동으로 close()를 실행하는 문법
		try(OutputStream out = new FileOutputStream("data2.txt");) {
//			파일을 생성하는 코드
//			바이트로 65는 메모장에서 A를 의미함
//			write()를 이용하여 data.txt에 65를 저장
			out.write(65);
			out.write(66);
			out.write(67);
			out.write(68);
			out.write(123);
//			파일 관련 예외는 IOException으로 대부분 처리 가능
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
