package fileupload;

import java.io.File;
import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utils.FileUtil;

@WebServlet("/13/UploadProcess.do")
@MultipartConfig(
    maxFileSize = 1024 * 1024 * 1,
    maxRequestSize = 1024 * 1024 * 10
)
public class UploadProcess extends HttpServlet {
    private static final long serialVersionUID = 1L;
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
        	// 1. 사용자가 입력한 저장 경로 가져오기
            String saveDirectory = req.getParameter("savePath");
            // 2. 사용자가 경로를 입력하지 않았을 경우 기본 경로(프로젝트 내부) 사용
            if (saveDirectory == null || saveDirectory.trim().isEmpty()) {
                saveDirectory = getServletContext().getRealPath("/Uploads");
            }
            // 3. ★★★ 폴더 자동 생성 로직 추가 ★★★
            File dir = new File(saveDirectory);
            if (!dir.exists()) {
                // mkdirs()는 중간 경로가 없으면 그것들도 함께 생성합니다 (예: C:/A/B/C)
                boolean created = dir.mkdirs(); 
                if (created) {
                    System.out.println("폴더 생성 성공: " + saveDirectory);
                } else {
                    System.out.println("폴더 생성 실패 (권한 문제 등): " + saveDirectory);
                }
            }
            String originalFileName = FileUtil.uploadFile(req, saveDirectory);
            String savedFileName = FileUtil.renameFile(saveDirectory, originalFileName);
            insertMyFile(req, originalFileName, savedFileName);
            resp.sendRedirect("FileList.jsp");
        }
        catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("errorMessage", "파일 업로드 오류");
            req.getRequestDispatcher("FileUploadMain.jsp").forward(req, resp);
        }
    }
        private void insertMyFile(HttpServletRequest req, String oFileName, String sFileName) {
            String title = req.getParameter("title");
            String[] cateArray = req.getParameterValues("cate");
            StringBuffer cateBuf = new StringBuffer();
            if (cateArray == null) {
                cateBuf.append("선택한 항목 없음");
            } else {
                for (String s : cateArray) {
                    cateBuf.append(s + ", ");
                }
            }

            MyFileDTO dto = new MyFileDTO();
            dto.setTitle(title);
            dto.setCate(cateBuf.toString());
            dto.setOfile(oFileName);
            dto.setSfile(sFileName);

            MyFileDAO dao = new MyFileDAO();
            dao.insertFile(dto);
            dao.close();
        }
 
}
