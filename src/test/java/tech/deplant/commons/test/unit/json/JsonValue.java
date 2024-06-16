package tech.deplant.commons.test.unit.json;

public sealed interface JsonValue extends JsonElement permits JsonBoolean, JsonNull, JsonNumber, JsonString {
}
