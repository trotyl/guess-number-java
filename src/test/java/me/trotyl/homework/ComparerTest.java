package me.trotyl.homework;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


public class ComparerTest {

    private Comparer comparer;

    @Before
    public void setUp() throws Exception {
        comparer = new Comparer();
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void compare_should_get_4A0B_of_2_identical_number() throws Exception {
        // Given
        String result1 = comparer.compare("1234", "1234");
        String result2 = comparer.compare("4321", "4321");

        // Then
        assertThat(result1, is("4A0B"));
        assertThat(result2, is("4A0B"));
    }

    @Test
    public void compare_should_get_3A0B_of_2_numbers_with_3_same_digits() throws Exception {
        String result1 = comparer.compare("1234", "1235");
        String result2 = comparer.compare("4321", "4320");

        assertThat(result1, is("3A0B"));
        assertThat(result2, is("3A0B"));
    }

    @Test
    public void compare_should_get_2A2B_of_2_numbers_with_same_digits_and_half_same_positions() throws Exception {
        String result1 = comparer.compare("1234", "1324");
        String result2 = comparer.compare("4321", "4231");

        assertThat(result1, is("2A2B"));
        assertThat(result2, is("2A2B"));
    }

    @Test
    public void compare_should_get_0A3B_of_2_numbers_with_3_same_digits_but_not_same_positions() throws Exception {
        String result1 = comparer.compare("1234", "2345");
        String result2 = comparer.compare("4321", "3210");

        assertThat(result1, is("0A3B"));
        assertThat(result2, is("0A3B"));
    }

    @Test
    public void compare_should_get_0A4B_of_2_numbers_with_4_same_digits_but_not_same_positions() throws Exception {
        String result = comparer.compare("1234", "4321");

        assertThat(result, is("0A4B"));
    }

    @Test
    public void compare_should_get_0A0B_of_2_numbers_with_no_same_digit() throws Exception {
        String result = comparer.compare("1234", "5678");

        assertThat(result, is("0A0B"));
    }
}
