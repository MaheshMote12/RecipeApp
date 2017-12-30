package com.me.converters;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import com.me.command.UnitOfMeasureCommand;
import com.me.model.UnitOfMeasure;

public class UnitOfMeasureCommandToUnitOfMeasureTest {

	
	UnitOfMeasureCommandToUnitOfMeasure converter;
	
	@Before
	public void setUp() throws Exception {
		converter = new UnitOfMeasureCommandToUnitOfMeasure();
	}

	@Test
	public void testNullObject() {
		assertNull(converter.convert(null));
	}

	@Test
	public void testEmptyObject() {
		assertNotNull(converter.convert(new UnitOfMeasureCommand()));
	}

	@Test
	public void testConvert() {
	
		UnitOfMeasureCommand uom = new UnitOfMeasureCommand();
		uom.setId(12l);
		uom.setUnit("tea spoon");
		
		UnitOfMeasure unitom = converter.convert(uom);
		
		assertEquals("tea spoon", unitom.getUnit());
		assertThat(12l, is(unitom.getId()));
	}
}
