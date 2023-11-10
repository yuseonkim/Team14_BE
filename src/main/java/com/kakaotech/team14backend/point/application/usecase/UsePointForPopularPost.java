package com.kakaotech.team14backend.point.application.usecase;

import com.kakaotech.team14backend.member.domain.Member;
import com.kakaotech.team14backend.point.application.UsePoint;
import com.kakaotech.team14backend.point.application.ValidatePoint;
import com.kakaotech.team14backend.point.domain.UsePointDecider;
import com.kakaotech.team14backend.point.dto.UsePointByPopularPostRequestDTO;
import com.kakaotech.team14backend.post.application.SetPostInstaCount;
import com.kakaotech.team14backend.post.domain.Post;
import com.kakaotech.team14backend.post.exception.PostNotFoundException;
import com.kakaotech.team14backend.post.infrastructure.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UsePointForPopularPost {

  private final ValidatePoint validatePoint;
  private final UsePoint usePoint;
  private final PostRepository postRepository;
  private final SetPostInstaCount setPostInstaUsecase;

  public String execute(UsePointByPopularPostRequestDTO usePointByPopularPostRequestDTO, Long senderId) {
    validatePoint.execute(usePointByPopularPostRequestDTO);
    Post post = postRepository.findById(usePointByPopularPostRequestDTO.postId()).orElseThrow(() -> new PostNotFoundException());
    usePoint.execute(senderId, post.getMember().getMemberId(), getPoint(usePointByPopularPostRequestDTO));
    setPostInstaUsecase.execute(post.getPostId(), post.getMember().getMemberId());
    return post.getMember().getInstaId();
  }

  private static Long getPoint(UsePointByPopularPostRequestDTO usePointByPopularPostRequestDTO) {
    return UsePointDecider.decidePoint(usePointByPopularPostRequestDTO.postLevel());
  }

}
