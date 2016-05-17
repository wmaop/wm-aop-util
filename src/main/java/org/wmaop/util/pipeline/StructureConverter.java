package org.wmaop.util.pipeline;

import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;

import com.wm.data.IData;
import com.wm.data.IDataCursor;
import com.wm.data.IDataFactory;
import com.wm.data.IDataUtil;

public class StructureConverter {

	public IData toIData(Map<String, ?> map) {
		IData idata = IDataFactory.create();
		IDataCursor idc = idata.getCursor();
		for (Entry<String, ?> e : map.entrySet()) {
			IDataUtil.put(idc, e.getKey(), asValue(e.getValue()));
		}
		idc.destroy();
		return idata;
		
	}

	public Object[] toIData(Collection<?> col) {
		return col.toArray(new Object[col.size()]);
	}
	
	@SuppressWarnings("unchecked")
	Object asValue(Object value) {
		if (value instanceof Map) {
			return toIData((Map<String, ?>) value);
		} else if (value instanceof Collection) {
			return toIData((Collection<?>)value);
		}
		return value;
	}
}
