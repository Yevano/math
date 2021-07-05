package yevano.math.vector;

import lombok.NonNull;
import lombok.val;
import yevano.math.matrix.MNMatrix;
import yevano.math.matrix.Matrix;

public interface Vector {
    public int dimensions();
    public @NonNull double[] getComponents();
    public double getComponent(int i);

    public default Matrix toColumnVector() {
        return MNMatrix.of(dimensions(), 1, getComponents());
    }

    public default Matrix toRowVector() {
        return MNMatrix.of(1, dimensions(), getComponents());
    }

    /**
     * Given two n-vectors A, B represented by <code>this</code> and <code>rhs</code>, get the dot
     * product A·B.
     * @param rhs
     * @return A·B
     */
    public default double dot(@NonNull Vector rhs) {
        if(this.dimensions() != rhs.dimensions()) {
            throw new IllegalArgumentException("Vector dimensions not compatible.");
        }

        double sum = 0;

        for(int i = 0; i < dimensions(); i++) {
            sum += getComponent(i) * rhs.getComponent(i);
        }

        return sum;
    }

    public default @NonNull Vector add(@NonNull Vector rhs) {
        if(this.dimensions() != rhs.dimensions()) {
            throw new IllegalArgumentException("Vector dimensions not compatible.");
        }

        val result = new double[rhs.dimensions()];

        for(int i = 0; i < result.length; i++) {
            result[i] = getComponent(i) + rhs.getComponent(i);
        }

        return NVector.variadic(result);
    }

    public default @NonNull Vector neg() {
        val result = new double[dimensions()];
        for(int i = 0; i < result.length; i++) result[i] = -getComponent(i);
        return NVector.variadic(result);
    }

    public default @NonNull Vector mul(double n) {
        val result = new double[dimensions()];

        for(int i = 0; i < result.length; i++) {
            result[i] = getComponent(i) * n;
        }

        return NVector.variadic(result);
    }

    public default double length2() {
        int dim = dimensions();
        double sum = 0;

        for(int i = 0; i < dim; i++) {
            double x = getComponent(i);
            sum += x * x;
        }

        return sum;
    }

    public default double length() {
        return Math.sqrt(length2());
    }

    public default boolean equals(Vector rhs) {
        if(dimensions() != rhs.dimensions()) return false;
        for(int i = 0; i < dimensions(); i++) {
            if(getComponent(i) != rhs.getComponent(i)) return false;
        }
        return true;
    }

    public default Vector normalized() {
        double len = length();
        if(len == 0.0) throw new RuntimeException("Normalization on vectors with length zero is undefined.");
        return mul(1.0 / len);
    }
}
