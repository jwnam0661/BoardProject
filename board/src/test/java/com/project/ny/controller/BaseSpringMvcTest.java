package com.project.ny.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.project.ny.model.UserBean;
import com.project.ny.service.LoginService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContextTest.xml")
@WebAppConfiguration
public class BaseSpringMvcTest {

	private MockMvc mockMvc;

//	@Autowired
//    protected WebApplicationContext wac;
//
//	@Mock
//    LoginService loginService;
//
//	@Before
//	public void setUp() throws Exception {
//		mockMvc = MockMvcBuilders.webAppContextSetup(wac).alwaysDo(print()).build();
//	}

//	@Test
//	public void testLoginController() throws Exception {
//		MockHttpServletRequestBuilder rb = MockMvcRequestBuilders.post("/login.do", new Object[]{});
//		rb.param("userId", "test1");
//		rb.param("password", "test1");
//
//		when(loginService.find("test1", "test1")).thenReturn(new UserBean());
//
//		ResultActions result = mockMvc.perform(rb);
//
//		result.andExpect(status().isOk());
//	}

	@Mock
	private LoginService loginService;
	@InjectMocks
	private LoginController loginController;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders
                .standaloneSetup(loginController)
                .build();
	}

	@Test
	public void testLoginController() throws Exception {
		MockHttpServletRequestBuilder rb = MockMvcRequestBuilders.post("/login.do", new Object[]{});
		rb.param("userId", "test1");
		rb.param("password", "test1");

		UserBean user = new UserBean();
		user.setUserId("test");

		when(loginService.find("test1", "test1")).thenReturn(user);

		ResultActions result = mockMvc.perform(rb);
		MvcResult mvcResult = mockMvc.perform(rb).andReturn();

		result.andExpect(status().isOk());

		user = (UserBean)mvcResult.getModelAndView().getModel().get("loginUser");
		assertEquals(user.getUserId(), "test");
	}
}
