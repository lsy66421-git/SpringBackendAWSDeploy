package Chapter24;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.time.Duration;
import java.time.Instant;

public class Ex05_FileCopy1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String src = "C:\\Windows\\WinSxS\\amd64_microsoft-windows-userexperience-desktop_31bf3856ad364e35_10.0.19041.6280_none_fb7989bd7e762e1e\\CBS\\InputApp\\Assets\\Ninja\\CategorySticker.png"; // 원본파일
		String dst = "copy2.png";
		try(InputStream in = new FileInputStream(src);
				OutputStream out = new FileOutputStream(dst)){
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
