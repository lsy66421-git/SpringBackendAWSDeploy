package com.cafe.controller;

import com.cafe.entity.MVCBoard;
import com.cafe.entity.Member;
import com.cafe.repository.MVCBoardRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class BoardController {

    private final MVCBoardRepository boardRepository;

    @GetMapping("/board/list")
    public String list(Model model,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "searchField", required = false) String searchField,
            @RequestParam(value = "searchWord", required = false) String searchWord) {

        Pageable pageable = PageRequest.of(page, 10, Sort.by("idx").descending());
        Page<MVCBoard> boardPage;

        if (searchWord != null && !searchWord.isEmpty()) {
            if ("title".equals(searchField)) {
                boardPage = boardRepository.findByTitleContaining(searchWord, pageable);
            } else if ("content".equals(searchField)) {
                boardPage = boardRepository.findByContentContaining(searchWord, pageable);
            } else {
                boardPage = boardRepository.findAll(pageable);
            }
        } else {
            boardPage = boardRepository.findAll(pageable);
        }

        model.addAttribute("boardList", boardPage.getContent());
        model.addAttribute("totalCount", boardPage.getTotalElements());
        model.addAttribute("totalPages", boardPage.getTotalPages());
        model.addAttribute("currentPage", page);
        model.addAttribute("searchField", searchField);
        model.addAttribute("searchWord", searchWord);

        return "board/list";
    }

    @GetMapping("/board/view")
    public String view(Model model, @RequestParam("idx") Long idx) {
        MVCBoard board = boardRepository.findById(idx).orElse(null);
        if (board != null) {
            board.setVisitcount(board.getVisitcount() + 1);
            boardRepository.save(board);
        }
        model.addAttribute("dto", board);
        return "board/view";
    }

    @GetMapping("/board/write")
    public String writeForm(HttpSession session, Model model) {
        if (session.getAttribute("loginUser") == null) {
            model.addAttribute("msg", "로그인 후 이용 가능합니다.");
            model.addAttribute("url", "/member/login");
            return "common/alert";
        }
        return "board/Write";
    }

    @PostMapping("/board/write")
    public String write(MVCBoard board, @RequestParam("file") MultipartFile file) {
        try {
            if (file != null && !file.isEmpty()) {
                String originalFilename = file.getOriginalFilename();
                String ext = "";
                if (originalFilename != null && originalFilename.contains(".")) {
                    ext = originalFilename.substring(originalFilename.lastIndexOf("."));
                }
                String savedFilename = new java.text.SimpleDateFormat("yyyyMMddHHmmss").format(new java.util.Date())
                        + "_" + new java.util.Random().nextInt(100) + ext;

                String uploadPath = System.getProperty("user.home") + "/Downloads";
                File uploadDir = new File(uploadPath);
                if (!uploadDir.exists()) {
                    uploadDir.mkdirs();
                }

                file.transferTo(new File(uploadPath + "/" + savedFilename));

                board.setOfile(originalFilename);
                board.setSfile(savedFilename);
            }
            board.setPass("NOPASS");

            boardRepository.save(board);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return "redirect:/board/list";
    }

    @GetMapping("/board/edit")
    public String editForm(@RequestParam("idx") Long idx, HttpSession session, Model model) {
        MVCBoard board = boardRepository.findById(idx).orElse(null);
        if (board == null) {
            return "redirect:/board/list";
        }

        Member loginUser = (Member) session.getAttribute("loginUser");
        if (!isAuthorized(loginUser, board)) {
            model.addAttribute("msg", "수정 권한이 없습니다.");
            model.addAttribute("url", "/board/view?idx=" + idx);
            return "common/alert";
        }

        model.addAttribute("dto", board);
        return "board/edit";
    }

    @PostMapping("/board/edit")
    public String edit(MVCBoard board, @RequestParam(value = "file", required = false) MultipartFile file,
            HttpSession session, Model model) {
        MVCBoard existingBoard = boardRepository.findById(board.getIdx()).orElse(null);
        if (existingBoard == null) {
            return "redirect:/board/list";
        }

        Member loginUser = (Member) session.getAttribute("loginUser");
        if (!isAuthorized(loginUser, existingBoard)) {
            model.addAttribute("msg", "수정 권한이 없습니다.");
            model.addAttribute("url", "/board/view?idx=" + board.getIdx());
            return "common/alert";
        }

        try {
            if (file != null && !file.isEmpty()) {
                String originalFilename = file.getOriginalFilename();
                String ext = "";
                if (originalFilename != null && originalFilename.contains(".")) {
                    ext = originalFilename.substring(originalFilename.lastIndexOf("."));
                }
                String savedFilename = new java.text.SimpleDateFormat("yyyyMMddHHmmss").format(new java.util.Date())
                        + "_" + new java.util.Random().nextInt(100) + ext;

                String uploadPath = System.getProperty("user.home") + "/Downloads";
                File uploadDir = new File(uploadPath);
                if (!uploadDir.exists())
                    uploadDir.mkdirs();

                file.transferTo(new File(uploadPath + "/" + savedFilename));

                existingBoard.setOfile(originalFilename);
                existingBoard.setSfile(savedFilename);
            }

            existingBoard.setName(board.getName());
            existingBoard.setTitle(board.getTitle());
            existingBoard.setContent(board.getContent());

            boardRepository.save(existingBoard);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return "redirect:/board/view?idx=" + board.getIdx();
    }

    @GetMapping("/board/delete")
    public String delete(@RequestParam("idx") Long idx, HttpSession session, Model model) {
        MVCBoard board = boardRepository.findById(idx).orElse(null);
        if (board == null) {
            return "redirect:/board/list";
        }

        Member loginUser = (Member) session.getAttribute("loginUser");
        if (!isAuthorized(loginUser, board)) {
            model.addAttribute("msg", "삭제 권한이 없습니다.");
            model.addAttribute("url", "/board/view?idx=" + idx);
            return "common/alert";
        }

        boardRepository.delete(board);
        return "redirect:/board/list";
    }

    @GetMapping("/download.do")
    public ResponseEntity<Resource> download(@RequestParam("sfile") String sfile, @RequestParam("ofile") String ofile,
            @RequestParam("idx") Long idx) throws IOException {
        String uploadPath = System.getProperty("user.home") + "/Downloads";
        Path path = Paths.get(uploadPath + "/" + sfile);

        if (!Files.exists(path)) {
            // Check legacy path
            Path legacyPath = Paths.get("C:/cafe_notice_upload/" + sfile);
            if (Files.exists(legacyPath)) {
                path = legacyPath;
            } else {
                return ResponseEntity.notFound().build();
            }
        }

        Resource resource = new InputStreamResource(Files.newInputStream(path));

        MVCBoard board = boardRepository.findById(idx).orElse(null);
        if (board != null) {
            board.setDowncount(board.getDowncount() + 1);
            boardRepository.save(board);
        }

        String encodedFileName = URLEncoder.encode(ofile, StandardCharsets.UTF_8).replaceAll("\\+", "%20");

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + encodedFileName + "\"")
                .body(resource);
    }

    @GetMapping("/image.do")
    public ResponseEntity<Resource> image(@RequestParam("sfile") String sfile) throws IOException {
        String uploadPath = System.getProperty("user.home") + "/Downloads";
        Path path = Paths.get(uploadPath + "/" + sfile);

        if (!Files.exists(path)) {
            // Check legacy path (images might also be there)
            Path legacyPath = Paths.get("C:/cafe_notice_upload/" + sfile);
            if (Files.exists(legacyPath)) {
                path = legacyPath;
            } else {
                return ResponseEntity.notFound().build();
            }
        }

        Resource resource = new InputStreamResource(Files.newInputStream(path));
        return ResponseEntity.ok()
                .body(resource);
    }

    private boolean isAuthorized(Member user, MVCBoard board) {
        if (user == null)
            return false;
        if (user.getAdmin() != null && user.getAdmin() == 1)
            return true;
        // Check matching name. board.getName() vs user.getName().
        // NOTE: Ideally we should use user.getUserId() but board stores 'name'.
        return user.getName().equals(board.getName());
    }
}
