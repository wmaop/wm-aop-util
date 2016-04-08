package org.wmaop.util.jexl.functions;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.jexl2.Expression;
import org.apache.commons.jexl2.MapContext;
import org.junit.Test;
import org.wmaop.util.jexl.JexlExpressionFactory;

public class ArraysFunctionTest {

	@Test
	public void shouldPatternMatchWithinArray() {
		Expression expr = JexlExpressionFactory.createExpression(
				"arrays:matches(values, '.*orld')");
		MapContext mc = new MapContext();
		mc.set("values", new String[] { "This", "is", "hello world" });
		assertTrue((Boolean) expr.evaluate(mc));
	}

	@Test
	public void shouldStringContainWithinArray() {
		Expression expr = JexlExpressionFactory.createExpression(
				"arrays:contains(values, 'or')");
		MapContext mc = new MapContext();
		mc.set("values", new String[] { "This", "is", "hello world" });
		assertTrue((Boolean) expr.evaluate(mc));
	}

	@Test
	public void shouldHandleNullArrayInputForStringContain() {
		Expression expr = JexlExpressionFactory.createExpression(
				"arrays:contains(values, 'or')");
		MapContext mc = new MapContext();
		mc.set("values", null);
		assertNull(expr.evaluate(mc));
	}

	@Test
	public void shouldHandleNullArrayInputForStringMatch() {
		Expression expr = JexlExpressionFactory.createExpression(
				"arrays:matches(values, '.*orld')");
		MapContext mc = new MapContext();
		mc.set("values", null);
		assertNull(expr.evaluate(mc));
	}
	@Test
	public void shouldHandleNullMatch() {
		String [] array = {"foo", null, "bar"};
		assertTrue(new ArraysFunction().contains(array, null));
		
		Expression expr = JexlExpressionFactory.createExpression(
				"arrays:contains(values, null)");
		MapContext mc = new MapContext();
		mc.set("values", array);
		assertTrue((Boolean) expr.evaluate(mc));
	}

}
