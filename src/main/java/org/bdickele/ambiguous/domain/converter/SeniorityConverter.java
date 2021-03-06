package org.bdickele.ambiguous.domain.converter;

import org.bdickele.ambiguous.domain.Seniority;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * Created by Bertrand DICKELE
 */
@Converter
public class SeniorityConverter implements AttributeConverter<Seniority, Integer> {

    @Override
    public Integer convertToDatabaseColumn(Seniority seniority) {
        return seniority == null ? null : seniority.getValue();
    }

    @Override
    public Seniority convertToEntityAttribute(Integer integer) {
        return Seniority.of(integer);
    }
}
