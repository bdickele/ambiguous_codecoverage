package org.bdickele.ambiguous.domain;

import com.openpojo.reflection.PojoClass;
import com.openpojo.reflection.impl.PojoClassFactory;
import com.openpojo.validation.Validator;
import com.openpojo.validation.ValidatorBuilder;
import com.openpojo.validation.test.impl.GetterTester;
import com.openpojo.validation.test.impl.SetterTester;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Test;


public class DomainPlumbingTest {

    @Test
    public void test_entities_equals() {
        EqualsVerifier.forClass(Destination.class)
                .verify();
    }

    @Test
    public void test_entities_getters_setters() {
        Validator openPojoValidator = ValidatorBuilder.create()
                .with(new SetterTester(), new GetterTester())
                .build();

        PojoClass pojoClass = PojoClassFactory.getPojoClass(Destination.class);
        openPojoValidator.validate(pojoClass);
    }
}
