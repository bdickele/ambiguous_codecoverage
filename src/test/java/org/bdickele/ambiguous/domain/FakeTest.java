package org.bdickele.ambiguous.domain;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Bertrand DICKELE
 */
public class FakeTest {


    @Test
    public void test_1() {
        assertThat(to_be_tested("A")).isEqualTo(1);
    }

    /*
    @Test
    public void test_2() {
        assertThat(to_be_tested("A")).isEqualTo(1);
        assertThat(to_be_tested("B")).isEqualTo(2);
        assertThat(to_be_tested("C")).isEqualTo(3);
        assertThat(to_be_tested("D")).isEqualTo(4);
        assertThat(to_be_tested("E")).isEqualTo(5);
        assertThat(to_be_tested("F")).isEqualTo(6);
    }
    */

    public Integer to_be_tested(String s) {
        return 1;
    }
}
