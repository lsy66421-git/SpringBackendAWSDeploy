package com.cafe.controller;

import com.cafe.entity.Member;
import com.cafe.repository.MemberRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberRepository memberRepository;

    @GetMapping("/login")
    public String loginForm() {
        return "member/login";
    }

    @PostMapping("/login")
    public String login(@RequestParam("userid") String userid, @RequestParam("pwd") String pwd, HttpSession session,
            Model model) {
        Optional<Member> memberOpt = memberRepository.findByUserid(userid);
        if (memberOpt.isPresent()) {
            Member member = memberOpt.get();
            if (member.getPwd().equals(pwd)) {
                session.setAttribute("loginUser", member);
                return "redirect:/";
            }
        }
        model.addAttribute("msg", "아이디 또는 비밀번호가 일치하지 않습니다.");
        return "member/login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    @GetMapping("/join")
    public String joinForm() {
        return "member/join";
    }

    @PostMapping("/join")
    public String join(Member member) {
        memberRepository.save(member);
        return "redirect:/member/login";
    }
}
