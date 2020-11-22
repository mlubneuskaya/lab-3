package calculator;

import java.util.List;

public class PowCalculator {
    public double calculate(double x, List<Double> polynomial) {
        double result = 0;
        int power = polynomial.size() - 1;
        for (double coefficient : polynomial) {
            result += coefficient * Math.pow(x, power);
            power--;
        }
        return result;
    }
}
