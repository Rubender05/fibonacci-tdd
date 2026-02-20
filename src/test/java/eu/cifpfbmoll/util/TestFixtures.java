package eu.cifpfbmoll.util;

import eu.cifpfbmoll.model.Category;
import eu.cifpfbmoll.model.FibonacciRecord;
import eu.cifpfbmoll.model.User;
import java.util.ArrayList;
import java.util.List;

/**
 * Fixture class for creating test data.
 * This class provides factory methods for creating test entities.
 */
public class TestFixtures {

    /**
     * Create a list of test users.
     * @return List of User objects for testing
     */
    public static List<User> createTestUsers() {
        List<User> users = new ArrayList<>();
        users.add(createUser("Ruben Arcos", "ruben@example.com"));
        users.add(createUser("Juan Perez", "juan@example.com"));
        users.add(createUser("Maria Garcia", "maria@example.com"));
        users.add(createUser("Alex Lopez", "alex@example.com"));
        users.add(createUser("Sara Ruiz", "sara@example.com"));
        users.add(createUser("David Sanz", "david@example.com"));
        users.add(createUser("Lucia Gil", "lucia@example.com"));
        users.add(createUser("Carlos Bo", "carlos@example.com"));
        users.add(createUser("Elena Ma", "elena@example.com"));
        users.add(createUser("Marc Tur", "marc@example.com"));
        return users;
    }

    /**
     * Create a single test user.
     * @param name User name
     * @param email User email
     * @return User object
     */
    public static User createUser(String name, String email) {
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        return user;
    }

    /**
     * Create a list of test categories.
     * @return List of Category objects for testing
     */
    public static List<Category> createTestCategories() {
        List<Category> categories = new ArrayList<>();
        categories.add(createCategory("Basico"));
        categories.add(createCategory("Intermedio"));
        categories.add(createCategory("Avanzado"));
        categories.add(createCategory("Experimental"));
        categories.add(createCategory("Educacion"));
        categories.add(createCategory("Matematicas"));
        categories.add(createCategory("Informatica"));
        categories.add(createCategory("Pruebas"));
        categories.add(createCategory("Ciencia"));
        categories.add(createCategory("Tecnologia"));
        return categories;
    }

    /**
     * Create a single test category.
     * @param name Category name
     * @return Category object
     */
    public static Category createCategory(String name) {
        Category category = new Category();
        category.setCategoryName(name);
        return category;
    }

    /**
     * Create a list of test Fibonacci records.
     * @return List of FibonacciRecord objects for testing
     */
    public static List<FibonacciRecord> createTestFibonacciRecords() {
        List<FibonacciRecord> records = new ArrayList<>();
        records.add(createFibonacciRecord(0, 0));
        records.add(createFibonacciRecord(1, 1));
        records.add(createFibonacciRecord(2, 1));
        records.add(createFibonacciRecord(3, 2));
        records.add(createFibonacciRecord(4, 3));
        records.add(createFibonacciRecord(5, 5));
        records.add(createFibonacciRecord(6, 8));
        records.add(createFibonacciRecord(7, 13));
        records.add(createFibonacciRecord(8, 21));
        records.add(createFibonacciRecord(10, 55));
        return records;
    }

    /**
     * Create a single test Fibonacci record.
     * @param inputN Input value for Fibonacci calculation
     * @param result Expected Fibonacci result
     * @return FibonacciRecord object
     */
    public static FibonacciRecord createFibonacciRecord(int inputN, int result) {
        FibonacciRecord record = new FibonacciRecord();
        record.setInputN(inputN);
        record.setResult(result);
        return record;
    }
}
