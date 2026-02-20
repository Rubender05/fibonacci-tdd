package eu.cifpfbmoll.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Entity Model Unit Tests")
public class EntityModelUnitTest {

    @Test
    @DisplayName("User should initialize with null values")
    void shouldInitializeUserWithNullValues() {
        User user = new User();
        assertNull(user.getName());
        assertNull(user.getEmail());
        assertNull(user.getId());
    }

    @Test
    @DisplayName("User should set and get name correctly")
    void shouldSetAndGetUserName() {
        User user = new User();
        user.setName("John Doe");
        assertEquals("John Doe", user.getName());
    }

    @Test
    @DisplayName("User should set and get email correctly")
    void shouldSetAndGetUserEmail() {
        User user = new User();
        user.setEmail("john@example.com");
        assertEquals("john@example.com", user.getEmail());
    }

    @Test
    @DisplayName("User should set and get ID correctly")
    void shouldSetAndGetUserId() {
        User user = new User();
        user.setId(1L);
        assertEquals(1L, user.getId());
    }

    @Test
    @DisplayName("User should handle empty name")
    void shouldHandleEmptyUserName() {
        User user = new User();
        user.setName("");
        assertEquals("", user.getName());
    }

    @Test
    @DisplayName("Category should initialize with null values")
    void shouldInitializeCategoryWithNullValues() {
        Category category = new Category();
        assertNull(category.getCategoryName());
        assertNull(category.getId());
    }

    @Test
    @DisplayName("Category should set and get name correctly")
    void shouldSetAndGetCategoryName() {
        Category category = new Category();
        category.setCategoryName("Math");
        assertEquals("Math", category.getCategoryName());
    }

    @Test
    @DisplayName("Category should set and get ID correctly")
    void shouldSetAndGetCategoryId() {
        Category category = new Category();
        category.setId(5L);
        assertEquals(5L, category.getId());
    }

    @Test
    @DisplayName("FibonacciRecord should initialize with zero result")
    void shouldInitializeFibonacciRecordWithZeroResult() {
        FibonacciRecord record = new FibonacciRecord();
        assertEquals(0, record.getResult());
    }

    @Test
    @DisplayName("FibonacciRecord should set and get inputN correctly")
    void shouldSetAndGetFibonacciRecordInputN() {
        FibonacciRecord record = new FibonacciRecord();
        record.setInputN(10);
        assertEquals(10, record.getInputN());
    }

    @Test
    @DisplayName("FibonacciRecord should set and get result correctly")
    void shouldSetAndGetFibonacciRecordResult() {
        FibonacciRecord record = new FibonacciRecord();
        record.setResult(55);
        assertEquals(55, record.getResult());
    }

    @Test
    @DisplayName("FibonacciRecord should set and get ID correctly")
    void shouldSetAndGetFibonacciRecordId() {
        FibonacciRecord record = new FibonacciRecord();
        record.setId(1L);
        assertEquals(1L, record.getId());
    }
}
