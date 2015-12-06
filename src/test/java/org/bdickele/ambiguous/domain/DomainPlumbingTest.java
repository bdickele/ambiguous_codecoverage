package org.bdickele.ambiguous.domain;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;
import org.junit.Test;


public class DomainPlumbingTest {

    @Test
    public void test_entities_equals() {
        EqualsVerifier.forClass(Destination.class)
                .withRedefinedSuperclass().suppress(Warning.STRICT_INHERITANCE)
                .verify();
    }
}
