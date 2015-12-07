package org.wmaop.util.yaml;

import com.wm.data.IData;
import com.wm.data.IDataCursor;

public class YamlTransformer {

	private final String INDENT = "   ";
	
	public String fromIData(IData idata) {
		StringBuilder sb = new StringBuilder();
		String indent = "";
		parseIData(idata, sb, indent);
		return sb.toString();
	}

	private void parseIData(IData idata, StringBuilder sb, String indent) {
		IDataCursor idc = idata.getCursor();
		while(idc.next()) {
			String k = idc.getKey();
			Object v = idc.getValue();
			// TODO - Doesnt handle array notation correctly
			if (v instanceof IData[]) {
				append(sb, indent, k, null);
				IData[] idataArr = ((IData[]) v);
				for (int i = 0; i < idataArr.length; i++) {
					parseIData(idataArr[i], sb, indent + INDENT);
				}
			} else if (v instanceof IData) {
				append(sb, indent, k, null);
				parseIData((IData)v, sb, indent + INDENT);
			} else {
				append(sb, indent, k, v);
			}
		}
	}
	
	private void append(StringBuilder sb, String indent, String k, Object v) {
		sb.append(indent);
		if (k.contains(":") || k.contains("*")) {
			sb.append("\"").append(k).append("\"");
		} else {
			sb.append(k);
		}
		sb.append(": ");
		if (v != null) {
			if (v instanceof String) {
				sb.append("\"").append(v.toString().trim()).append("\"");
			} else {
				sb.append(v.toString().trim());
			}
		}
		sb.append("\r\n");
	}
}
