package com.jdc.hotel.model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Security implements Serializable {

	private static final long serialVersionUID = 1L;

	public Security() {
	}

	private LocalDateTime creation;

	private LocalDateTime modification;

	private String createUser;

	private String updateUser;

	private boolean delFlag;

	public LocalDateTime getCreation() {
		return creation;
	}

	public void setCreation(LocalDateTime creation) {
		this.creation = creation;
	}

	public LocalDateTime getModification() {
		return modification;
	}

	public void setModification(LocalDateTime modification) {
		this.modification = modification;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	public boolean isDelFlag() {
		return delFlag;
	}

	public void setDelFlag(boolean delFlag) {
		this.delFlag = delFlag;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((createUser == null) ? 0 : createUser.hashCode());
		result = prime * result + ((creation == null) ? 0 : creation.hashCode());
		result = prime * result + (delFlag ? 1231 : 1237);
		result = prime * result + ((modification == null) ? 0 : modification.hashCode());
		result = prime * result + ((updateUser == null) ? 0 : updateUser.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Security other = (Security) obj;
		if (createUser == null) {
			if (other.createUser != null)
				return false;
		} else if (!createUser.equals(other.createUser))
			return false;
		if (creation == null) {
			if (other.creation != null)
				return false;
		} else if (!creation.equals(other.creation))
			return false;
		if (delFlag != other.delFlag)
			return false;
		if (modification == null) {
			if (other.modification != null)
				return false;
		} else if (!modification.equals(other.modification))
			return false;
		if (updateUser == null) {
			if (other.updateUser != null)
				return false;
		} else if (!updateUser.equals(other.updateUser))
			return false;
		return true;
	}

}