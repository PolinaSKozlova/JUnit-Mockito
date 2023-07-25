package edu.school21.numbers;

import edu.school21.exceptions.IllegalNumberException;

public class NumberWorker {
    public boolean isPrime(int number) throws IllegalNumberException {
        if (checkNumber(number)) {
            return numberIsPrime(number);
        } else {
            throw new IllegalNumberException("Number is not prime");
        }
    }

    public int digitsSum(int number) {
        int sum = 0;
        for (; number > 0; sum += number % 10, number /= 10) {
        }
        return sum;
    }

    private boolean numberIsPrime(int value) {
        int d = 3;
        if (value % 2 == 0) {
            return value == 2;
        }
        while (d * d <= value && value % d != 0) {
            d += 2;
        }
        return d * d > value;
    }

    private boolean checkNumber(int number) {
        if (number < 2) {
            return false;
        } else {
            return true;
        }
    }
}
