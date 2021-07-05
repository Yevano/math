package yevano.math.matrix;

import java.util.Arrays;

import lombok.NonNull;
import lombok.val;

public class MNMatrix implements Matrix {
    public static MNMatrix of(int rowCount, int columnCount, double... components) {
        return new MNMatrix(rowCount, columnCount, components);
    }

    public static MNMatrix rows(double[]... rows) {
        int m = rows.length;
        if(m == 0) throw new IllegalArgumentException("Not enough arguments.");
        int n = rows[0].length;
        double[] result = Arrays.copyOf(rows[0], m * n);
        
        for(int i = 1; i < m; i++) {
            double[] row = rows[i];
            if(row.length != n) throw new IllegalArgumentException("Rows must be of equal length.");

            for(int j = 0; j < n; j++) {
                result[i * n + j] = row[j];
            }
        }

        return MNMatrix.of(m, n, result);
    }

    private final int rowCount;
    private final int columnCount;
    private final double[] components;

    public MNMatrix(int rowCount, int columnCount, @NonNull double... components) {
        if(components.length != rowCount * columnCount) throw new IllegalArgumentException("Not enough arguments.");
        this.rowCount = rowCount;
        this.columnCount = columnCount;
        this.components = Arrays.copyOf(components, components.length);
    }

    public MNMatrix(@NonNull Matrix matrix) {
        this.rowCount = matrix.rowCount();
        this.columnCount = matrix.columnCount();
        this.components = matrix.getComponents();
    }

    @Override
    public @NonNull double[] getComponents() {
        return Arrays.copyOf(components, components.length);
    }

    @Override
    public double getComponent(int i) {
        return components[i];
    }

    @Override
    public int rowCount() {
        return rowCount;
    }

    @Override
    public int columnCount() {
        return columnCount;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Matrix && equals((Matrix) obj);
    }

    @Override
    public String toString() {
        val sb = new StringBuilder();

        for(int i = 0; i < rowCount(); i++) {
            sb.append("[");
            sb.append(Double.toString(this.getComponent(i, 0)));

            for(int j = 1; j < columnCount(); j++) {
                sb.append(" ");
                sb.append(Double.toString(this.getComponent(i, j)));
            }

            sb.append("]");
        }

        return sb.toString();
    }
}
