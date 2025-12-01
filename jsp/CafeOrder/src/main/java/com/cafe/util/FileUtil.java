package com.cafe.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

public class FileUtil {
	
	public static String uploadFile(HttpServletRequest req, String sDirectory) throws ServletException, IOException {
		// 경로가 없으면 생성하는 로직 추가
		File dir = new File(sDirectory);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		
		Part part = req.getPart("ofile");
		String partHeader = part.getHeader("content-disposition");
		String[] phArr = partHeader.split("filename=");
		String originalFileName = phArr[1].trim().replace("\"", "");
		if (!originalFileName.isEmpty()) {             
		    part.write(sDirectory + File.separator + originalFileName);
		}
		return originalFileName;
		}

	// 파일명 변경
	public static String renameFile(String sDirectory, String fileName) {
	    String ext = fileName.substring(fileName.lastIndexOf("."));
	    String now = new SimpleDateFormat("yyyyMMdd_HmsS").format(new Date());
	    String newFileName = now + ext;
	    File oldFile = new File(sDirectory + File.separator + fileName);
	    File newFile = new File(sDirectory + File.separator + newFileName);
	    oldFile.renameTo(newFile);

	    return newFileName;
	}
	// FileUtil.java 클래스 내부에 추가하세요

	// 여러 개의 파일 업로드 처리
	public static ArrayList<String> multipleFile(HttpServletRequest req, String sDirectory)
	        throws ServletException, IOException {
	    
	    // 파일명들을 저장할 리스트 생성
	    ArrayList<String> listFileName = new ArrayList<>();
	    
	    // 전송된 모든 파트(Part)를 가져옴 (input 태그들)
	    Collection<Part> parts = req.getParts();
	    
	    for(Part part : parts) {
	        // 파트의 이름(name 속성)이 "ofile"이 아니면 건너뜀
	        // (즉, 파일 입력 필드의 name이 "ofile"인 것만 처리)
	        if(!part.getName().equals("ofile"))
	            continue;     
	        // 헤더에서 파일명 추출
	        String partHeader = part.getHeader("content-disposition");
	        String[] phArr = partHeader.split("filename=");
	        String originalFileName = phArr[1].trim().replace("\"", "");        
	        // 파일명이 비어있지 않다면 (파일이 선택되었다면) 저장
	        if (!originalFileName.isEmpty()) {
	            part.write(sDirectory + File.separator + originalFileName);
	        }      
	        // 리스트에 파일명 추가
	        listFileName.add(originalFileName);
	    }  
	    // 저장된 파일명 리스트 반환
	    return listFileName;
	}
	
	// 명시한 파일을 찾아 다운로드합니다.
	public static void download(HttpServletRequest req, HttpServletResponse resp,
	                            String directory, String sfileName, String ofileName) {
	    String sDirectory = directory;
	    try {
	        // 파일을 찾아 입력 스트림 생성 
	        File file = new File(sDirectory, sfileName);
	        InputStream iStream = new FileInputStream(file);

	        // 한글 파일명 깨짐 방지
	        String client = req.getHeader("User-Agent");
	        if (client.indexOf("WOW64") == -1) { // 인터넷 익스플로러가 아닌 경우 (일반적인 판별법과는 조금 다를 수 있음)
	            ofileName = new String(ofileName.getBytes("UTF-8"), "ISO-8859-1");
	        }
	        else { // 인터넷 익스플로러인 경우
	            ofileName = new String(ofileName.getBytes("KSC5601"), "ISO-8859-1");
	        }

	        // 파일 다운로드용 응답 헤더 설정 
	        resp.reset();
	        resp.setContentType("application/octet-stream");
	        resp.setHeader("Content-Disposition",
	                       "attachment; filename=\"" + ofileName + "\"");
	        resp.setHeader("Content-Length", "" + file.length() );

	        // out.clear();  // 출력 스트림 초기화 (JSP에서 호출 시 필요할 수 있음)

	        // response 내장 객체로부터 새로운 출력 스트림 생성 
	        OutputStream oStream = resp.getOutputStream();

	        // 출력 스트림에 파일 내용 출력 
	        byte b[] = new byte[(int)file.length()];
	        int readBuffer = 0;
	        while ( (readBuffer = iStream.read(b)) > 0 ) {
	            oStream.write(b, 0, readBuffer);
	        }

	        // 입/출력 스트림 닫음 
	        iStream.close();
	        oStream.close();
	    }
	    catch (FileNotFoundException e) {
	        System.out.println("파일을 찾을 수 없습니다.");
	        e.printStackTrace();
	    }
	    catch (Exception e) {
	        System.out.println("예외가 발생하였습니다.");
	        e.printStackTrace();
	    }
	}
	
	public static void deleteFile(HttpServletRequest req, String directory, String filename) {
		String sDirectory = req.getServletContext().getRealPath(directory);
		File file = new File(sDirectory + File.separator + filename);
		if(file.exists()) {
			file.delete();
		}
	}
	
	public static void deleteFile2(String directory, String filename) {
	    File file = new File(directory + File.separator + filename);
	    if (file.exists()) {
	        file.delete();
	    }
	}
	
}
