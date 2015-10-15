package org.wmaop.util.jexl;

import static org.junit.Assert.*;

import org.apache.commons.jexl2.Expression;
import org.apache.commons.jexl2.JexlContext;
import org.apache.commons.jexl2.MapContext;
import org.junit.Test;

public class FunctionsTest {

	@Test
	public void shouldPatternMatchWithinArray() {
		Expression expr = JexlExpressionFactory.getEngine().createExpression(
				"arrays:matches(values, '.*orld')");
		MapContext mc = new MapContext();
		mc.set("values", new String[] { "This", "is", "hello world" });
		assertTrue((Boolean) expr.evaluate(mc));
	}

	@Test
	public void shouldStringContainWithinArray() {
		Expression expr = JexlExpressionFactory.getEngine().createExpression(
				"arrays:contains(values, 'or')");
		MapContext mc = new MapContext();
		mc.set("values", new String[] { "This", "is", "hello world" });
		assertTrue((Boolean) expr.evaluate(mc));
	}

	@Test
	public void shouldHandleNullArrayInputForStringContain() {
		Expression expr = JexlExpressionFactory.getEngine().createExpression(
				"arrays:contains(values, 'or')");
		MapContext mc = new MapContext();
		mc.set("values", null);
		assertNull(expr.evaluate(mc));
	}

	@Test
	public void shouldHandleNullArrayInputForStringMatch() {
		Expression expr = JexlExpressionFactory.getEngine().createExpression(
				"arrays:matches(values, '.*orld')");
		MapContext mc = new MapContext();
		mc.set("values", null);
		assertNull(expr.evaluate(mc));
	}
}
