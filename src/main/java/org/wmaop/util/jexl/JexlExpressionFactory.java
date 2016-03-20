package org.wmaop.util.jexl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.jexl2.Expression;
import org.apache.commons.jexl2.JexlEngine;
import org.wmaop.util.jexl.functions.ArraysFunction;

public class JexlExpressionFactory {
	
	private static final JexlEngine jexlEngine = new JexlEngine();

	static {
		jexlEngine.setCache(512);
		jexlEngine.setLenient(true);
		jexlEngine.setSilent(false);

		Map<String, Object> funcs = new HashMap<>();
		funcs.put("arrays", new ArraysFunction());

		jexlEngine.setFunctions(funcs);
	}

	private JexlExpressionFactory() {}

	public static Expression createExpression(String expr) {
		return jexlEngine.createExpression(ExpressionProcessor.escapedToEncoded(expr));
	}


	public static JexlEngine getEngine() {
		return jexlEngine;
	}
}
