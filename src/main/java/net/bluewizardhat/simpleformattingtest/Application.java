package net.bluewizardhat.simpleformattingtest;

import java.io.IOException;
import java.util.stream.Stream;

public class Application {
	public static void main(String[] args) {
		if (args.length != 2) {
			System.out.println("Two arguments required, alignment and width");
			System.out.println("Alignment: " + SingleLineFormatter.allAlignments());
			System.out.println("Width    : desired width of output");
			System.exit(1);
		}
		String alignment = args[0];
		String width = args[1];

		try {
			SingleLineFormatter formatter = SingleLineFormatter.getFormatter(alignment);
			int printWidth = Integer.parseInt(width);

			new Application().run(formatter, printWidth);
		} catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
			System.exit(1);
		}
	}

	private void run(SingleLineFormatter formatter, int width) throws IOException {
		System.out.println("Enter lines, exit with ctrl-d or an empty line");
		Stream<String> words = WordReader.readWords(System.in);
		Stream<String> lines = LineFormatter.convertWordsToLines(formatter, width, words);

		System.out.println("Formatted text:");
		lines.forEach(System.out::println);
	}
}
