package utils;

import java.util.Random;

/**
 * A random number generator
 * @author Adrian Kristanto
 * Modified by: Kok Tim Ming
 *
 */
public class RandomNumberGenerator {
    /**
     * Returns a random int from 0 to bound (exclusive)
     * @param bound range of random int
     * @return a random int from 0 to bound
     */
    public static int getRandomInt(int bound) {
        return bound > 0 ? new Random().nextInt(bound) : 0;
    }

    /**
     * Returns a random int from lower to upper bound (inclusive)
     * @param lowerBound inclusive lower bound
     * @param upperBound inclusive upper bound
     * @return an integer from lower to upper bound inclusive
     */
    public static int getRandomInt(int lowerBound, int upperBound) {
        int range = upperBound - lowerBound + 1;
        return new Random().nextInt(range) + lowerBound;
    }
}
