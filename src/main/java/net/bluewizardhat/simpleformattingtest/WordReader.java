package net.bluewizardhat.simpleformattingtest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Stream;

/**
 * Reads from input stream and splits into words.
 */
public class WordReader {
	public static Stream<String> readWords(InputStream is) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(is));

		Stream.Builder<String[]> words = Stream.builder();
		while (true) {
			String line = in.readLine();

			if (line == null || "".equals(line)) {
				break;
			}

			String[] lineWords = line.split("(\\s|\\n)+");
			words.accept(lineWords);
		}

		return words.build().flatMap(Arrays::stream).filter(word -> word != null && !"".equals(word));
	}
}
