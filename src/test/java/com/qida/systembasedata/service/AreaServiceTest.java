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

import com.qida.systembasedata.entity.Area;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:test-applicationContext-services.xml")
public class AreaServiceTest {
	@Autowired 
	AreaService areaService;
	
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
	public final void testFindAreaById() {
		Area area = areaService.findAreaById(1L);
		Assert.assertNotNull(area);
	}

	@Test
	public final void testFindAreasByType() {
		List<Area> areas = areaService.findAreasByType(0);
		Assert.assertNotNull(areas);
		Assert.assertTrue(areas.size()>0);
		
	}

	@Test
	public final void testFindAllAreas() {
		List<Area> areas = areaService.findAllAreas();
		Assert.assertNotNull(areas);
		Assert.assertTrue(areas.size()>0);
	}

	@Test
	public final void testFindAllSubAreas() {
		List<Area> areas = areaService.findAllSubAreas(1L, null);
		Assert.assertNotNull(areas);
		Assert.assertTrue(areas.size()>0);
	}

	@Test
	public final void testCRUDArea() {
		Area area = new Area();
		area.setAreaName("test");
		area.setAreaPid(1L);
		area.setType((byte)0);
		
		//增加
		Long areaId = areaService.createArea(area);
		Assert.assertNotNull(areaId);
		
		//根据id查询
		area = areaService.findAreaById(areaId);
		Assert.assertNotNull(area);
		
		//更新
		area.setAreaName("updateTest");
		areaService.updateArea(area);
		area = areaService.findAreaById(areaId);
		Assert.assertNotNull(area);
		Assert.assertEquals("updateTest", area.getAreaName());
		
		//删除
		areaService.deleteAreaById(area.getId());
		area = areaService.findAreaById(areaId);
		Assert.assertNull(area);
	}
}
