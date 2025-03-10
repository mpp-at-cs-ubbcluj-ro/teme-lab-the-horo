import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SimpleTest {
    @org.junit.jupiter.api.Test
    public void test1() {
        assertEquals(3, 1 + 2);
    }
    @org.junit.jupiter.api.Test
    public void testfloat() {
        assertEquals(
                Stream.of(1.0, 2.0, 3.0).mapToDouble(Double::doubleValue).sum(),
                6);

    }
}
