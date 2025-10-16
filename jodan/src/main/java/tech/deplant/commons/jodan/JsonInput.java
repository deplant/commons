package tech.deplant.commons.jodan;

import tech.deplant.commons.jodan.data.JsonElement;
import tech.deplant.commons.jodan.data.JsonIterator;

import java.text.CharacterIterator;
import java.text.StringCharacterIterator;

public class JsonInput {

	public static void parse(String json) {
		final JsonIterator it = new JsonIterator(json);
		System.out.println(JsonElement.parse(it));
	}

}
