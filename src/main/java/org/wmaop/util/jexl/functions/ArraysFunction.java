package org.wmaop.util.jexl.functions;

import java.util.regex.Pattern;

public class ArraysFunction {
	
	public boolean contains(String[] arr, String substring) {
		for (String v : arr) {
			if (substring == null) {
				if (v == null) {
					return true;
				}
			} else if (v.contains(substring)) {
				return true;
			}
		}
		return false;
	}

	public boolean matches(String[] arr, String regex) {
		if (arr == null) {
			throw new JexlFunctionException("Array passed to arrays:matches is null");
		}

		Pattern p = Pattern.compile(regex);
		for (String v : arr) {
			if (p.matcher(v).matches()) {
				return true;
			}
		}
		return false;
	}

}
