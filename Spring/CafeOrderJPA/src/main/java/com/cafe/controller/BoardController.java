package com.cafe.controller;

import com.cafe.entity.MVCBoard;
import com.cafe.repository.MVCBoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    private final MVCBoardRepository boardRepository;

    @GetMapping("/list")
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

    @GetMapping("/view")
    public String view(Model model, @RequestParam("idx") Long idx) {
        MVCBoard board = boardRepository.findById(idx).orElse(null);
        if (board != null) {
            board.setVisitcount(board.getVisitcount() + 1);
            boardRepository.save(board);
        }
        model.addAttribute("dto", board);
        return "board/view";
    }

    @GetMapping("/write")
    public String writeForm() {
        System.out.println("DEBUG: Entering writeForm");
        return "board/Write";
    }

    @PostMapping("/write")
    public String write(MVCBoard board) {
        boardRepository.save(board);
        return "redirect:/board/list";
    }

    @GetMapping("/pass")
    public String passForm(@RequestParam("mode") String mode, @RequestParam("idx") Long idx, Model model) {
        model.addAttribute("mode", mode);
        model.addAttribute("idx", idx);
        return "board/pass";
    }

    @PostMapping("/pass")
    public String checkPass(@RequestParam("idx") Long idx, @RequestParam("mode") String mode,
            @RequestParam("pass") String pass, Model model) {
        MVCBoard board = boardRepository.findById(idx).orElse(null);

        if (board == null) {
            model.addAttribute("msg", "게시물이 존재하지 않습니다.");
            model.addAttribute("url", "/board/list");
            return "common/alert"; // You might need to create this or use a simple script page
        }

        if (!board.getPass().equals(pass)) {
            model.addAttribute("msg", "비밀번호가 일치하지 않습니다.");
            model.addAttribute("url", "back"); // Special handling in alert.jsp
            return "common/alert";
        }

        if ("edit".equals(mode)) {
            return "redirect:/board/edit?idx=" + idx;
        } else if ("delete".equals(mode)) {
            boardRepository.deleteById(idx);
            model.addAttribute("msg", "삭제되었습니다.");
            model.addAttribute("url", "/board/list");
            return "common/alert";
        }

        return "redirect:/board/list";
    }

    @GetMapping("/edit")
    public String editForm(@RequestParam("idx") Long idx, Model model) {
        MVCBoard board = boardRepository.findById(idx).orElse(null);
        model.addAttribute("dto", board);
        return "board/edit";
    }

    @PostMapping("/edit")
    public String edit(MVCBoard board) {
        MVCBoard existingBoard = boardRepository.findById(board.getIdx()).orElse(null);
        if (existingBoard != null) {
            existingBoard.setName(board.getName());
            existingBoard.setTitle(board.getTitle());
            existingBoard.setContent(board.getContent());
            existingBoard.setOfile(board.getOfile());
            existingBoard.setSfile(board.getSfile());
            boardRepository.save(existingBoard);
        }
        return "redirect:/board/view?idx=" + board.getIdx();
    }

}
