package org.wmaop.util.pipeline;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.webmethods.util.Pair;

public class NameParserTest {
	@Test
	public void shouldParseServiceName() {
		Pair<String, String> fp1 = NameParser.parseFQServiceName("foo");
		assertEquals("", fp1.getFirst());
		assertEquals("foo", fp1.getSecond());
	}		
	
	@Test
	public void shouldParsePackageAndService() {
		Pair<String, String> fp1 = NameParser.parseFQServiceName("foo:bar");
		assertEquals("foo", fp1.getFirst());
		assertEquals("bar", fp1.getSecond());
	}

	@Test
	public void shouldHandleBlankServiceName() {
		Pair<String, String> fp1 = NameParser.parseFQServiceName("");
		assertEquals("", fp1.getFirst());
		assertEquals("", fp1.getSecond());
	}

	@Test
	public void shouldHandleNullServiceName() {
		Pair<String, String> fp1 = NameParser.parseFQServiceName(null);
		assertEquals("", fp1.getFirst());
		assertEquals("", fp1.getSecond());
	}
}
