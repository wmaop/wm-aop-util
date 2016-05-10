package org.wmaop.util.pipeline;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.wm.data.IData;
import com.wm.data.IDataCursor;
import com.wm.data.IDataFactory;
import com.wm.data.IDataUtil;
import com.wm.util.Values;

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

	public IData toIData(Collection<?> col) {
		Values values = new Values();
		List<Object> idl = new ArrayList<>();
		for (Object o : col) {
			idl.add(asValue(o));
		}
		values.put(idl.toArray(new IData[idl.size()]));
		return values;
		
	}

	
	Object asValue(Object value) {
		if (value instanceof Map) {
			return toIData((Map<String, ?>) value);
		} else if (value instanceof Collection) {
			return toIData((Collection)value);
		}
		return value;
	}
}
