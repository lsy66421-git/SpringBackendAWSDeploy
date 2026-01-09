package com.example.kiosk.controller;

import com.example.kiosk.entity.Board;
import com.example.kiosk.entity.Member;
import com.example.kiosk.entity.Role;
import com.example.kiosk.repository.BoardRepository;
import com.example.kiosk.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/board")
@RequiredArgsConstructor
public class BoardController {
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    @GetMapping
    public List<com.example.kiosk.dto.BoardResponse> getAllBoards(
            @RequestParam(value = "type", defaultValue = "NOTICE") String type,
            @AuthenticationPrincipal UserDetails userDetails) {

        if (userDetails == null)
            return List.of();
        Member member = memberRepository.findByUsername(userDetails.getUsername()).orElseThrow();

        List<Board> boards;
        if ("NOTICE".equals(type)) {
            boards = boardRepository.findAllByBoardType("NOTICE");
        } else {
            if (member.getRole() == Role.ADMIN) {
                boards = boardRepository.findAllByBoardTypeOrNull("INQUIRY");
            } else {
                boards = boardRepository.findAllByWriterIdAndBoardTypeOrNull(member.getId(), "INQUIRY");
            }
        }

        return boards.stream()
                .map(com.example.kiosk.dto.BoardResponse::from)
                .collect(java.util.stream.Collectors.toList());
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> createBoard(
            @RequestParam("title") String title,
            @RequestParam("content") String content,
            @RequestParam(value = "type") String type, // Removed defaultValue
            @RequestParam(value = "file", required = false) MultipartFile file,
            @AuthenticationPrincipal UserDetails userDetails) {

        Member member = memberRepository.findByUsername(userDetails.getUsername()).orElseThrow();

        // Permission Check
        if ("NOTICE".equals(type)) {
            if (member.getRole() != Role.ADMIN) {
                return ResponseEntity.status(403).body("Only Admin can write notices");
            }
        }

        String fileName = null;
        String filePath = null;

        if (file != null && !file.isEmpty()) {
            try {
                String uploadDir = "C:/MyKioskProject/uploads/";
                java.io.File dir = new java.io.File(uploadDir);
                if (!dir.exists())
                    dir.mkdirs();

                String fileNameOrg = file.getOriginalFilename();
                String savedName = System.currentTimeMillis() + "_" + fileNameOrg;
                java.io.File dest = new java.io.File(uploadDir + savedName);
                file.transferTo(dest);

                fileName = fileNameOrg;
                filePath = "/uploads/" + savedName;
            } catch (java.io.IOException e) {
                e.printStackTrace();
            }
        }

        Board board = Board.builder()
                .title(title)
                .content(content)
                .boardType(type)
                .writer(member)
                .fileName(fileName)
                .filePath(filePath)
                .build();

        Board saved = boardRepository.save(board);
        return ResponseEntity.ok(com.example.kiosk.dto.BoardResponse.from(saved));
    }

    @PostMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> updateBoard(@PathVariable Long id,
            @RequestParam("title") String title,
            @RequestParam("content") String content,
            @RequestParam(value = "file", required = false) MultipartFile file,
            @AuthenticationPrincipal UserDetails userDetails) {

        try {
            if (userDetails == null)
                return ResponseEntity.status(401).body("User not logged in");

            Member member = memberRepository.findByUsername(userDetails.getUsername())
                    .orElseThrow(() -> new RuntimeException("User not found"));

            Board board = boardRepository.findById(id).orElseThrow(() -> new RuntimeException("Board not found"));

            // Permission Check
            boolean isWriter = board.getWriter() != null && board.getWriter().getId().equals(member.getId());
            boolean isAdmin = member.getRole() == Role.ADMIN;

            if ("NOTICE".equals(board.getBoardType())) {
                if (!isAdmin)
                    return ResponseEntity.status(403).body("Not authorized");
            } else {
                if (!isWriter && !isAdmin)
                    return ResponseEntity.status(403).body("Not authorized");
            }

            board.setTitle(title);
            board.setContent(content);

            // File Update Logic
            if (file != null && !file.isEmpty()) {
                try {
                    String uploadDir = "C:/MyKioskProject/uploads/";
                    java.io.File dir = new java.io.File(uploadDir);
                    if (!dir.exists())
                        dir.mkdirs();

                    String fileName = file.getOriginalFilename();
                    String savedName = System.currentTimeMillis() + "_" + fileName;
                    java.io.File dest = new java.io.File(uploadDir + savedName);
                    file.transferTo(dest);

                    board.setFileName(fileName);
                    board.setFilePath("/uploads/" + savedName);
                } catch (java.io.IOException e) {
                    e.printStackTrace();
                    return ResponseEntity.status(500).body("File upload failed: " + e.getMessage());
                }
            }

            Board saved = boardRepository.save(board);
            return ResponseEntity.ok(com.example.kiosk.dto.BoardResponse.from(saved));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBoard(@PathVariable Long id,
            @AuthenticationPrincipal UserDetails userDetails) {
        Member member = memberRepository.findByUsername(userDetails.getUsername()).orElseThrow();

        Board board = boardRepository.findById(id).orElseThrow(() -> new RuntimeException("Board not found"));

        boolean isWriter = board.getWriter() != null && board.getWriter().getId().equals(member.getId());
        boolean isAdmin = member.getRole() == Role.ADMIN;

        if ("NOTICE".equals(board.getBoardType())) {
            if (!isAdmin)
                return ResponseEntity.status(403).body("Not authorized");
        } else {
            if (!isWriter && !isAdmin)
                return ResponseEntity.status(403).body("Not authorized");
        }
        boardRepository.delete(board);
        return ResponseEntity.ok("Deleted successfully");
    }
}
