package com.project.ny.controller;

import static org.easymock.EasyMock.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.project.ny.model.UserBean;
import com.project.ny.service.LoginService;

public class LoginControllerTest extends BaseSpringMvcTest<LoginController, UserBean> {

	@Autowired
	private LoginService loginService;

	@Test
	public void testLoginController() throws Exception {
		MockHttpServletRequestBuilder rb = MockMvcRequestBuilders.post("/login.do", new Object[]{});
		rb.param("userId", "test1");
		rb.param("password", "test1");

		UserBean user = mockControl.createMock(UserBean.class);
		expect(user.getUserId()).andReturn("test1").anyTimes();
		expect(user.getPassword()).andReturn("test1").anyTimes();

		expect(loginService.find("test1", "test1")).andReturn(user);

		mockControl.replay();

		ResultActions result = mockMvc.perform(rb);

		result.andExpect(status().isOk());

		form = (UserBean)result.andReturn().getModelAndView().getModel().get("loginUser");
		assertEquals(form.getUserId(), "test1");
	}
}
