package me.trotyl.homework;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Random;

import static org.hamcrest.core.Is.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class CliTest {
    private ByteArrayOutputStream testOut;

    @Before
    public void setUp() throws Exception {
        Random random = mock(Random.class);
        when(random.nextInt(9877)).thenReturn(1234);
        Cli.random = random;

        testOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(testOut));
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void should_show_welcome_when_started() throws Exception {
        String input = String.join(System.getProperty("line.separator"),
                "1234", "1234", "1234", "1234", "1234", "1234");
        ByteArrayInputStream testIn = new ByteArrayInputStream(input.getBytes());
        System.setIn(testIn);

        Cli.main(null);

        String[] outputLines = testOut.toString().split(System.getProperty("line.separator"));

        assertThat(outputLines[0], is("Welcome!"));
    }

    @Test
    public void should_prompt_duplicate_when_input_duplicated_digits() throws Exception {
        String input = String.join(System.getProperty("line.separator"),
                "1111", "1234", "1234", "1234", "1234", "1234", "1234");
        ByteArrayInputStream testIn = new ByteArrayInputStream(input.getBytes());
        System.setIn(testIn);

        Cli.main(null);

        String[] outputLines = testOut.toString().split(System.getProperty("line.separator"));

        assertThat(outputLines[2], is("Please input your number(6):Cannot input duplicate numbers!"));
    }

    @Test
    public void should_have_proper_order_of_prompt_if_always_wrong() throws Exception {
        String input = String.join(System.getProperty("line.separator"),
                "5678", "5678", "5678", "5678", "5678", "5678");
        ByteArrayInputStream testIn = new ByteArrayInputStream(input.getBytes());
        System.setIn(testIn);

        Cli.main(null);

        String[] outputLines = testOut.toString().split(System.getProperty("line.separator"));

        assertThat(outputLines.length, is(13));
        assertThat(outputLines[0], is("Welcome!"));
        assertThat(outputLines[1], is(""));
        assertThat(outputLines[2], is("Please input your number(6):0A0B"));
        assertThat(outputLines[3], is(""));
        assertThat(outputLines[4], is("Please input your number(5):0A0B"));
        assertThat(outputLines[5], is(""));
        assertThat(outputLines[6], is("Please input your number(4):0A0B"));
        assertThat(outputLines[7], is(""));
        assertThat(outputLines[8], is("Please input your number(3):0A0B"));
        assertThat(outputLines[9], is(""));
        assertThat(outputLines[10], is("Please input your number(2):0A0B"));
        assertThat(outputLines[11], is(""));
        assertThat(outputLines[12], is("Please input your number(1):Game Over"));
    }

    @Test
    public void should_have_proper_order_of_prompt_if_right_at_first() throws Exception {
        String input = String.join(System.getProperty("line.separator"),
                "1234");
        ByteArrayInputStream testIn = new ByteArrayInputStream(input.getBytes());
        System.setIn(testIn);

        Cli.main(null);

        String[] outputLines = testOut.toString().split(System.getProperty("line.separator"));

        assertThat(outputLines.length, is(3));
        assertThat(outputLines[0], is("Welcome!"));
        assertThat(outputLines[1], is(""));
        assertThat(outputLines[2], is("Please input your number(6):Congratulations!"));
    }

    @Test
    public void should_have_proper_order_of_prompt_if_right_after_trials() throws Exception {
        String input = String.join(System.getProperty("line.separator"),
                "1236", "1235", "1234");
        ByteArrayInputStream testIn = new ByteArrayInputStream(input.getBytes());
        System.setIn(testIn);

        Cli.main(null);

        String[] outputLines = testOut.toString().split(System.getProperty("line.separator"));

        assertThat(outputLines.length, is(7));
        assertThat(outputLines[0], is("Welcome!"));
        assertThat(outputLines[1], is(""));
        assertThat(outputLines[2], is("Please input your number(6):3A0B"));
        assertThat(outputLines[3], is(""));
        assertThat(outputLines[4], is("Please input your number(5):3A0B"));
        assertThat(outputLines[5], is(""));
        assertThat(outputLines[6], is("Please input your number(4):Congratulations!"));
    }
}
