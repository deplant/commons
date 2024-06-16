package tech.deplant.commons.test.unit.json;

public sealed interface JsonElement permits JsonArray, JsonObject, JsonValue {

	String stringify();
}
