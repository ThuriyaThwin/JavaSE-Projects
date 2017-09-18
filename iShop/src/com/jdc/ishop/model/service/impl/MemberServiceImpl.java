package com.jdc.ishop.model.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.jdc.ishop.IShopException;
import com.jdc.ishop.model.entity.Member;
import com.jdc.ishop.model.entity.Member.Role;
import com.jdc.ishop.model.service.MemberService;
import com.jdc.ishop.utils.DatabaseManager;

public class MemberServiceImpl implements MemberService {

	@Override
	public Member login(String login, String pass) {
		
		Member member = find(login);
		
		if(null == member) {
			throw new IShopException("Please check Login Id.");
		}
		
		if(!member.getPassword().equals(pass)) {
			throw new IShopException("Please check Your Password.");
		}
		
		if(member.isDelete()) {
			throw new IShopException("You can't access this application.");
		}
		
		return member;
	}

	private Member find(String login) {
		String sql = "select * from member where login = ?";

		try(Connection conn = DatabaseManager.getConnection(); 
				PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, login);
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				return getObject(rs);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}



	@Override
	public List<Member> find(Role role, String name) {
		StringBuilder sb = new StringBuilder("select * from member where 1 = 1 ");
		
		List<String> params = new ArrayList<>();
		List<Member> list = new ArrayList<>();
		
		if(null != role) {
			sb.append("and role = ? ");
			params.add(role.toString());
		}
		
		if(!name.isEmpty()) {
			sb.append("and name like ?");
			params.add(name.concat("%"));
		}

		try(Connection conn = DatabaseManager.getConnection(); 
				PreparedStatement stmt = conn.prepareStatement(sb.toString())) {
			
			for (int i = 0; i < params.size(); i++) {
				stmt.setString(i + 1, params.get(i));
			}
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				list.add(getObject(rs));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}

	@Override
	public void save(Member t) {
		Member old = find(t.getLogin());
		
		if(null == old) {
			create(t);
		} else {
			update(t);
		}
	}

	private void update(Member t) {
		String sql = "update member set name = ?, password = ?, phone = ?, email = ?, role = ?, del_flag = ? where login = ?";

		try(Connection conn = DatabaseManager.getConnection(); 
				PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, t.getName());
			stmt.setString(2, t.getPassword());
			stmt.setString(3, t.getPhone());
			stmt.setString(4, t.getEmail());
			stmt.setString(5, t.getRole().toString());
			stmt.setBoolean(6, t.isDelete());
			stmt.setString(7, t.getLogin());
			
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void create(Member t) {
		String sql = "insert into member values (?, ?, ?, ?, ?, ?, ?)";

		try(Connection conn = DatabaseManager.getConnection(); 
				PreparedStatement stmt = conn.prepareStatement(sql)) {
			
			stmt.setString(1, t.getLogin());
			stmt.setString(2, t.getName());
			stmt.setString(3, t.getPassword());
			stmt.setString(4, t.getPhone());
			stmt.setString(5, t.getEmail());
			stmt.setString(6, t.getRole().toString());
			stmt.setBoolean(7, t.isDelete());
			
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private Member getObject(ResultSet rs) throws SQLException {
		Member member = new Member();
		
		member.setLogin(rs.getString("login"));
		member.setName(rs.getString("name"));
		member.setPassword(rs.getString("password"));
		member.setPhone(rs.getString("phone"));
		member.setEmail(rs.getString("email"));
		member.setRole(Role.valueOf(rs.getString("role")));
		member.setDelete(rs.getBoolean("del_flag"));
		
		return member;
	}
}
