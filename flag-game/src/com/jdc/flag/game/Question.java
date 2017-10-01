package com.jdc.flag.game;

import java.io.Serializable;
import java.util.List;

@SuppressWarnings("serial")
public class Question implements Serializable {

	private Flag flag;
	private List<String> names;

	public Flag getFlag() {
		return flag;
	}

	public void setFlag(Flag flag) {
		this.flag = flag;
	}

	public List<String> getNames() {
		return names;
	}

	public void setNames(List<String> names) {
		this.names = names;
	}

}
