package org.bdickele.ambiguous.domain.converter;

import org.bdickele.ambiguous.domain.RequestAgreementStatus;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * Created by Bertrand DICKELE
 */
@Converter
public class RequestAgreementStatusConverter implements AttributeConverter<RequestAgreementStatus, String> {

    @Override
    public String convertToDatabaseColumn(RequestAgreementStatus status) {
        return status.getCode();
    }

    @Override
    public RequestAgreementStatus convertToEntityAttribute(String code) {
        return RequestAgreementStatus.getByCode(code);
    }
}
