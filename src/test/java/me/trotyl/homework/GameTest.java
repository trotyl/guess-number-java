package me.trotyl.homework;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GameTest {
    private Game game;

    @Before
    public void setUp() throws Exception {
        Random mockedRandom = mock(Random.class);
        when(mockedRandom.nextInt(9877)).thenReturn(1234);

        game = new Game(new Generator(mockedRandom), new Comparer());
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void guess_should_get_right_result_for_certain_input() throws Exception {
        game.start(6);

        String result1 = game.guess("1234").toString();
        String result2 = game.guess("1324").toString();
        String result3 = game.guess("4321").toString();
        String result4 = game.guess("5678").toString();

        assertThat(result1, is("[4, 0]"));
        assertThat(result2, is("[2, 2]"));
        assertThat(result3, is("[0, 4]"));
        assertThat(result4, is("[0, 0]"));
    }

    @Test
    public void guess_should_get_minusOne_if_not_started() throws Exception {
        String result = game.guess("1234").toString();
        assertThat(result, is("[-1, -1]"));
    }

    @Test
    public void guess_should_affect_remaining() throws Exception {
        game.start(2);
        int result1 = game.getRemaining();

        game.guess("5678");
        int result2 = game.getRemaining();

        game.guess("5678");
        int result3 = game.getRemaining();


        assertThat(result1, is(2));
        assertThat(result2, is(1));
        assertThat(result3, is(0));
    }

    @Test
    public void guess_should_get_minusOne_if_is_over() throws Exception {
        game.start(2);

        String result1 = game.guess("5678").toString();
        String result2 = game.guess("5678").toString();
        String result3 = game.guess("5678").toString();

        assertThat(result1, is("[0, 0]"));
        assertThat(result2, is("[0, 0]"));
        assertThat(result3, is("[-1, -1]"));
    }
}
