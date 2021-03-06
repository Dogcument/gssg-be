package com.gssg.gssgbe.domain.common.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gssg.gssgbe.domain.common.dto.ProfileDogDto;
import com.gssg.gssgbe.domain.common.repository.ProfileDogRepository;

import lombok.RequiredArgsConstructor;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class FindProfileDogService {

	private final ProfileDogRepository profileDogRepository;

	public List<ProfileDogDto> findAll() {
		return profileDogRepository.findAll().stream()
			.map(ProfileDogDto::of)
			.collect(Collectors.toList());
	}
}
