package tech.deplant.commons.jodan;

public sealed interface JsonElement permits JsonArray, JsonObject, JsonValue {

	String stringify();
}
