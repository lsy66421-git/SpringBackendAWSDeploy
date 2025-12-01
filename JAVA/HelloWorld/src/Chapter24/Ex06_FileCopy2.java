package Chapter24;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;

public class Ex06_FileCopy2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String src = "C:\\Users\\it\\Documents\\카카오톡 받은 파일\\이순영_수강증명서_2차_250922.pdf"; // 원본파일
		String dst = "copy3.pdf";
//		BufferedInputStream : 보조 스트림으로 생성자의 매개변수에 기반 스트림을 설정해야 함.
		try(BufferedInputStream in = new BufferedInputStream(new FileInputStream(src));
				BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(dst))){
			Instant start = Instant.now();
			int data;
			while(true) {
				data = in.read();
				if(data == -1) break;
				out.write(data);
			}
			Instant end = Instant.now();
			System.out.println("복사에 걸린 시간 : "
					+ Duration.between(start, end).toMillis());
		}catch(IOException e) {
			// 예외 메시지를 출력하여 오류 원인을 확인합니다.
		    System.err.println("파일 복사 중 오류 발생:");
		    e.printStackTrace();
		}
	}

}
