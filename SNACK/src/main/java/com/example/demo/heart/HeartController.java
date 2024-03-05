package com.example.demo.heart;

import com.example.demo.socialboard.repository.SocialBoardRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/heart/")
public class HeartController {
    private final HeartService heartService;
    private final SocialBoardRepository socialBoardRepository;

    @PostMapping
    public ResponseEntity<?> insert(@RequestBody @Valid HeartRequestDTO heartRequestDTO, Authentication authentication) throws Exception {
        heartService.insert(heartRequestDTO, authentication);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@RequestBody @Valid HeartRequestDTO heartRequestDTO, Authentication authentication) {
        heartService.delete(heartRequestDTO, authentication);
        return ResponseEntity.ok().build();

    }



}
