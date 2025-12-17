package com.cafe.controller;

import com.cafe.entity.MVCBoard;
import com.cafe.entity.Member;
import com.cafe.mapper.MVCBoardMapper;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    private final MVCBoardMapper boardMapper;
    private final String UPLOAD_DIR = "c:\\cafe_upload\\";

    @GetMapping("/list")
    public String list(Model model,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "searchField", required = false) String searchField,
            @RequestParam(value = "searchWord", required = false) String searchWord) {

        int size = 10;
        int start = page * size;

        List<MVCBoard> boardList = boardMapper.selectList(start, size, searchField, searchWord);
        int totalCount = boardMapper.selectCount(searchField, searchWord);
        int totalPages = (int) Math.ceil((double) totalCount / size);

        model.addAttribute("boardList", boardList);
        model.addAttribute("totalCount", totalCount);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("currentPage", page);
        model.addAttribute("searchField", searchField);
        model.addAttribute("searchWord", searchWord);

        return "board/list";
    }

    @GetMapping("/view")
    public String view(Model model, @RequestParam("idx") Long idx) {
        boardMapper.updateVisitCount(idx);
        MVCBoard board = boardMapper.selectById(idx);
        model.addAttribute("dto", board);
        return "board/view";
    }

    @GetMapping("/write")
    public String writeForm(HttpSession session, Model model) {
        if (session.getAttribute("loginUser") == null) {
            model.addAttribute("msg", "로그인 후 이용 가능합니다.");
            model.addAttribute("url", "/member/login");
            return "common/alert";
        }
        return "board/Write";
    }

    @PostMapping("/write")
    public String write(MVCBoard board,
            @RequestParam(value = "file", required = false) org.springframework.web.multipart.MultipartFile file,
            HttpSession session) {
        if (session.getAttribute("loginUser") == null) {
            return "redirect:/member/login";
        }

        // File upload handling
        if (file != null && !file.isEmpty()) {
            String uploadDir = "c:\\cafe_upload\\";
            java.io.File dir = new java.io.File(uploadDir);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            String originalFileName = file.getOriginalFilename();
            String ext = originalFileName.substring(originalFileName.lastIndexOf("."));
            // Shorten filename: Date + Random(3 digits) + Ext
            String savedFileName = new java.text.SimpleDateFormat("yyyyMMddHHmmss").format(new java.util.Date())
                    + "_" + new java.util.Random().nextInt(100) + ext;

            try {
                file.transferTo(new java.io.File(uploadDir + savedFileName));
                board.setOfile(originalFileName);
                board.setSfile(savedFileName);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // Password is not used, set empty (Mapper will handle default)
        board.setPass("");
        boardMapper.insert(board);
        return "redirect:/board/list";
    }

    // Removed /pass endpoints

    @GetMapping("/delete")
    public String delete(@RequestParam("idx") Long idx, HttpSession session, Model model) {
        Member loginUser = (Member) session.getAttribute("loginUser");
        if (loginUser == null) {
            model.addAttribute("msg", "로그인이 필요합니다.");
            model.addAttribute("url", "/member/login");
            return "common/alert";
        }

        MVCBoard board = boardMapper.selectById(idx);
        if (board == null) {
            model.addAttribute("msg", "게시물이 존재하지 않습니다.");
            model.addAttribute("url", "/board/list");
            return "common/alert";
        }

        // Check if user is admin or author
        // Assuming 'admin' userId is "admin" or just checking name match
        if (!"admin".equals(loginUser.getUserid()) && !loginUser.getName().equals(board.getName())) {
            model.addAttribute("msg", "본인 또는 관리자만 삭제할 수 있습니다.");
            model.addAttribute("url", "back");
            return "common/alert";
        }

        boardMapper.delete(idx);
        model.addAttribute("msg", "삭제되었습니다.");
        model.addAttribute("url", "/board/list");
        return "common/alert";
    }

    @GetMapping("/edit")
    public String editForm(@RequestParam("idx") Long idx, HttpSession session, Model model) {
        Member loginUser = (Member) session.getAttribute("loginUser");
        if (loginUser == null) {
            model.addAttribute("msg", "로그인이 필요합니다.");
            model.addAttribute("url", "/member/login");
            return "common/alert";
        }

        MVCBoard board = boardMapper.selectById(idx);
        if (board == null) {
            return "redirect:/board/list";
        }

        // Auth check
        if (!"admin".equals(loginUser.getUserid()) && !loginUser.getName().equals(board.getName())) {
            model.addAttribute("msg", "본인 또는 관리자만 수정할 수 있습니다.");
            model.addAttribute("url", "back");
            return "common/alert";
        }

        model.addAttribute("dto", board);
        return "board/edit";
    }

    @PostMapping("/edit")
    public String edit(MVCBoard board,
            @RequestParam(value = "file", required = false) org.springframework.web.multipart.MultipartFile file,
            @RequestParam(value = "prevOfile", required = false) String prevOfile,
            @RequestParam(value = "prevSfile", required = false) String prevSfile,
            HttpSession session, Model model) {

        Member loginUser = (Member) session.getAttribute("loginUser");
        if (loginUser == null) {
            return "redirect:/member/login";
        }

        MVCBoard original = boardMapper.selectById(board.getIdx());
        if (original == null) {
            return "redirect:/board/list";
        }

        if (!"admin".equals(loginUser.getUserid()) && !loginUser.getName().equals(original.getName())) {
            model.addAttribute("msg", "수정 권한이 없습니다.");
            model.addAttribute("url", "/board/list");
            return "common/alert";
        }

        // Handle File Upload
        if (file != null && !file.isEmpty()) {
            try {
                String originalFileName = file.getOriginalFilename();
                String ext = originalFileName.substring(originalFileName.lastIndexOf("."));
                // Shorten filename: Date + Random(3 digits) + Ext
                String savedFileName = new java.text.SimpleDateFormat("yyyyMMddHHmmss").format(new java.util.Date())
                        + "_" + new java.util.Random().nextInt(100) + ext;

                file.transferTo(new java.io.File(UPLOAD_DIR + savedFileName));

                board.setOfile(originalFileName);
                board.setSfile(savedFileName);
            } catch (Exception e) {
                e.printStackTrace();
                // Optionally handle upload failure
            }
        } else {
            // Keep existing file
            board.setOfile(prevOfile);
            board.setSfile(prevSfile);
        }

        boardMapper.update(board);
        return "redirect:/board/view?idx=" + board.getIdx();
    }

}
