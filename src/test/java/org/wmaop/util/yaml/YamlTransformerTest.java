package org.wmaop.util.yaml;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import com.wm.data.IData;
import com.wm.util.coder.IDataXMLCoder;
import com.wm.util.coder.InvalidDatatypeException;

public class YamlTransformerTest {

	@Test
	public void test() throws InvalidDatatypeException, IOException {
		IData idata = new IDataXMLCoder().decode(this.getClass().getClassLoader().getResourceAsStream("complex.xml"));
		System.out.println(new YamlTransformer().fromIData(idata));
	}

}
