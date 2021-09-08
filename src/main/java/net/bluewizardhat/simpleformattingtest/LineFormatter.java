package net.bluewizardhat.simpleformattingtest;

import java.util.stream.Stream;

/**
 * Formats words into lines.
 */
public class LineFormatter {
	private SingleLineFormatter formatter;
	private int width;
	private StringBuilder builder = new StringBuilder();
	private Stream.Builder<String> lines = Stream.builder();

	public LineFormatter(SingleLineFormatter formatter, int width) {
		this.formatter = formatter;
		this.width = width;
	}

	private void accept(String word) {
		if (builder.length() == 0) {
			builder.append(word);
		} else if (builder.length() + word.length() + 1 < width) {
			builder.append(" ");
			builder.append(word);
		} else {
			lines.accept(formatter.format(width, builder.toString()));
			builder = new StringBuilder();
			builder.append(word);
		}
	}

	private Stream<String> acceptLast() {
		if (builder.length() > 0) {
			lines.accept(formatter.format(width, builder.toString()));
		}
		return lines.build();
	}

	public static Stream<String> convertWordsToLines(SingleLineFormatter formatter, int width, Stream<String> words) {
		LineFormatter lf = new LineFormatter(formatter, width);
		words.forEach(lf::accept);
		return lf.acceptLast();
	}
}
