package tech.deplant.commons.jodan.data;

public sealed interface JsonValue extends JsonElement permits JsonBoolean, JsonNumber, JsonNull, JsonString {
}
