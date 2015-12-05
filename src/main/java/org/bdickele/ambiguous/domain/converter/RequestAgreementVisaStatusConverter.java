package org.bdickele.ambiguous.domain.converter;

import org.bdickele.ambiguous.domain.RequestAgreementVisaStatus;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * Created by Bertrand DICKELE
 */
@Converter
public class RequestAgreementVisaStatusConverter implements AttributeConverter<RequestAgreementVisaStatus, String> {

    @Override
    public String convertToDatabaseColumn(RequestAgreementVisaStatus visaStatus) {
        return visaStatus.getCode();
    }

    @Override
    public RequestAgreementVisaStatus convertToEntityAttribute(String s) {
        return RequestAgreementVisaStatus.getByCode(s);
    }
}
