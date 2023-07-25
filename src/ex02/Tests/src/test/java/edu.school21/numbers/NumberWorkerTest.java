package edu.school21.numbers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class NumberWorkerTest {

    @ParameterizedTest
    @DisplayName("Test for prime numbers")
    @ValueSource(strings = {"113", "3", "2", "167"})
    void isPrimeForPrimesTest(int number) {
        NumberWorker nW = new NumberWorker();
        try {
            assertEquals(true, nW.isPrime(number));
        } catch (IllegalNumberException e) {
            e.printStackTrace();
        }
    }

    @ParameterizedTest
    @DisplayName("Test for not prime numbers")
    @ValueSource(strings = {"200", "9", "8734", "99"})
    void isPrimeForNotPrimesTest(int number) {
        NumberWorker nW = new NumberWorker();
        try {
            assertEquals(false, nW.isPrime(number));
        } catch (IllegalNumberException e) {
            e.printStackTrace();
        }
    }

    @ParameterizedTest
    @DisplayName("Test for exceptions in primeNumbers")
    @ValueSource(strings = {"-100", "0", "1"})
    void isPrimeForIncorrectNumbersTest(int number) {
        NumberWorker nW = new NumberWorker();
        assertThrows(IllegalNumberException.class, () -> nW.isPrime(number));
    }

    @ParameterizedTest
    @DisplayName("Test for counting sum of digits")
    @CsvFileSource(resources = "/data.csv", delimiterString = " ")
    void testDigitsSumTest(int number, int result) {
        NumberWorker nW = new NumberWorker();
        assertEquals(result, nW.digitsSum(number));
    }
}