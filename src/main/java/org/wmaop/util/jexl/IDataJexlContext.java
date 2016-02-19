package org.wmaop.util.jexl;

import java.util.StringTokenizer;

import org.apache.commons.jexl2.JexlContext;
import org.apache.log4j.Logger;

import com.wm.data.IData;
import com.wm.data.IDataCursor;
import com.wm.data.IDataFactory;
import com.wm.data.IDataUtil;

public class IDataJexlContext implements JexlContext {

	private static final Logger logger = Logger.getLogger(IDataJexlContext.class);
	private final IData idata;

	public IDataJexlContext(IData idata) {
		this.idata = idata;
	}

	public Object get(String name) {
		if (name.indexOf("__") != -1) {
			name = ExpressionProcessor.decode(name);
		}
		IDataCursor cursor = idata.getCursor();
		Object o = IDataUtil.get(cursor, name);
		cursor.destroy();
		Object ret = o;
		try {
			if (o instanceof IData[]) {
				IData[] idataArr = ((IData[]) o);
				IDataJexlContext[] arr = new IDataJexlContext[idataArr.length];
				for (int i = 0; i < idataArr.length; i++) {
					arr[i] = new IDataJexlContext(idataArr[i]);
				}
				ret = arr;
			} else if (o instanceof IData) {
				ret = new IDataJexlContext((IData) o);
			}
		} catch (Exception e) {
			logger.error("Error evaluating: " + name, e);
			throw new RuntimeException(e);
		}
		return ret;
	}

	public void set(String name, Object value) {
		StringTokenizer st = new StringTokenizer(name, ".");
		IData id = idata;
		while (st.hasMoreElements()) {
			IDataCursor idc = id.getCursor();
			name = ExpressionProcessor.escapedToEncoded(st.nextToken());
			if (st.hasMoreTokens()) {
				id = IDataFactory.create();
				IDataUtil.put(idc, name, id);
				idc.destroy();
				continue;
			}
			IDataUtil.put(idc, name, value.toString());
			idc.destroy();
		}
	}

	public boolean has(String name) {
		IDataCursor cursor = idata.getCursor();
		Object o = IDataUtil.get(cursor, ExpressionProcessor.decode(name));
		cursor.destroy();
		return o != null;
	}

	public IData toIData() {
		return idata;
	}
}
