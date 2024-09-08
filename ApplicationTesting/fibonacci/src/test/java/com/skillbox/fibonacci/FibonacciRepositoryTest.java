package com.skillbox.fibonacci;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class FibonacciRepositoryTest extends PostgresTestContainerInitializer {

    @Autowired
    private FibonacciRepository repository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    @DisplayName("Test Save New Fibonacci Number")
    public void testSaveNewFibonacciNumber() {
        // Arrange
        FibonacciNumber number = new FibonacciNumber(5, 5);

        // Act
        repository.save(number);
        testEntityManager.flush();
        testEntityManager.clear();

        // Assert
        List<FibonacciNumber> actual = jdbcTemplate.query(
                "SELECT * FROM fibonacci_number WHERE index = 5",
                (rs, rowNum) -> new FibonacciNumber(rs.getInt("index"), rs.getInt("value"))
        );

        assertEquals(1, actual.size());
        assertEquals(number.getIndex(), actual.get(0).getIndex());
        assertEquals(number.getValue(), actual.get(0).getValue());
    }

    @Test
    @DisplayName("Find by index")
    public void testFindByIndex() {
        // Arrange
        FibonacciNumber number = new FibonacciNumber(7, 13);
        testEntityManager.persistAndFlush(number);
        testEntityManager.clear();

        // Act
        Optional<FibonacciNumber> result = repository.findByIndex(7);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(7, result.get().getIndex());
        assertEquals(13, result.get().getValue());
    }

    @Test
    @DisplayName("Test Duplicated Number")
    public void testInsertDuplicateNumber() {
        // Arrange
        FibonacciNumber number1 = new FibonacciNumber(8, 21);
        FibonacciNumber number2 = new FibonacciNumber(8, 21);

        // Act & Assert
        testEntityManager.persistAndFlush(number1);
        testEntityManager.clear();

        assertDoesNotThrow(() -> {
            repository.save(number2);
            testEntityManager.flush();
        });

        // Check that there's only one entry in the database
        List<FibonacciNumber> actual = jdbcTemplate.query(
                "SELECT * FROM fibonacci_number WHERE index = 8",
                (rs, rowNum) -> new FibonacciNumber(rs.getInt("index"), rs.getInt("value"))
        );

        assertEquals(1, actual.size());
    }
}