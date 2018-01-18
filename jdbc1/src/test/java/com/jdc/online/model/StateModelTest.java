package com.jdc.online.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.jdc.jdbc.helper.ConnectionManager;
import com.jdc.online.entity.State;
import com.jdc.online.repo.StateRepo;

public class StateModelTest {

	private static StateRepo model;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ConnectionManager.truncate("state");
		model = new StateRepo();
	}

	@Test
	public void test1() {
		// get all test
		State s = new State();
		s.setName("Dala");

		model.insert(s);

		assertEquals(1, s.getId());
	}

	@Test
	public void test2() {
		// get all test
		List<State> list = model.getAll();
		assertNotNull(list);
		assertEquals(1, list.size());
	}

	@Test
	public void test3() {

		// find by id
		State s1 = model.findById(1);
		assertNotNull(s1);
		assertEquals("Dala", s1.getName());

		State s3 = model.findById(2);
		assertNull(s3);

	}

	@Test
	public void test5() {
		// find by id
		State s1 = model.findById(1);
		assertNotNull(s1);
		assertEquals("Dala", s1.getName());

		s1.setName("Ayarwadi");
		model.update(s1);

		s1 = model.findById(1);
		assertNotEquals("Dala", s1.getName());
		assertEquals("Ayarwadi", s1.getName());

	}

	@Test
	public void test6() {

		model.delete(1);
		State s1 = model.findById(1);
		assertNull(s1);

	}

}
