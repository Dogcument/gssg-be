package com.gssg.gssgbe.domain.member.service;

import static com.gssg.gssgbe.common.exception.ErrorCode.*;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gssg.gssgbe.common.exception.custom.BusinessException;
import com.gssg.gssgbe.domain.member.dto.request.CreateMemberDto;
import com.gssg.gssgbe.domain.member.entity.Member;
import com.gssg.gssgbe.domain.member.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Transactional
@RequiredArgsConstructor
@Service
public class CreateMemberService {

	private final MemberRepository memberRepository;

	public long create(final CreateMemberDto createDto) {
		validation(createDto);

		final Member member = new Member(createDto);

		return memberRepository.save(member).getId();
	}

	private void validation(CreateMemberDto request) {
		if (memberRepository.existsByEmail(request.getEmail())) {
			throw new BusinessException(EXISTS_EMAIL);
		}
	}
}
