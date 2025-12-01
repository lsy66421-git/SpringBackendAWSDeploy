package fileupload;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/13/MultiDownload.do")
public class MultiDownloadController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        
        // 1. 저장 경로 확인
        String saveDirectory = getServletContext().getRealPath("/Uploads");
        
        // 2. 선택된 파일 리스트 받기 (value="sfile::ofile" 형태)
        String[] checkFiles = req.getParameterValues("checkFiles");
        
        if (checkFiles == null || checkFiles.length == 0) {
            resp.sendRedirect("FileList.jsp");
            return;
        }

        // 3. 응답 헤더 설정 (ZIP 파일 다운로드)
        resp.setContentType("application/zip");
        resp.setHeader("Content-Disposition", "attachment; filename=\"download.zip\"");

        // 4. ZIP 출력 스트림 생성
        try (ServletOutputStream sos = resp.getOutputStream();
             ZipOutputStream zos = new ZipOutputStream(sos)) {
            
            for (String fileVal : checkFiles) {
                // 구분자(::)로 저장된 파일명과 원본 파일명 분리
                String[] names = fileVal.split("::");
                String sName = names[0]; // 서버에 저장된 이름
                String oName = names[1]; // 원본 이름 (ZIP 안에 들어갈 이름)
                
                File file = new File(saveDirectory, sName);
                
                if (file.exists()) {
                    // ZIP 항목 추가 (원본 이름으로 저장)
                    ZipEntry zipEntry = new ZipEntry(oName);
                    zos.putNextEntry(zipEntry);
                    
                    // 파일 읽어서 ZIP에 쓰기
                    try (FileInputStream fis = new FileInputStream(file);
                         BufferedInputStream bis = new BufferedInputStream(fis)) {
                        
                        byte[] buffer = new byte[1024];
                        int read;
                        while ((read = bis.read(buffer)) != -1) {
                            zos.write(buffer, 0, read);
                        }
                    }
                    zos.closeEntry();
                }
            }
            zos.finish(); // 압축 완료
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}