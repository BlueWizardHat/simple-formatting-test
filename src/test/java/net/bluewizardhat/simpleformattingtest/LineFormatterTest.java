package net.bluewizardhat.simpleformattingtest;

import org.junit.Test;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

public class LineFormatterTest {
	@Test
	public void testLeft10() {
		// Setup
		Stream<String> words = Stream.of("This", "text", "should", "be", "left", "aligned");

		// Execute
		Stream<String> lines = LineFormatter.convertWordsToLines(SingleLineFormatter.getFormatter("left"), 10, words);

		// Verify
		String expected = "This text \nshould be \nleft      \naligned   ";
		assertEquals(expected, lines.collect(Collectors.joining("\n")));
	}

	@Test
	public void testLeft20() {
		// Setup
		Stream<String> words = Stream.of("This", "text", "should", "be", "left", "aligned");

		// Execute
		Stream<String> lines = LineFormatter.convertWordsToLines(SingleLineFormatter.getFormatter("left"), 20, words);

		// Verify
		String expected = "This text should be \nleft aligned        ";
		assertEquals(expected, lines.collect(Collectors.joining("\n")));
	}

	@Test
	public void testRight10() {
		// Setup
		Stream<String> words = Stream.of("This", "text", "should", "be", "right", "aligned");

		// Execute
		Stream<String> lines = LineFormatter.convertWordsToLines(SingleLineFormatter.getFormatter("right"), 10, words);

		// Verify
		String expected = " This text\n should be\n     right\n   aligned";
		assertEquals(expected, lines.collect(Collectors.joining("\n")));
	}

	@Test
	public void testRight20() {
		// Setup
		Stream<String> words = Stream.of("This", "text", "should", "be", "right", "aligned");

		// Execute
		Stream<String> lines = LineFormatter.convertWordsToLines(SingleLineFormatter.getFormatter("right"), 20, words);

		// Verify
		String expected = " This text should be\n       right aligned";
		assertEquals(expected, lines.collect(Collectors.joining("\n")));
	}

	@Test
	public void testCenter10() {
		// Setup
		Stream<String> words = Stream.of("This", "text", "should", "be", "center", "aligned");

		// Execute
		Stream<String> lines = LineFormatter.convertWordsToLines(SingleLineFormatter.getFormatter("center"), 10, words);

		// Verify
		String expected = "This text \nshould be \n  center  \n aligned  ";
		assertEquals(expected, lines.collect(Collectors.joining("\n")));
	}

	@Test
	public void testCenter20() {
		// Setup
		Stream<String> words = Stream.of("This", "text", "should", "be", "center", "aligned");

		// Execute
		Stream<String> lines = LineFormatter.convertWordsToLines(SingleLineFormatter.getFormatter("center"), 20, words);

		// Verify
		String expected = "This text should be \n   center aligned   ";
		assertEquals(expected, lines.collect(Collectors.joining("\n")));
	}

	@Test
	public void testPerfectFit() {
		// Setup
		Stream<String> words = Stream.of("4444", "55555");

		// Execute
		Stream<String> lines = LineFormatter.convertWordsToLines(SingleLineFormatter.getFormatter("left"), 10, words);

		// Verify
		String expected = "4444 55555";
		assertEquals(expected, lines.collect(Collectors.joining("\n")));
	}

	@Test
	public void testOneMoreThanPerfectFit() {
		// Setup
		Stream<String> words = Stream.of("55555", "55555");

		// Execute
		Stream<String> lines = LineFormatter.convertWordsToLines(SingleLineFormatter.getFormatter("left"), 10, words);

		// Verify
		String expected = "55555     \n55555     ";
		assertEquals(expected, lines.collect(Collectors.joining("\n")));
	}
}
