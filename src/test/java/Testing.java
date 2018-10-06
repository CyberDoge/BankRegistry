import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Testing {
    @Test
    void testFileRegExp() {
        String s = "asd0312.dbf";
        assertTrue(s.matches("^[A-Za-z]{3}\\d{4}\\.dbf$"));
    }
    @Test
    void testDateRegExp() {
        String s1 = "0312";
        assertTrue(s1.matches("^[0-9]{4}$"));
        String s2 = "312";
        assertFalse(s2.matches("^[0-9]{4}$"));
        String s3 = "a312";
        assertFalse(s3.matches("^[0-9]{4}$"));
        String s4 = "21312";
        assertFalse(s4.matches("^[0-9] {4}$"));
    }
}
