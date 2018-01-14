package com.jdc.online.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.jdc.online.entity.Township;
import com.jdc.online.repo.TownshipRepo;

public class TownshipModelTest {

	private static TownshipRepo model;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		model = new TownshipRepo();
	}

	@Test
	public void test1() {
		// get All Test
		List<Township> list = model.getAll();
		assertNotNull(list);
		assertEquals(38, list.size());
	}

	@Test
	public void test2() {
		// Find By Id Test
		Township tsh1 = model.findById(1);
		assertNotNull(tsh1);
		assertEquals("Hinthada Township", tsh1.getName());

		Township tsh2 = model.findById(39);
		assertNull(tsh2);
	}

	@Test
	public void test3() {
		// Find By Name Like Test
		List<Township> list1 = model.findByNameLike("Ka");
		assertNotNull(list1);
		assertEquals(2, list1.size());

		List<Township> list2 = model.findByNameLike("Ba");
		assertNotNull(list2);
		assertEquals(0, list2.size());
	}

	@Test
	public void test4() {
		// Find By State Id Test
		List<Township> list1 = model.findByStateId(1);
		assertNotNull(list1);
		assertEquals(38, list1.size());

		List<Township> list2 = model.findByStateId(2);
		assertNotNull(list2);
		assertEquals(0, list2.size());
	}
}
