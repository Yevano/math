package yevano.math.matrix;

import lombok.NonNull;
import yevano.math.vector.Vector2;

public class Matrix2 extends MNMatrix {
    public static final Matrix2 IDENTITY = Matrix2.of(
        1, 0,
        0, 1
    );

    public static Matrix2 of(double m11, double m12, double m21, double m22) {
        return new Matrix2(
            m11, m12,
            m21, m22
        );
    }

    
    public static Matrix2 rotation(double t) {
        return of(
            Math.cos(t), -Math.sin(t),
            Math.sin(t), Math.cos(t)
        );
    }

    public Matrix2(double m11, double m12, double m21, double m22) {
        super(2, 2,
            m11, m12,
            m21, m22
        );
    }

    public Matrix2(@NonNull Matrix matrix) {
        this(
            matrix.getComponent(0), matrix.getComponent(1),
            matrix.getComponent(2), matrix.getComponent(3)
        );

        if(matrix.rowCount() != 2 || matrix.columnCount() != 2) throw new IllegalArgumentException();
    }

    @Override
    public Vector2 getRow(int i) {
        return new Vector2(super.getRow(i));
    }

    @Override
    public Vector2 getColumn(int j) {
        return new Vector2(super.getColumn(j));
    }

    public Matrix2 mul(@NonNull Matrix2 rhs) {
        return new Matrix2(super.mul(rhs));
    }

    public Vector2 mul(@NonNull Vector2 rhs) {
        return new Vector2(super.mul(rhs));
    }

    public Matrix2 mul(double rhs) {
        return new Matrix2(super.mul(rhs));
    }

    public Matrix2 add(@NonNull Matrix2 rhs) {
        return new Matrix2(super.add(rhs));
    }
}
