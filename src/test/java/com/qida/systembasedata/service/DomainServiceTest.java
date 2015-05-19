package com.qida.systembasedata.service;

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

import com.qida.systembasedata.entity.Domain;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:test-applicationContext-services.xml")
public class DomainServiceTest {
	@Autowired
	DomainService domainService;
	
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
	public final void testFindDomainById() {
		Long id = 6L;
		Domain domain = domainService.findDomainById(id);
		Assert.assertNotNull(domain);
		Assert.assertEquals(id, domain.getId());
	}

	@Test
	public final void testFindAllDomains() {
		List<Domain> domains = domainService.findAllDomains();
		Assert.assertNotNull(domains);
		Assert.assertTrue(domains.size()>0);
	}

	@Test
	public final void testFindAllSubDomains() {
		List<Domain> domains = domainService.findAllSubDomains(0L, null);
		Assert.assertNotNull(domains);
		Assert.assertTrue(domains.size()>0);	
	}

	@Test
	public final void testCURDDomain() {
		Domain domain = new Domain();
		domain.setDomainName("test");
		domain.setDomainPid(6L);
		
		//增加
		Long id = domainService.createDomain(domain);
		Assert.assertNotNull(id);
		
		//根据id查询
		domain = domainService.findDomainById(id);
		Assert.assertNotNull(domain);
		
		//更新
		domain.setDomainName("updateTest");
		domainService.updateDomain(domain);
		domain = domainService.findDomainById(id);
		Assert.assertNotNull(domain);
		Assert.assertEquals("updateTest", domain.getDomainName());
		
		//删除
		domainService.deleteDomainById(domain.getId());
		domain = domainService.findDomainById(id);
		Assert.assertNull(domain);
	}


}
