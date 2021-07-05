package yevano.math.vector;

import lombok.NonNull;
import lombok.val;

public class Vector4 extends NVector {
    public static final Vector4 ZERO = new Vector4(0, 0, 0, 0);
    public static final Vector4 X_AXIS = new Vector4(1, 0, 0, 0);
    public static final Vector4 Y_AXIS = new Vector4(0, 1, 0, 0);
    public static final Vector4 Z_AXIS = new Vector4(0, 0, 1, 0);
    public static final Vector4 W_AXIS = new Vector4(0, 0, 0, 1);

    public static Vector4 of(double x, double y, double z, double w) {
        return new Vector4(x, y, z, w);
    }

    public Vector4(double x, double y, double z, double w) {
        super(x, y, z, w);
    }

    public Vector4(@NonNull Vector rhs) {
        super(rhs);
        val message = String.format("Expected a 4-vector, but got a %s-vector.", rhs.dimensions());
        if(rhs.dimensions() != 4) throw new IllegalArgumentException(message);
    }

    public double x() {
        return getComponent(0);
    }

    public double y() {
        return getComponent(1);
    }

    public double z() {
        return getComponent(2);
    }

    public double w() {
        return getComponent(3);
    }

    @Override
    public Vector4 add(@NonNull Vector rhs) {
        return new Vector4(super.add(rhs));
    }

    @Override
    public Vector4 mul(double rhs) {
        return new Vector4(super.mul(rhs));
    }

    @Override
    public Vector4 neg() {
        return new Vector4(super.neg());
    }

    @Override
    public Vector4 normalized() {
        return new Vector4(super.normalized());
    }
}
