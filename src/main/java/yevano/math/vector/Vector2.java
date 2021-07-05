package yevano.math.vector;

import lombok.NonNull;
import lombok.val;

public class Vector2 extends NVector {
    public static final Vector2 ZERO = new Vector2(0, 0);
    public static final Vector2 X_AXIS = new Vector2(1, 0);
    public static final Vector2 Y_AXIS = new Vector2(0, 1);

    public static Vector2 of(double x, double y) {
        return new Vector2(x, y);
    }

    public Vector2(double x, double y) {
        super(x, y);
    }

    public Vector2(@NonNull Vector rhs) {
        super(rhs);
        val message = String.format("Expected a 2-vector, but got a %s-vector.", rhs.dimensions());
        if(rhs.dimensions() != 2) throw new IllegalArgumentException(message);
    }

    public double x() {
        return getComponent(0);
    }

    public double y() {
        return getComponent(1);
    }

    @Override
    public Vector2 add(@NonNull Vector rhs) {
        return new Vector2(super.add(rhs));
    }

    @Override
    public Vector2 mul(double rhs) {
        return new Vector2(super.mul(rhs));
    }

    @Override
    public Vector2 neg() {
        return new Vector2(super.neg());
    }

    @Override
    public Vector2 normalized() {
        return new Vector2(super.normalized());
    }
}