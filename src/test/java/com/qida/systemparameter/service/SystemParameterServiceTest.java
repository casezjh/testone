package com.qida.systemparameter.service;

import java.util.List;

import junit.framework.Assert;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.qida.common.entity.AppName;
import com.qida.common.paging.PagingResult;
import com.qida.systemparameter.entity.SystemParameter;
import com.qida.systemparameter.entity.SystemParameterCondition;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:test-applicationContext-services.xml")
public class SystemParameterServiceTest {
	@Autowired
	SystemParameterService systemParameterService;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}


	@Test
	public final void testUpdateAndRestParameterSystemParameter() {
		Long id = 1L;
		String updateValue = "newValue";
		SystemParameter systemParameter = systemParameterService.findParameterById(id);
		systemParameter.setValue(updateValue);
		systemParameterService.updateParameter(systemParameter);
		systemParameter = systemParameterService.findParameterById(id);
		Assert.assertEquals(updateValue, systemParameter.getValue());
		
		systemParameterService.resetParameter(systemParameter.getId());
		systemParameter = systemParameterService.findParameterById(id);
		Assert.assertEquals(systemParameter.getDefaultValue(), systemParameter.getValue());
	}

	
	@Test
	public final void testFindParameterById() {
		Long id = 1L;
		SystemParameter systemParameter = systemParameterService.findParameterById(id);
		Assert.assertEquals(id, systemParameter.getId());
	}


	@Test
	public final void testFindParameterByModuleAndCode() {
		List<SystemParameter> systemParameters = systemParameterService.findParametersByModule(AppName.LTR, "common");
		Assert.assertNotNull(systemParameters);
		Assert.assertTrue(systemParameters.size() > 0);
	}


	@Test
	public final void testSearchParametersSystemParameterCondition() {
		SystemParameterCondition systemParameterCondition = new SystemParameterCondition();
		systemParameterCondition.setAppName(AppName.LTR);
		systemParameterCondition.setCode("h");
		systemParameterCondition.setPageNo(1);
		systemParameterCondition.setPageSize(5);
		
		PagingResult<SystemParameter> pagingResult = systemParameterService.searchParameters(systemParameterCondition);
		Assert.assertNotNull(pagingResult);
		Assert.assertTrue(pagingResult.getTotalCount()> 0);
		Assert.assertTrue(pagingResult.getResult().size() >0);
	}

}
