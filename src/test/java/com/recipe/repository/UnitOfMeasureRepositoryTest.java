package com.recipe.repository;

import static org.junit.Assert.assertEquals;

import java.util.Optional;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.recipe.domain.UnitOfMeasure;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UnitOfMeasureRepositoryTest {
	
	@Autowired
	UnitOfMeasureRepository unitOfMeasureRepository;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testfindByDescriprion() {
		Optional<UnitOfMeasure> uomOpptional = unitOfMeasureRepository.findByDescription("Teaspoon");
		assertEquals("Teaspoon", uomOpptional.get().getDescription());
	}

	@Test
	public void testfindByDescriprionCup() {
		Optional<UnitOfMeasure> uomOpptional = unitOfMeasureRepository.findByDescription("Cup");
		assertEquals("Cup", uomOpptional.get().getDescription());
	}

}
