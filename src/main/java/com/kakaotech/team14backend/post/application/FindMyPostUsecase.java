package com.kakaotech.team14backend.post.application;

import com.kakaotech.team14backend.post.application.command.FindLikeStatusCommand;
import com.kakaotech.team14backend.post.domain.Post;
import com.kakaotech.team14backend.post.dto.GetMyPostResponseDTO;
import com.kakaotech.team14backend.post.dto.GetPostDTO;
import com.kakaotech.team14backend.post.infrastructure.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FindMyPostUsecase {

  private final PostRepository postRepository;
  private final FindLikeStatusCommand findLikeStatusCommand;

  public GetMyPostResponseDTO execute(GetPostDTO getPostDTO) {
    Long memberId = getPostDTO.memberId();
    Long postId = getPostDTO.postId();

    Post post = postRepository.findByPostIdAndMemberId(memberId, postId);
    boolean isLiked = findLikeStatusCommand.execute(memberId, postId);
    return PostMapper.from(post, isLiked, memberId);
  }
}
