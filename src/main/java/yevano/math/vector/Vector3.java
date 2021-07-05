package yevano.math.vector;

import lombok.NonNull;
import lombok.val;
import yevano.math.rotation.EulerAngles;

public class Vector3 extends NVector {
    public static final Vector3 ZERO = new Vector3(0, 0, 0);
    public static final Vector3 X_AXIS = new Vector3(1, 0, 0);
    public static final Vector3 Y_AXIS = new Vector3(0, 1, 0);
    public static final Vector3 Z_AXIS = new Vector3(0, 0, 1);

    public static Vector3 of(double x, double y, double z) {
        return new Vector3(x, y, z);
    }

    public Vector3(@NonNull Vector rhs) {
        super(rhs);
        val message = String.format("Expected a 3-vector, but got a %s-vector.", rhs.dimensions());
        if(rhs.dimensions() != 3) throw new IllegalArgumentException(message);
    }

    public Vector3(double x, double y, double z) {
        super(x, y, z);
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

    @Override
    public Vector3 add(@NonNull Vector rhs) {
        return new Vector3(super.add(rhs));
    }

    @Override
    public Vector3 mul(double rhs) {
        return new Vector3(super.mul(rhs));
    }

    @Override
    public Vector3 neg() {
        return new Vector3(super.neg());
    }

    @Override
    public Vector3 normalized() {
        return new Vector3(super.normalized());
    }

    public Vector3 cross(@NonNull Vector3 rhs) {
        return Vector3.of(
            y() * rhs.z() - z() * rhs.y(),
            z() * rhs.x() - x() * rhs.z(),
            x() * rhs.y() - y() * rhs.x());
    }

    public EulerAngles toAzimuthElevation() {
        double pitch, yaw;
        pitch = Math.atan2(y(), -z());
        yaw = Math.atan2(z(), x());
        if(yaw >= 0) pitch -= Math.PI;
        return EulerAngles.of(pitch, yaw, 0);
    }
}
