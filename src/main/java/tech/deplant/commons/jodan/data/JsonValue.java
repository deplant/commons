package tech.deplant.commons.jodan.data;

import tech.deplant.commons.Strings;

import java.math.BigDecimal;

public sealed interface JsonValue extends JsonElement permits JsonBoolean, JsonNumber, JsonNull, JsonString {
}
