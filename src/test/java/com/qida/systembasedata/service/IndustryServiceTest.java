package com.qida.systembasedata.service;

import static org.junit.Assert.fail;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.qida.systembasedata.entity.Industry;
import com.qida.systembasedata.entity.Industry;
import com.qida.systembasedata.entity.Industry;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:test-applicationContext-services.xml")
public class IndustryServiceTest {
	@Autowired
	IndustryService industryService;
	
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
	public final void testFindIndustryById() {
		Long id = 1L;
		Industry Industry = industryService.findIndustryById(id);
		Assert.assertNotNull(Industry);
		Assert.assertEquals(id, Industry.getId());
	}

	@Test
	public final void testFindAllIndustries() {
		List<Industry> Industrys = industryService.findAllIndustries();
		Assert.assertNotNull(Industrys);
		Assert.assertTrue(Industrys.size()>0);
	}

	@Test
	public final void testFindAllSubIndustries() {
		List<Industry> Industrys = industryService.findAllSubIndustries(0L, null);
		Assert.assertNotNull(Industrys);
		Assert.assertTrue(Industrys.size()>0);	
	}

	@Test
	public final void testCURDIndustry() {
		Industry industry = new Industry();
		industry.setIndustryName("test");
		industry.setIndustryPid(1L);
		
		//增加
		Long id = industryService.createIndustry(industry);
		Assert.assertNotNull(id);
		
		//根据id查询
		industry = industryService.findIndustryById(id);
		Assert.assertNotNull(industry);
		
		//更新
		industry.setIndustryName("updateTest");
		industryService.updateIndustry(industry);
		industry = industryService.findIndustryById(id);
		Assert.assertNotNull(industry);
		Assert.assertEquals("updateTest", industry.getIndustryName());
		
		//删除
		industryService.deleteIndustryById(industry.getId());
		industry = industryService.findIndustryById(id);
		Assert.assertNull(industry);
	}

}
