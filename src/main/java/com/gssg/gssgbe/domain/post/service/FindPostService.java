package com.gssg.gssgbe.domain.post.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gssg.gssgbe.common.clazz.NoOffsetPageRequest;
import com.gssg.gssgbe.domain.member.entity.Member;
import com.gssg.gssgbe.domain.member.repository.MemberRepository;
import com.gssg.gssgbe.domain.post.dto.reponse.PostDto;
import com.gssg.gssgbe.domain.post.repository.PostRepository;

import lombok.RequiredArgsConstructor;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class FindPostService {

	private final PostRepository postRepository;
	private final MemberRepository memberRepository;

	public List<PostDto> findAll(final Member loginMember, final NoOffsetPageRequest pageRequest) {
		return postRepository.findAll(pageRequest).stream()
			.map(post -> PostDto.of(post, post.isLike(loginMember)))
			.collect(Collectors.toList());
	}

	public List<PostDto> findByMember(final Member loginMember, final long memberId,
		final NoOffsetPageRequest pageRequest) {
		final Member member = memberRepository.findById(memberId)
			.orElseThrow(EntityNotFoundException::new);
		return postRepository.findAllByMember(member, pageRequest).stream()
			.map(post -> PostDto.of(post, post.isLike(loginMember)))
			.collect(Collectors.toList());
	}
}
