package eu.cifpfbmoll;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import eu.cifpfbmoll.model.*;

class FibonacciTest {
    private Fibonacci fibo = new Fibonacci();
    @Test void t0() { assertEquals(0, fibo.calculate(0)); }
    @Test void t1() { assertEquals(1, fibo.calculate(1)); }
    @Test void t2() { assertEquals(1, fibo.calculate(2)); }
    @Test void t3() { assertEquals(2, fibo.calculate(3)); }
    @Test void t4() { assertEquals(3, fibo.calculate(4)); }
    @Test void t5() { assertEquals(5, fibo.calculate(5)); }
    @Test void t6() { assertEquals(8, fibo.calculate(6)); }
    @Test void t7() { assertEquals(13, fibo.calculate(7)); }
    @Test void t8() { assertEquals(21, fibo.calculate(8)); }
    @Test void t9() { assertThrows(IllegalArgumentException.class, () -> fibo.calculate(-1)); }
    @Test void u1() { User u = new User(); u.setName("R"); assertEquals("R", u.getName()); }
    @Test void u2() { User u = new User(); u.setEmail("a@a.com"); assertEquals("a@a.com", u.getEmail()); }
    @Test void u3() { Category c = new Category(); c.setCategoryName("M"); assertEquals("M", c.getCategoryName()); }
    @Test void u4() {
        FibonacciRecord r = new FibonacciRecord();
        r.setInputN(10);
        assertEquals(10, r.getInputN());
    }
    @Test void u5() { assertNotNull(new User()); }
    @Test void u6() { assertNotNull(new Category()); }
    @Test void u7() { assertNotNull(new FibonacciRecord()); }
    @Test void u8() { User u = new User(); u.setId(1L); assertEquals(1L, u.getId()); }
    @Test void u9() { Category c = new Category(); c.setId(5L); assertEquals(5L, c.getId()); }
    @Test void u10() {
        FibonacciRecord r = new FibonacciRecord();
        r.setResult(55);
        assertEquals(55, r.getResult());
    }
    @Test void x1() { assertTrue(fibo.calculate(5) > 0); }
    @Test void x2() { assertNotEquals(10, fibo.calculate(4)); }
    @Test void x3() { User u = new User(); assertNull(u.getName()); }
    @Test void x4() { Category c = new Category(); assertNull(c.getCategoryName()); }
    @Test void x5() { FibonacciRecord r = new FibonacciRecord(); assertEquals(0, r.getInputN()); }
    @Test void x6() { assertEquals(144, fibo.calculate(12)); }
    @Test void x7() { assertNotNull(fibo.toString()); }
    @Test void x8() { User u = new User(); u.setName(""); assertEquals("", u.getName()); }
    @Test void x9() { Category c = new Category(); c.setCategoryName("X"); assertNotNull(c.getCategoryName()); }
    @Test void x10() { assertEquals(1, fibo.calculate(1)); }
}