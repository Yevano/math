package yevano.math.matrix;

import lombok.NonNull;
import lombok.val;
import yevano.math.vector.Vector3;

public class Matrix3 extends MNMatrix {
    public static final Matrix3 IDENTITY = Matrix3.of(
        1, 0, 0,
        0, 1, 0,
        0, 0, 1
    );

    public static Matrix3 of(
        double m11, double m12, double m13,
        double m21, double m22, double m23,
        double m31, double m32, double m33)
    {
        return new Matrix3(
            m11, m12, m13,
            m21, m22, m23,
            m31, m32, m33
        );
    }

    public static Matrix3 rotation(double t, @NonNull Vector3 axis) {
        val normAxis = axis.normalized();
        double l = normAxis.x();
        double m = normAxis.y();
        double n = normAxis.z();

        double ct = Math.cos(t);
        double st = Math.sin(t);
        double lst = l*st;
        double mst = m*st;
        double nst = n*st;
        double omct = 1 - ct;
        double ll = l*l;
        double mm = m*m;
        double nn = n*n;
        double ml = m*l;
        double nl = n*l;
        double nm = n*m;

        return Matrix3.of(
            ll * omct +  ct, ml * omct - nst, nl * omct + mst,
            ml * omct + nst, mm * omct +  ct, nm * omct - lst,
            nl * omct - mst, nm * omct + lst, nn * omct +  ct
        );
    }

    public static Matrix3 rotateX(double t) {
        double cos = Math.cos(t);
        double sin = Math.sin(t);

        return Matrix3.of(
            1.0, 0.0, 0.0,
            0.0, cos, -sin,
            0.0, sin, cos
        );
    }

    public static Matrix3 rotateY(double t) {
        double cos = Math.cos(t);
        double sin = Math.sin(t);

        return Matrix3.of(
            cos, 0.0, sin,
            0.0, 1.0, 0.0,
            -sin, 0.0, cos
        );
    }

    public static Matrix3 rotateZ(double t) {
        double cos = Math.cos(t);
        double sin = Math.sin(t);

        return Matrix3.of(
            cos, -sin, 0.0,
            sin, cos, 0.0,
            0.0, 0.0, 1.0
        );
    }

    public Matrix3(
        double m11, double m12, double m13,
        double m21, double m22, double m23,
        double m31, double m32, double m33)
    {
        super(3, 3,
            m11, m12, m13,
            m21, m22, m23,
            m31, m32, m33
        );
    }

    public Matrix3(@NonNull Matrix matrix) {
        this(
            matrix.getComponent(0), matrix.getComponent(1), matrix.getComponent(2),
            matrix.getComponent(3), matrix.getComponent(4), matrix.getComponent(5),
            matrix.getComponent(6), matrix.getComponent(7), matrix.getComponent(8)
        );

        if(matrix.rowCount() != 3 || matrix.columnCount() != 3) throw new IllegalArgumentException();
    }

    @Override
    public Vector3 getRow(int i) {
        return new Vector3(super.getRow(i));
    }

    @Override
    public Vector3 getColumn(int j) {
        return new Vector3(super.getColumn(j));
    }

    public Matrix3 mul(@NonNull Matrix3 rhs) {
        return new Matrix3(super.mul(rhs));
    }

    public Vector3 mul(@NonNull Vector3 rhs) {
        return new Vector3(super.mul(rhs));
    }

    public Matrix3 mul(double rhs) {
        return new Matrix3(super.mul(rhs));
    }

    public Matrix3 add(@NonNull Matrix3 rhs) {
        return new Matrix3(super.add(rhs));
    }
}
