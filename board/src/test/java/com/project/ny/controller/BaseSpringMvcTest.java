package com.project.ny.controller;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import org.easymock.EasyMock;
import org.easymock.IMocksControl;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContextTest.xml")
@WebAppConfiguration
public class BaseSpringMvcTest<C, F> {

	protected MockMvc mockMvc;

	protected IMocksControl mockControl;

	@Autowired
    protected WebApplicationContext wac;

	@Autowired
    protected MockHttpServletRequest request;

	@Autowired
    protected MockHttpSession session;

	@Autowired
	protected C controller;

	protected F form;

	@Before
	public void setUp() throws Exception {
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
		mockControl = EasyMock.createControl();
		changeTestTargetMock();
		form = createForm();
	}

	/**
     * フォームを作成する。
     *
     * @return 作成したフォーム
     */
    private F createForm() {
        try {
            Class<?> entityClass = (Class<?>) getActualType(1);
            // リフレクションでインスタンスを生成
            return (F) entityClass.newInstance();
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 実際のクラスの型を取得する。
     */
    private Class<?> getActualType(int index) {
        // 実行時の型が取れる。ここではBaseSpringMvcTest
        Class<?> clazz = this.getClass();
        // ここではBaseSpringMvcTest<C, F>がとれる
        Type type = clazz.getGenericSuperclass();
        ParameterizedType pt = (ParameterizedType) type;
        // BaseSpringMvcTestの型変数に対するバインドされた型がとれる
        Type[] actualTypeArguments = pt.getActualTypeArguments();
        return (Class<?>) actualTypeArguments[index];
    }

    /**
     * フォームを作成する。
     *
     * @return 作成したフォーム
     */
    private void changeTestTargetMock() {
    	// テストクラスの変数を取得する。
        Field[] testFields = this.getClass().getDeclaredFields();

        Class clazz = getActualType(0);

        while (clazz != null) {
        	// テスト対象のクラスの変数を取得する。
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                // Autowiredアノテーションのついたフィールドをモックにする。
                if (field.isAnnotationPresent(Autowired.class)) {

                    // インターフェースかどうか判定
                    if (field.getType().isInterface()) {

                        // このフィールド変数はインターフェースである。モックにする
                        Object mockObject = mockControl.createMock(field.getType());
                        ReflectionTestUtils.setField(controller, field.getName(), mockObject);

                        // テストクラスの変数に同様の値があったら，置き換える。
                        for (Field testField : testFields) {
                            if (testField.isAnnotationPresent(Autowired.class) && testField.getType().equals(field.getType())) {
                                ReflectionTestUtils.setField(this, testField.getName(), mockObject);
                            }
                        }
                    }
                }
            }

            // 親クラスへ
            clazz = clazz.getSuperclass();
		}
    }


//	@Test
//	public void testLoginController() throws Exception {
//		MockHttpServletRequestBuilder rb = MockMvcRequestBuilders.post("/login.do", new Object[]{});
//		rb.param("userId", "test1");
//		rb.param("password", "test1");
//
//		UserBean user = new UserBean();
//		user.setUserId("test");
//
//		when(loginService.find("test1", "test1")).thenReturn(user);
//
//		ResultActions result = mockMvc.perform(rb);
//		MvcResult mvcResult = mockMvc.perform(rb).andReturn();
//
//		result.andExpect(status().isOk());
//
//		user = (UserBean)mvcResult.getModelAndView().getModel().get("loginUser");
//		assertEquals(user.getUserId(), "test");
//	}
//	@Mock
//	private LoginService loginService;
//	@InjectMocks
//	private LoginController loginController;
//
//	@Before
//	public void setUp() throws Exception {
//		MockitoAnnotations.initMocks(this);
//		mockMvc = MockMvcBuilders
//                .standaloneSetup(loginController)
//                .build();
//	}
//
//	@Test
//	public void testLoginController() throws Exception {
//		MockHttpServletRequestBuilder rb = MockMvcRequestBuilders.post("/login.do", new Object[]{});
//		rb.param("userId", "test1");
//		rb.param("password", "test1");
//
//		UserBean user = new UserBean();
//		user.setUserId("test");
//
//		when(loginService.find("test1", "test1")).thenReturn(user);
//
//		ResultActions result = mockMvc.perform(rb);
//		MvcResult mvcResult = mockMvc.perform(rb).andReturn();
//
//		result.andExpect(status().isOk());
//
//		user = (UserBean)mvcResult.getModelAndView().getModel().get("loginUser");
//		assertEquals(user.getUserId(), "test");
//	}

//	@Autowired
//  protected WebApplicationContext wac;
//
//	@Mock
//  LoginService loginService;
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
}
