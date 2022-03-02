package tr.com.hepsiburada.core.enumeration;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.stream.Stream;

@Getter
@AllArgsConstructor
public enum ResponseType {

    PERSONALIZED("personalized"),
    NON_PERSONALIZED("non-personalized");

    @JsonValue
    private final String type;

    @JsonCreator
    public static ResponseType decode(final String type) {
        return Stream.of(ResponseType.values()).filter(targetEnum -> targetEnum.type.equalsIgnoreCase(type)).findFirst().orElse(null);
    }

}
