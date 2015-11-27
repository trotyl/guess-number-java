package me.trotyl.homework;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class GeneratorTest {

    private Generator generator;

    @Before
    public void setUp() throws Exception {
        generator = new Generator(new Random());
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void generate_should_get_a_4_length_string() throws Exception {
        String number = generator.generate();

        assertThat(number.length(), is(4));
    }

    @Test
    public void generate_should_get_a_string_of_number() throws Exception {
        String number = generator.generate();

        boolean result = number.chars().allMatch(Character::isDigit);
        assertThat(result, is(true));
    }

    @Test
    public void generate_should_get_a_number_whose_digits_are_different() throws Exception {
        String number = generator.generate();

        int count = (int)number.chars().distinct().count();
        assertThat(count, is(4));
    }
}
