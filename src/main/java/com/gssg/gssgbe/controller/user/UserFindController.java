package com.gssg.gssgbe.controller.user;

import com.gssg.gssgbe.controller.user.response.FindAllUserResponse;
import com.gssg.gssgbe.controller.user.response.UserResponse;
import com.gssg.gssgbe.user.service.UserFindService;
import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class UserFindController {

  private final UserFindService userFindService;

  @Operation(summary = "전체 조회")
  @GetMapping("/api/v1/users")
  public FindAllUserResponse findAll() {
    List<UserResponse> userResponses = userFindService.findAll().stream()
        .map(UserResponse::new)
        .collect(Collectors.toList());

    return new FindAllUserResponse(userResponses);
  }
}