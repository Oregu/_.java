import junit.framework.Assert;
import org.junit.Test;

import java.util.function.BiFunction;
import java.util.function.Function;

import static junit.framework.Assert.*;

public class _Test {

    @Test
    public void curry() {
        BiFunction<String, String, String> concat = (a, b) -> a.concat(" ").concat(b);

        Function<String, Function<String, String>> curried = _.curry(concat);
        assertEquals(concat.apply("Jake", "Smith"), curried.apply("Jake").apply("Smith"));
    }
}