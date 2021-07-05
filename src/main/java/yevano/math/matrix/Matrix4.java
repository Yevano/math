package yevano.math.matrix;

import lombok.NonNull;
import yevano.math.vector.Vector4;

public class Matrix4 extends MNMatrix {
    public static final Matrix4 IDENTITY = Matrix4.of(
        1, 0, 0, 0,
        0, 1, 0, 0,
        0, 0, 1, 0,
        0, 0, 0, 1
    );

    public static Matrix4 of(
        double m11, double m12, double m13, double m14,
        double m21, double m22, double m23, double m24,
        double m31, double m32, double m33, double m34,
        double m41, double m42, double m43, double m44)
    {
        return new Matrix4(
            m11, m12, m13, m14,
            m21, m22, m23, m24,
            m31, m32, m33, m34,
            m41, m42, m43, m44
        );
    }

    public Matrix4(
        double m11, double m12, double m13, double m14,
        double m21, double m22, double m23, double m24,
        double m31, double m32, double m33, double m34,
        double m41, double m42, double m43, double m44)
    {
        super(4, 4,
            m11, m12, m13, m14,
            m21, m22, m23, m24,
            m31, m32, m33, m34,
            m41, m42, m43, m44
        );
    }

    public Matrix4(@NonNull Matrix matrix) {
        this(
            matrix.getComponent(0), matrix.getComponent(1), matrix.getComponent(2), matrix.getComponent(3),
            matrix.getComponent(4), matrix.getComponent(5), matrix.getComponent(6), matrix.getComponent(7),
            matrix.getComponent(8), matrix.getComponent(9), matrix.getComponent(10), matrix.getComponent(11),
            matrix.getComponent(12), matrix.getComponent(13), matrix.getComponent(14), matrix.getComponent(15)
        );

        if(matrix.rowCount() != 4 || matrix.columnCount() != 4) throw new IllegalArgumentException();
    }

    @Override
    public Vector4 getRow(int i) {
        return new Vector4(super.getRow(i));
    }

    @Override
    public Vector4 getColumn(int j) {
        return new Vector4(super.getColumn(j));
    }

    public Matrix4 mul(@NonNull Matrix4 rhs) {
        return new Matrix4(super.mul(rhs));
    }

    public Vector4 mul(@NonNull Vector4 rhs) {
        return new Vector4(super.mul(rhs));
    }

    public Matrix4 mul(double rhs) {
        return new Matrix4(super.mul(rhs));
    }

    public Matrix4 add(@NonNull Matrix4 rhs) {
        return new Matrix4(super.add(rhs));
    }
}
