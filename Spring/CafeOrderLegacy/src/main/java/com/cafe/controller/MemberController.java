package com.cafe.controller;

import com.cafe.entity.Member;
import com.cafe.mapper.MemberMapper;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberMapper memberMapper;

    @GetMapping("/login")
    public String loginForm() {
        return "member/login";
    }

    @PostMapping("/login")
    public String login(@RequestParam("userid") String userid, @RequestParam("pwd") String pwd, HttpSession session,
            Model model) {
        Member member = memberMapper.findByUserid(userid);
        if (member != null) {
            if (member.getPwd().equals(pwd)) {
                session.setAttribute("loginUser", member);
                System.out.println(">>> [로그인 성공] 세션에 저장된 데이터: " + member);
                return "redirect:/";

            }
        }
        model.addAttribute("msg", "아이디 또는 비밀번호가 일치하지 않습니다.");
        return "member/login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        System.out.println(">>> [로그아웃 요청] 삭제 전 세션 데이터: " + session.getAttribute("loginUser"));
        session.invalidate();
        System.out.println(">>> [로그아웃 완료] 세션이 무효화(삭제) 되었습니다.");

        return "redirect:/";
    }

    @GetMapping("/join")
    public String joinForm() {
        return "member/join";
    }

    @PostMapping("/join")
    public String join(Member member) {
        memberMapper.insert(member);
        return "redirect:/member/login";
    }
}
