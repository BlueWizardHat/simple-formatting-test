package net.bluewizardhat.simpleformattingtest;

import java.util.Arrays;
import java.util.stream.Collectors;

public abstract class SingleLineFormatter {
	public static final String ALIGN_LEFT = "left";
	public static final String ALIGN_RIGHT = "right";
	public static final String ALIGN_CENTER = "center";

	public static final String[] ALL_ALIGNMENTS = { ALIGN_LEFT, ALIGN_RIGHT, ALIGN_CENTER };

	public static String allAlignments() {
		return Arrays.stream(SingleLineFormatter.ALL_ALIGNMENTS).collect(Collectors.joining(", "));
	}

	/**
	 * Formats a single line. If the input string is longer than the desired length no attempt is made to shorten it.
	 * @param length desired length of the text that should be returned
	 * @param text the input string with no padding
	 */
	public abstract String format(int length, String text);

	protected void pad(int spaces, StringBuilder builder) {
		for (int i = 0; i < spaces; i++) {
			builder.append(' ');
		}
	}

	private static class LeftAlignFormatter extends SingleLineFormatter {
		public String format(int length, String text) {
			StringBuilder builder = new StringBuilder(length);
			builder.append(text);
			pad(length - text.length(), builder);
			return builder.toString();
		}
	}

	private static class RightAlignFormatter extends SingleLineFormatter {
		public String format(int length, String text) {
			StringBuilder builder = new StringBuilder(length);
			pad(length - text.length(), builder);
			builder.append(text);
			return builder.toString();
		}
	}

	private static class CenterAlignFormatter extends SingleLineFormatter {
		public String format(int length, String text) {
			StringBuilder builder = new StringBuilder(length);
			pad((length - text.length()) / 2, builder);
			builder.append(text);
			pad(length - builder.length(), builder);
			return builder.toString();
		}
	}

	public static SingleLineFormatter getFormatter(String alignment) {
		if (alignment == null) {
			throw new NullPointerException("null not allowed");
		}
		switch (alignment.toLowerCase()) {
			case ALIGN_LEFT:
				return new LeftAlignFormatter();
			case ALIGN_RIGHT:
				return new RightAlignFormatter();
			case ALIGN_CENTER:
				return new CenterAlignFormatter();
			default:
				throw new IllegalArgumentException("alignment '" + alignment + "' is not supported, must be one of " + allAlignments());
		}
	}
}
