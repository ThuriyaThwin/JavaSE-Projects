package com.jdc.online.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.jdc.jdbc.helper.ConnectionManager;
import com.jdc.online.entity.Category;

public class CategoryModelTest {

	private static CategoryModel model;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ConnectionManager.truncate("category");
		model = new CategoryModel();
	}

	@Test
	public void test1() {
		// insert test
		Category c = new Category();
		c.setName("Foods");
		
		model.insert(c);
		
		assertEquals(1, c.getId());
	}

	@Test
	public void test2() {
		// find by id test
		Category c = model.findById(1);
		
		assertNotNull(c);
		assertEquals("Foods", c.getName());
	}

	@Test
	public void test3() {
		// select all test
		List<Category> list = model.getAll();
		assertNotNull(list);
		assertEquals(1, list.size());
	}

	@Test
	public void test4() {
		// Update test
		Category c = model.findById(1);
		c.setName("Drinks");
		model.update(c);
		
		c = model.findById(1);
		assertNotEquals("Foods", c.getName());
		assertEquals("Drinks", c.getName());
	}

	@Test
	public void test5() {
		// Delete test
		model.delete(1);
		assertNull(model.findById(1));
	}
}
