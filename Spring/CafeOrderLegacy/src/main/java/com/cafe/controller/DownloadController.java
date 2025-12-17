package com.cafe.controller;

import com.cafe.mapper.MVCBoardMapper;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.*;
import java.net.URLEncoder;
import java.nio.file.Files;

@Controller
@RequiredArgsConstructor
public class DownloadController {

    private final MVCBoardMapper boardMapper;
    private final String UPLOAD_DIR = "c:\\cafe_upload\\";

    @GetMapping("/download.do")
    public void download(@RequestParam(value = "ofile", required = false) String ofile,
            @RequestParam(value = "sfile", required = false) String sfile,
            @RequestParam("idx") Long idx,
            HttpServletResponse response) throws IOException {

        // Fallback: If sfile is empty, try to fetch from DB
        if (sfile == null || sfile.trim().isEmpty()) {
            com.cafe.entity.MVCBoard board = boardMapper.selectById(idx);
            if (board != null && board.getSfile() != null && !board.getSfile().trim().isEmpty()) {
                sfile = board.getSfile();
                ofile = board.getOfile();
            }
        }

        if (sfile == null || sfile.trim().isEmpty()) {
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().print("<script>alert('첨부파일이 없는 게시물입니다.');history.back();</script>");
            return;
        }

        // Ensure directory exists (just in case)
        File uploadDir = new File(UPLOAD_DIR);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        File file = new File(UPLOAD_DIR + sfile);
        if (!file.exists()) {
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().print("<script>alert('파일이 존재하지 않습니다.');history.back();</script>");
            return;
        }

        // Update download count
        boardMapper.updateDownCount(idx);

        // Set headers for download
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition",
                "attachment; filename=\"" + URLEncoder.encode(ofile, "UTF-8").replaceAll("\\+", "%20") + "\"");
        response.setContentLength((int) file.length());

        // Stream file to client
        try (BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));
                BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream())) {
            byte[] buffer = new byte[1024];
            int read;
            while ((read = in.read(buffer)) != -1) {
                out.write(buffer, 0, read);
            }
        }
    }

    @GetMapping("/image.do")
    public void image(@RequestParam("sfile") String sfile, HttpServletResponse response) throws IOException {
        File file = new File(UPLOAD_DIR + sfile);
        if (!file.exists()) {
            return;
        }

        String mimeType = Files.probeContentType(file.toPath());
        response.setContentType(mimeType != null ? mimeType : "application/octet-stream");

        try (BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));
                BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream())) {
            byte[] buffer = new byte[1024];
            int read;
            while ((read = in.read(buffer)) != -1) {
                out.write(buffer, 0, read);
            }
        }
    }
}
