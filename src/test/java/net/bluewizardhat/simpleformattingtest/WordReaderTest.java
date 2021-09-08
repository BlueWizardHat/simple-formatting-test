package net.bluewizardhat.simpleformattingtest;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

public class WordReaderTest {
	@Test
	public void testSimpleString() throws IOException {
		// Setup
		ByteArrayInputStream source = new ByteArrayInputStream("Read this text".getBytes(StandardCharsets.UTF_8));

		// Execute
		Stream<String> words = WordReader.readWords(source);

		// Verify
		assertEquals("Read+this+text", words.collect(Collectors.joining("+")));
	}

	@Test
	public void testMultipleSpaces() throws IOException {
		// Setup
		ByteArrayInputStream source = new ByteArrayInputStream(" This   text should be   left aligned  ".getBytes(StandardCharsets.UTF_8));

		// Execute
		Stream<String> words = WordReader.readWords(source);

		// Verify
		assertEquals("This+text+should+be+left+aligned", words.collect(Collectors.joining("+")));
	}

	@Test
	public void testNewlines() throws IOException {
		// Setup
		ByteArrayInputStream source = new ByteArrayInputStream(" This \n  text should be   left aligned\n\n".getBytes(StandardCharsets.UTF_8));

		// Execute
		Stream<String> words = WordReader.readWords(source);

		// Verify
		assertEquals("This+text+should+be+left+aligned", words.collect(Collectors.joining("+")));
	}
}
