package tech.deplant.commons.jodan;

public record JsonArray(JsonElement[] elements) implements JsonElement {
	@Override
	public String stringify() {
		StringBuilder stringBuilder = new StringBuilder("[");
		for (int i = 0; i < elements.length; i++) {
			stringBuilder.append(elements[i].stringify());
			if (i < elements.length - 1) {
				stringBuilder.append(",");
			}
		}
		stringBuilder.append("]");
		return stringBuilder.toString();
	}
}
