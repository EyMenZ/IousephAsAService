package com.iouseph.model;

import static org.junit.Assert.*;

import java.util.Map;

import org.junit.Test;

public class UserTest {

	@Test
	public void testUser() {
		User user = new User();
		assertNotNull(user);
	}

	@Test
	public void testUserStringString() {
		User user = new User("username", "password");
		assertNotNull(user);
	}

	@Test
	public void testSetId() {
		User user = new User();
		user.setId("1");
		assertSame(user.getId(), "1");
	}

	@Test
	public void testGetUsername() {
		User user = new User("username", "password");
		assertSame(user.getUsername(), "username");
	}

	@Test
	public void testSetUsername() {
		User user = new User("username", "password");
		user.setUsername("new name");
		assertSame(user.getUsername(), "new name");
	}

	@Test
	public void testGetPassword() {
		User user = new User("username", "password");
		assertSame(user.getPassword(), "password");
	}

	@Test
	public void testSetPassword() {
		User user = new User("username", "password");
		user.setPassword("new password");
		assertSame(user.getPassword(), "new password");
	}

}
