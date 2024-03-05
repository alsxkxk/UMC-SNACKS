package com.example.demo.socialboard;

import com.example.demo.socialboard.dto.SocialBoardDTO;
import com.example.demo.socialboard.dto.SocialBoardResponseDTO;
import com.example.demo.socialboard.entity.SocialBoard;
import com.example.demo.socialboard.repository.SocialBoardRepository;
import jakarta.validation.Valid;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/social-board/")
@AllArgsConstructor
@Slf4j
public class SocialBoardController {
    private final SocialBoardService socialBoardService;
    private final SocialBoardRepository socialBoardRepository;

    @PostMapping
    public ResponseEntity<SocialBoardResponseDTO> create(@Valid @RequestBody SocialBoardDTO boardDTO) {

        SocialBoard board = socialBoardService.saveBoard(boardDTO);
        SocialBoardResponseDTO socialBoardResponseDTO = board.toResponseEntity();

        return ResponseEntity.ok().body(socialBoardResponseDTO);
    }

    @GetMapping("{boardId}")
    public ResponseEntity<SocialBoardResponseDTO> read(@PathVariable Long boardId) {
        SocialBoard board = socialBoardService.getBoard(boardId).orElseThrow(() -> new NoSuchElementException("해당 id의 게시판이 존재하지 않습니다."));

        SocialBoardResponseDTO socialBoardResponseDTO = board.toResponseEntity();
        return ResponseEntity.ok().body(socialBoardResponseDTO);
    }

    // 업데이트 로직
    // 게시글의 "내용"만 변경가능하다.
    // 투표 게시글의 경우 투표와 내용, 일반 게시글의 경우 사진과 내용
    @PutMapping("{boardId}")
    public ResponseEntity update(@PathVariable Long boardId, @Valid @RequestBody SocialBoardDTO socialBoardDTO) {
        socialBoardService.updateBoard(boardId, socialBoardDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("{boardId}")
    public void delete(@PathVariable Long boardId) {
        socialBoardService.deleteBoard(boardId);
    }


    @GetMapping("like")
    public ResponseEntity<?> findUserFavoriteBoard(Authentication authentication){
        Long userId = Long.parseLong(authentication.getName());
        List<SocialBoardSimpleDTO> collect = socialBoardRepository.findUserFavoriteBoardsByMemberId(userId).stream()
                .map(s -> new SocialBoardSimpleDTO(s.getId()))
                .collect(Collectors.toList());

        return ResponseEntity.ok(new Result(userId, collect));
    }


    @Data
    @AllArgsConstructor
    static class Result<T>{
        private Long userId;
        private T data;
    }

    @Data
    @AllArgsConstructor
    static class SocialBoardSimpleDTO{
        private Long id;
    }

}
