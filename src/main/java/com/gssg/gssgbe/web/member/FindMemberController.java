package com.gssg.gssgbe.web.member;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.gssg.gssgbe.common.annotation.LoginMember;
import com.gssg.gssgbe.common.exception.ErrorCode;
import com.gssg.gssgbe.common.exception.custom.BusinessException;
import com.gssg.gssgbe.domain.member.dto.response.MemberDto;
import com.gssg.gssgbe.domain.member.entity.Member;
import com.gssg.gssgbe.domain.member.service.FindMemberService;
import com.gssg.gssgbe.web.member.response.FindAllMemberResponse;
import com.gssg.gssgbe.web.member.response.MemberResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "회원")
@RequiredArgsConstructor
@RestController
public class FindMemberController {

	private final FindMemberService findMemberService;

	@Operation(summary = "전체 조회")
	@GetMapping("/api/v1/members")
	public FindAllMemberResponse findAll() {
		final List<MemberDto> dtos = findMemberService.findAll();

		return new FindAllMemberResponse(dtos.stream()
			.map(MemberResponse::new)
			.collect(Collectors.toList()));
	}

	@Operation(summary = "회원 정보 상세 조회", security = @SecurityRequirement(name = "bearerAuth"))
	@GetMapping("/api/v1/members/{memberId}")
	public MemberResponse myInfo(
		@Parameter(hidden = true) @LoginMember final Member loginMember,
		@PathVariable final Long memberId) {
		final MemberDto memberDto = findMemberService.findById(memberId)
			.orElseThrow(() -> new BusinessException(ErrorCode.NOT_FOUND));

		return new MemberResponse(memberDto);
	}
}
