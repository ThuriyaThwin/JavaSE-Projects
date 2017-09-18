package com.jdc.ishop.model.service;

import java.util.List;

import com.jdc.ishop.model.entity.Member;
import com.jdc.ishop.model.entity.Member.Role;
import com.jdc.ishop.model.service.impl.MemberServiceImpl;

public interface MemberService {

	Member login(String login, String pass);

	List<Member> find(Role role, String name);

	void save(Member t);

	static MemberService getInstance() {
		return new MemberServiceImpl();
	}

}