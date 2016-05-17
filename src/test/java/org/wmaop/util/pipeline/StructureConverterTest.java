package org.wmaop.util.pipeline;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.wm.data.IData;
import com.wm.data.IDataCursor;
import com.wm.data.IDataUtil;

public class StructureConverterTest {

	@Test
	public void shouldConvertMap() {
		Map<String, Object> m = new HashMap<>();
		m.put("alpha", 1);
		m.put("beta", "value");
		List<String> lst = new ArrayList<>();
		lst.add("a");
		lst.add("b");
		m.put("list", lst);
		
		Map<String, Object> m2 = new HashMap<>();
		m2.put("alpha2", 1);
		m2.put("beta2", "value");
		m.put("amap", m2);
		
		IData idata = new StructureConverter().toIData(m);
		IDataCursor idc = idata.getCursor();
		assertEquals(1, IDataUtil.get(idc, "alpha"));
		assertEquals("value", IDataUtil.get(idc, "beta"));
		
		Object[] arry = IDataUtil.getObjectArray(idc, "list");
		assertEquals(2, arry.length);
		
		IData idata2 = IDataUtil.getIData(idc, "amap");
		IDataCursor idc2 = idata2.getCursor();
		assertEquals(1, IDataUtil.get(idc2, "alpha2"));
	}

}
 