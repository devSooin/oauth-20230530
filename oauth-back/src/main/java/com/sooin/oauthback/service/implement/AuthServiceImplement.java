package com.sooin.oauthback.service.implement;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.sooin.oauthback.dto.request.auth.SignUpRequestDto;
import com.sooin.oauthback.dto.response.ResponseDto;
import com.sooin.oauthback.dto.response.auth.SignUpResponseDto;
import com.sooin.oauthback.entity.UserEntity;
import com.sooin.oauthback.repository.UserRepository;
import com.sooin.oauthback.service.AuthService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImplement implements AuthService {

  private final UserRepository userRepository;
  
  @Override
  public ResponseEntity<? super SignUpResponseDto> signUp(SignUpRequestDto dto) {

    String id = dto.getId();

    try {

      boolean hasId = userRepository.existsById(id);
      if (hasId) return SignUpResponseDto.existedId();

      UserEntity userEntity = new UserEntity(dto);
      userRepository.save(userEntity);

    } catch (Exception exception) {
      exception.printStackTrace();
      return ResponseDto.databaseError();
    }

    return SignUpResponseDto.success();

  }
  
}
