package com.example.kiosk.dto;

import com.example.kiosk.entity.Board;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BoardResponse {
    private Long id;
    private String title;
    private String content;
    private WriterResponse writer; // Nested object
    private String createdAt;
    private String fileName;
    private String filePath;
    private String boardType;

    @Getter
    @Builder
    public static class WriterResponse {
        private Long id;
        private String name;
    }

    public static BoardResponse from(Board board) {
        return BoardResponse.builder()
                .id(board.getId())
                .title(board.getTitle())
                .content(board.getContent())
                .writer(board.getWriter() != null
                        ? WriterResponse.builder()
                                .id(board.getWriter().getId())
                                .name(board.getWriter().getName())
                                .build()
                        : null)
                .createdAt(board.getCreatedAt() != null ? board.getCreatedAt().toString() : "")
                .fileName(board.getFileName())
                .filePath(board.getFilePath())
                .boardType(board.getBoardType())
                .build();
    }
}
