package org.bdickele.ambiguous.domain.converter;

import org.bdickele.ambiguous.domain.UserProfile;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * Created by Bertrand DICKELE
 */
@Converter
public class ProfileConverter implements AttributeConverter<UserProfile, String> {

    @Override
    public String convertToDatabaseColumn(UserProfile profile) {
        return profile.getCode();
    }

    @Override
    public UserProfile convertToEntityAttribute(String s) {
        return UserProfile.getByCode(s);
    }
}
