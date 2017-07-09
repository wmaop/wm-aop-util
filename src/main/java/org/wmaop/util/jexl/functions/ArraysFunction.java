package org.wmaop.util.jexl.functions;

import java.util.regex.Pattern;

import org.wmaop.util.logger.Logger;

public class ArraysFunction {
	
	private static final Logger logger = Logger.getLogger(ArraysFunction.class);
	
	public boolean contains(String[] arr, String substring) {
		if (arr == null) {
			logger.warn("Array passed to arrays:contains is null");
			return false;
		}
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
			logger.warn("Array passed to arrays:matches is null");
			return false;
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
