package calculator;

import java.util.List;

public class HornerCalculator {
    public double calculate(double x, List<Double> polynomial) {
        double result = 0;
        for (double v : polynomial) {
            result = v + (x * result);
        }
        return result;
    }
}