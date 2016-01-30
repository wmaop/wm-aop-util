package org.wmaop.util.jexl;

public class ExpressionProcessor {

	static String escapedToEncoded(String expr) {
		int slashPos = expr.indexOf('\\');
		if (slashPos == -1) {
			return expr;
		}
		StringBuilder sb = new StringBuilder();
		int lastSlash = -1;
		while (slashPos != -1) {
			sb.append(expr.substring(lastSlash +  1, slashPos));
			char c = expr.charAt(slashPos + 1);
			switch (c) {
			case ':':
				sb.append("___$");
				break;
			case '-':
				sb.append("__$$");
				break;
			case ' ':
				sb.append("__$_");
				break;
			default:
				throw new RuntimeException("Invalid escaped character: " + c);
			}
			lastSlash = ++slashPos;
			slashPos = expr.indexOf('\\', lastSlash + 1);
		}
		if (lastSlash + 1  < expr.length()) {
			sb.append(expr.substring(lastSlash + 1));
		}
		return sb.toString();
	}

	static String decode(String expr) {
		int undPos = expr.indexOf("__");
		if (undPos == -1) {
			return expr;
		}
		// TODO Inefficient, needs replacing as above
		return expr.replace("___$", ":").replace("__$$", "-").replace("__$_", " ");
	}
}
