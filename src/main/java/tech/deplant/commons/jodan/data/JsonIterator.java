package tech.deplant.commons.jodan.data;

import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class JsonIterator {

	private final CharacterIterator it;
	private final List<String> breadcrumbs = new ArrayList<>();

	public JsonIterator(String json) {
		this.it = new StringCharacterIterator(json);
	}

	public static final char DONE = '\uFFFF';

	public char first() {
		return it.first();
	}

	public char curr() {
		return it.current();
	}

	public char next() {
		return it.next();
	}
}
