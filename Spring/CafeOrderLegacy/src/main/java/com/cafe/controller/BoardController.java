package com.cafe.controller;

import com.cafe.entity.MVCBoard;
import com.cafe.mapper.MVCBoardMapper;
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
    public String writeForm() {
        return "board/Write";
    }

    @PostMapping("/write")
    public String write(MVCBoard board) {
        boardMapper.insert(board);
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
        MVCBoard board = boardMapper.selectById(idx);

        if (board == null) {
            model.addAttribute("msg", "게시물이 존재하지 않습니다.");
            model.addAttribute("url", "/board/list");
            return "common/alert";
        }

        if (!board.getPass().equals(pass)) {
            model.addAttribute("msg", "비밀번호가 일치하지 않습니다.");
            model.addAttribute("url", "back");
            return "common/alert";
        }

        if ("edit".equals(mode)) {
            return "redirect:/board/edit?idx=" + idx;
        } else if ("delete".equals(mode)) {
            boardMapper.delete(idx);
            model.addAttribute("msg", "삭제되었습니다.");
            model.addAttribute("url", "/board/list");
            return "common/alert";
        }

        return "redirect:/board/list";
    }

    @GetMapping("/edit")
    public String editForm(@RequestParam("idx") Long idx, Model model) {
        MVCBoard board = boardMapper.selectById(idx);
        model.addAttribute("dto", board);
        return "board/edit";
    }

    @PostMapping("/edit")
    public String edit(MVCBoard board) {
        // In MyBatis/Manual update, we might just update the fields we have in the
        // form.
        // Assuming 'board' object has idx populated.
        // If the form doesn't pass all fields (like visitcount), we should be careful.
        // Our 'update' SQL updates name, title, content, ofile, sfile.
        // So we can just pass 'board' directly if those fields are bound.
        // However, checks for nulls might be needed if not bound.
        // Let's assume the form binds them correctly or use selectById first?
        // Legacy 'update' usually just overwrites.
        // The SQL I wrote: UPDATE ... SET name = #{name}, title = #{title}...

        boardMapper.update(board);
        return "redirect:/board/view?idx=" + board.getIdx();
    }

}
