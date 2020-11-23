package calculator;


public class PrimeNumbers {
    public static boolean isCloseToPrime(String value) {
        double doubleValue = Double.parseDouble(value);
        long round = Math.round(doubleValue);
        if (isPrime(round)) {
            return Math.abs(round - doubleValue) <= 0.1;
        }
        return false;
    }

    private static boolean isPrime(long value) {
        if (value == 0) {
            return false;
        }
        for (int i = 2; i <= value / 2; i++) {
            if ((value % i) == 0) {
                return false;
            }
        }
        return true;
    }

}
