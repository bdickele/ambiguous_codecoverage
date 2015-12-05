package org.bdickele.ambiguous.domain.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * JPA 2.1 doesn't support new Java 8 Date API
 * Created by Bertrand DICKELE
 */
@Converter
public class LocalDateTimeConverter implements AttributeConverter<LocalDateTime, Timestamp> {

    @Override
    public Timestamp convertToDatabaseColumn(LocalDateTime localDateTime) {
        return localDateTime == null ? null : Timestamp.valueOf(localDateTime);
    }

    @Override
    public LocalDateTime convertToEntityAttribute(Timestamp timestamp) {
        return timestamp == null ? null : timestamp.toLocalDateTime();
    }
}
