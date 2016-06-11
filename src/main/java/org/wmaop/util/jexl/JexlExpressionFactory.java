package org.wmaop.util.jexl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.jexl3.JexlBuilder;
import org.apache.commons.jexl3.JexlEngine;
import org.apache.commons.jexl3.JexlExpression;
import org.wmaop.util.jexl.functions.ArraysFunction;

public class JexlExpressionFactory {
	
	private static final JexlEngine jexlEngine;

	static {
		Map<String, Object> funcs = new HashMap<>();
		funcs.put("arrays", new ArraysFunction());
		
		jexlEngine = new JexlBuilder().cache(512).silent(false).strict(false).namespaces(funcs).create();
	}

	private JexlExpressionFactory() {}

	public static JexlExpression createExpression(String expr) {
		return jexlEngine.createExpression(ExpressionProcessor.escapedToEncoded(expr));
	}


	public static JexlEngine getEngine() {
		return jexlEngine;
	}
}
