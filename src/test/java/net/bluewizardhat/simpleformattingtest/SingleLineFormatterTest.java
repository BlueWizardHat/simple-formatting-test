package net.bluewizardhat.simpleformattingtest;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SingleLineFormatterTest {
	@Test(expected = NullPointerException.class)
	public void test_Null_Formatter() {
		SingleLineFormatter.getFormatter(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_Bad_Formatter() {
		SingleLineFormatter.getFormatter("don't exist");
	}

	@Test
	public void testLeft() {
		SingleLineFormatter formatter = SingleLineFormatter.getFormatter("left");
		assertEquals("pling", formatter.format(3, "pling"));
		assertEquals("pling     ", formatter.format(10, "pling"));
	}

	@Test
	public void testRight() {
		SingleLineFormatter formatter = SingleLineFormatter.getFormatter("right");
		assertEquals("pling", formatter.format(3, "pling"));
		assertEquals("     pling", formatter.format(10, "pling"));
	}

	@Test
	public void testCenter() {
		SingleLineFormatter formatter = SingleLineFormatter.getFormatter("center");
		assertEquals("pling", formatter.format(3, "pling"));
		assertEquals("  pling   ", formatter.format(10, "pling"));
		assertEquals("   pling   ", formatter.format(11, "pling"));
	}
}
