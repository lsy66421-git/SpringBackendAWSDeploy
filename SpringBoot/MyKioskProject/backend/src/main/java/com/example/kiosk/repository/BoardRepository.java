package com.example.kiosk.repository;

import com.example.kiosk.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {
        List<Board> findAllByWriter(com.example.kiosk.entity.Member writer);

        // Notice: strict match
        List<Board> findAllByBoardType(String boardType);

        // Inquiry (Admin): Match type OR Null (legacy)
        @org.springframework.data.jpa.repository.Query("SELECT b FROM Board b WHERE b.boardType = :boardType OR b.boardType IS NULL")
        List<Board> findAllByBoardTypeOrNull(
                        @org.springframework.data.repository.query.Param("boardType") String boardType);

        // Inquiry (Writer): Match (Writer AND Type) OR (Writer AND Null)
        @org.springframework.data.jpa.repository.Query("SELECT b FROM Board b WHERE b.writer.id = :writerId AND (b.boardType = :boardType OR b.boardType IS NULL)")
        List<Board> findAllByWriterIdAndBoardTypeOrNull(
                        @org.springframework.data.repository.query.Param("writerId") Long writerId,
                        @org.springframework.data.repository.query.Param("boardType") String boardType);
}
