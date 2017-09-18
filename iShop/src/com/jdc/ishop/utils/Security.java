package com.jdc.ishop.utils;

import com.jdc.ishop.model.entity.Member;

public class Security {
	
	private static Member loginUser;

	public static void setLoginUser(Member member) {
		loginUser = member;
	}
	
	public static Member getLoginUser() {
		return loginUser;
	}

}
