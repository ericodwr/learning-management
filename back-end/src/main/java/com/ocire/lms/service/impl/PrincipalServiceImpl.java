package com.ocire.lms.service.impl;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.ocire.lms.service.PrincipalService;

@Service
public class PrincipalServiceImpl implements PrincipalService {

	@Override
	public Long getPrincipal() {
		final Integer id = (int) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		final Long idFix = (long) id;
		return idFix;
	}
}
