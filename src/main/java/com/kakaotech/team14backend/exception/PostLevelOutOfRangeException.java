package com.kakaotech.team14backend.exception;

import com.kakaotech.team14backend.common.MessageCode;
import lombok.Getter;

@Getter
public class PostLevelOutOfRangeException extends RuntimeException{
  public final MessageCode messageCode;

  public PostLevelOutOfRangeException(MessageCode messageCode) {
    this.messageCode = messageCode;
  }
}