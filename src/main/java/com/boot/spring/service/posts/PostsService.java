package com.boot.spring.service.posts;

import com.boot.spring.domain.posts.Posts;
import com.boot.spring.domain.posts.PostsRepository;
import com.boot.spring.web.dto.PostsResponseDto;
import com.boot.spring.web.dto.PostsSaveRequestDto;
import com.boot.spring.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;


    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        // 저장 요청 객체로 save한 후 아이디 반환.
        return postsRepository.save(requestDto.toEntity()).getId();

    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        // id로 select후 없으면 예외.
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id = " + id));

        // id로 찾은 Posts에 update 실행.
        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    public PostsResponseDto findById(Long id)   {
        Posts entity = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id = " + id));

        return new PostsResponseDto(entity);
    }
}
