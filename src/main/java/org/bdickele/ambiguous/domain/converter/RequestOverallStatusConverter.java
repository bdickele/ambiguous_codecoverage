package org.bdickele.ambiguous.domain.converter;

import org.bdickele.ambiguous.domain.RequestOverallStatus;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * Created by Bertrand DICKELE
 */
@Converter
public class RequestOverallStatusConverter implements AttributeConverter<RequestOverallStatus, String> {

    @Override
    public String convertToDatabaseColumn(RequestOverallStatus status) {
        return status.getCode();
    }

    @Override
    public RequestOverallStatus convertToEntityAttribute(String code) {
        return RequestOverallStatus.getByCode(code);
    }
}
