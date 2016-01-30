package org.wmaop.util.jexl;

import static org.junit.Assert.*;

import org.apache.commons.jexl2.Expression;
import org.junit.Test;

import com.wm.data.IData;
import com.wm.data.IDataCursor;
import com.wm.data.IDataFactory;
import com.wm.data.IDataUtil;

public class JexlExpressionFactoryTest {

	@Test
	public void testDotNotation() {
		Expression expr = JexlExpressionFactory.createExpression("alpha.beta == \"hello\"");
		IData alpha = IDataFactory.create();
		put(alpha, "beta", "goodbye");
		IData idata = IDataFactory.create();
		put(idata, "alpha", alpha);
		Boolean result = (Boolean) expr.evaluate(new IDataJexlContext(idata));
		assertFalse(result);
		put(alpha, "beta", "hello");
		result = (Boolean) expr.evaluate(new IDataJexlContext(idata));
		assertTrue(result);
	}

	@Test
	public void shouldMatchPipeline() throws Exception {
		Expression expr = JexlExpressionFactory.createExpression("foo == 2");
		IData idata = IDataFactory.create();
		Boolean result = (Boolean) expr.evaluate(new IDataJexlContext(idata));
		assertFalse(result);
		put(idata, "foo", 2);
		result = (Boolean) expr.evaluate(new IDataJexlContext(idata));
		assertTrue(result);
		put(idata, "foo", 1);
		result = (Boolean) expr.evaluate(new IDataJexlContext(idata));
		assertFalse(result);
	}

	@Test
	public void test() {
		testExpression("foo_2 == 2", "foo_2", "2");
		testExpression("foo\\:3 == 3", "foo:3", "3");
		testExpression("foo\\ 4 == 4", "foo 4", "4");
		testExpression("foo\\-5 == 5", "foo-5", "5");
		testExpression("foo\\-\\- == 5", "foo--", "5");
		testExpression("f\\-oo == 5", "f-oo", "5");
		testExpression("\\-oo == 5", "-oo", "5");
		testExpression("a\\-b\\-c == 5", "a-b-c", "5");
		try {
			testExpression("foo\\b == 5", "foo--", "5");
			fail();
		} catch (RuntimeException e) {
			// NOOP
		}
		
	}

	void testExpression(String expression, String varName, String varValue) {
		Expression expr = JexlExpressionFactory.createExpression(expression);
		IData idata = IDataFactory.create();
		Boolean result = (Boolean) expr.evaluate(new IDataJexlContext(idata));
		assertFalse(result);
		put(idata, varName, varValue);
		result = (Boolean) expr.evaluate(new IDataJexlContext(idata));
		assertTrue(result);
	}
	
	void put(IData idata, String k, Object v) {
		IDataCursor cursor = idata.getCursor();
		IDataUtil.put(cursor, k, v);
		cursor.destroy();
	}
}
