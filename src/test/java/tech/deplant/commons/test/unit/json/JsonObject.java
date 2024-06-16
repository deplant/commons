package tech.deplant.commons.test.unit.json;

import java.util.Map;

public record JsonObject(Map<String, JsonElement> properties) implements JsonElement {
	@Override
	public String stringify() {
		StringBuilder stringBuilder = new StringBuilder("{");
		stringBuilder.append(String.join(",",
		                                 properties().entrySet()
		                                             .stream()
		                                             .map(entry -> "\"%s\":%s".formatted(entry.getKey(),entry.getValue()
		                                                                                                                                          .stringify()))
		                                             .toList()));
		stringBuilder.append("}");
		return stringBuilder.toString();
	}
}
