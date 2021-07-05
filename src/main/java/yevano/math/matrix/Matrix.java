package yevano.math.matrix;

import java.util.Arrays;

import lombok.NonNull;
import lombok.val;
import yevano.math.vector.NVector;
import yevano.math.vector.Vector;

public interface Matrix {
    public @NonNull double[] getComponents();
    public double getComponent(int i);
    public int rowCount();
    public int columnCount();

    public default double getComponent(int row, int column) {
        int m = rowCount();
        int n = columnCount();
        if(row >= m || column >= n) throw new IndexOutOfBoundsException();
        return getComponent(row * n + column);
    }

    /**
     * Given this m×n matrix A, get the n-vector represented by the ith row of A.
     * @param i
     * @return
     */
    public default Vector getRow(int i) {
        if(i < 0 || i >= rowCount()) throw new IllegalArgumentException("Invalid row.");
        val n = columnCount();
        val result = new double[n];

        for(int j = 0; j < n; j++) {
            result[j] = getComponent(i, j);
        }

        return NVector.variadic(result);
    }

    /**
     * Given this m×n matrix A, get the m-vector represented by the jth column of A.
     * @param j
     * @return
     */
    public default Vector getColumn(int j) {
        if(j < 0 || j >= columnCount()) throw new IllegalArgumentException("Invalid row.");
        val m = rowCount();
        val result = new double[m];

        for(int i = 0; i < m; i++) {
            result[i] = getComponent(i, j);
        }

        return NVector.variadic(result);
    }

    /**
     * Given this m×n matrix A, and an n×p matrix <code>rhs</code> B, get the m×p matrix product AB.
     * This operation is not well-defined if the number of columns in A is not equal to the number
     * of rows in B.
     * 
     * @param rhs
     * @return The matrix product AB.
     */
    public default Matrix mul(@NonNull Matrix rhs) {
        int m = rowCount();
        int n = columnCount();
        int p = rhs.columnCount();

        if(n != rhs.rowCount()) {
            throw new IllegalArgumentException("Matrix dimensions not compatible.");
        }

        double[] result = new double[m * p];

        for(int i = 0; i < m; i++) {
            for(int j = 0; j < p; j++) {
                Vector row = this.getRow(i);
                Vector column = rhs.getColumn(j);
                result[i * p + j] = row.dot(column);
            }
        }

        return MNMatrix.of(m, p, result);
    }

    /**
     * Multiplies this m×n matrix by an m-vector by assuming the given vector represents a column
     * vector s.t. the result of this method will be the same as multiplying this matrix by an m×1
     * matrix, the latter of which has entries corresponding to the components of the vector it
     * represents.
     * 
     * @param rhs
     * @return
     */
    public default Vector mul(@NonNull Vector rhs) {
        return mul(rhs.toColumnVector()).getColumn(0);
    }

    /**
     * Multiply this matrix by a scalar <code>n</code>.
     * @param n
     * @return The product of this matrix and <code>n</code>.
     */
    public default @NonNull Matrix mul(double n) {
        val cs = getComponents();
        val result = Arrays.copyOf(cs, cs.length);
        for(int i = 0; i < result.length; i++) result[i] *= n;
        return MNMatrix.of(rowCount(), columnCount(), result);
    }

    /**
     * Add this matrix with <code>rhs</code>.
     * @param rhs
     * @throws IllegalArgumentException if the matrices do not have the same dimensions.
     * @return The sum of the two matrices.
     */
    public default @NonNull Matrix add(@NonNull Matrix rhs) {
        int m = rowCount();
        int n = columnCount();

        if(m != rhs.rowCount() || n != rhs.columnCount()) {
            throw new IllegalArgumentException("Matrix dimensions not compatible.");
        }

        double[] result = getComponents();
        for(int i = 0; i < result.length; i++) result[i] += rhs.getComponent(i);
        return MNMatrix.of(m, n, result);
    }

    public default boolean equals(Matrix rhs) {
        val m = rowCount();
        val n = columnCount();
        if(m != rhs.rowCount() || n != rhs.columnCount()) return false;
        int mn = m * n;

        for(int i = 0; i < mn; i++) {
            if(getComponent(i) != rhs.getComponent(i)) return false;
        }

        return true;
    }
}
