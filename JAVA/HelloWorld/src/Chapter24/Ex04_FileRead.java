package Chapter24;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Ex04_FileRead {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		data.txt에서 내용을 읽는 클래스
		try(InputStream in = new FileInputStream("data.txt")){
//			in.read() : 1바이트를 읽어 int자료형으로 반환하는 함수
//			int data = in.read();
////			int로 출력할 때
//			System.out.println(data);
////			문자열로 출력할 때
//			System.out.printf("%c \n", data);
//			data = in.read();
//			System.out.println(data);
//			System.out.printf("%c \n", data);
			while(true) {
				int data = in.read();
				if(data == -1) break;
//				System.out.println(data);
				System.out.printf("%c", data);
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
	}

}
