package tech.deplant.commons.jodan;

public sealed interface JsonValue extends JsonElement permits JsonBoolean, JsonNull, JsonNumber, JsonString {
}
