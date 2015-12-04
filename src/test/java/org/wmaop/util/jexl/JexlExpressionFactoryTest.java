package org.wmaop.util.jexl;

import static org.junit.Assert.*;

import java.io.IOException;

import org.apache.commons.jexl2.Expression;
import org.junit.Test;

import com.wm.data.IData;
import com.wm.data.IDataCursor;
import com.wm.data.IDataFactory;
import com.wm.data.IDataUtil;
import com.wm.util.coder.IDataXMLCoder;
import com.wm.util.coder.InvalidDatatypeException;

public class JexlExpressionFactoryTest {

	@Test
	public void testDotNotation() {
		Expression expr = JexlExpressionFactory.getEngine().createExpression("alpha.beta == \"hello\"");
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
		Expression expr = JexlExpressionFactory.getEngine().createExpression("foo == 2");
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
	
	void put(IData idata, String k, Object v) {
		IDataCursor cursor = idata.getCursor();
		IDataUtil.put(cursor, k, v);
		cursor.destroy();
	}
	
	
	@Test
	public void shouldCorrectlyAccessVariables() throws Exception {
		IData idata = new IDataXMLCoder().decode(this.getClass().getClassLoader().getResourceAsStream("complex.xml"));
		assertTrue(executeExpression("producer.prd_Id == \"2\"", idata));
		assertTrue(executeExpression("producer.prd_GeneralPartyInfo.pty_Addr[0].adr_City == \"Napa\"", idata));
		assertTrue(executeExpression("producer[0].bar != \"Napa\"", idata));

		// Doesnt exist
		assertFalse(executeExpression("producer.prd_GeneralPartyInfo.pty_Addr[1].adr_City == \"Napa\"", idata));
		assertFalse(executeExpression("foo.bar == \"Napa\"", idata));
		
		// Bad array details
		// Raise defect JEXL-183 as this blows rather than correctly returning true
		//assertTrue(executeExpression("bar[0] != \"Napa\"", idata));
	}
	
	private Boolean executeExpression(String expression, IData idata) {
		Expression expr = JexlExpressionFactory.getEngine().createExpression(expression);
		return (Boolean) expr.evaluate(new IDataJexlContext(idata));
	}
}
