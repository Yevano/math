package yevano.math.rotation;

import lombok.val;
import yevano.math.Convert;
import yevano.math.matrix.Matrix3;
import yevano.math.vector.Vector3;

public class EulerAngles {
    public static final EulerAngles ZERO = EulerAngles.of(0, 0, 0);

    public static EulerAngles of(double pitch, double yaw, double roll) {
        return new EulerAngles(pitch, yaw, roll);
    }

    public static EulerAngles fromDeg(double pitch, double yaw, double roll) {
        return EulerAngles.of(
            Convert.degToRad(pitch),
            Convert.degToRad(yaw),
            Convert.degToRad(roll)
        );
    }

    private final double pitch;
    private final double yaw;
    private final double roll;

    public EulerAngles(double pitch, double yaw, double roll) {
        this.pitch = pitch;
        this.yaw = yaw;
        this.roll = roll;
    }

    public double pitch() {
        return pitch;
    }

    public double yaw() {
        return yaw;
    }

    public double roll() {
        return roll;
    }

    public Matrix3 transform() {
        return Matrix3.rotateX(pitch).mul(Matrix3.rotateY(yaw)).mul(Matrix3.rotateZ(roll));
    }

    public Quaternion toRotationQuaternion() {
        Quaternion pitchRotation = Quaternion.fromAxisAngle(pitch, Vector3.X_AXIS);
        Quaternion yawRotation   = Quaternion.fromAxisAngle(yaw, Vector3.Y_AXIS);
        Quaternion rollRotation  = Quaternion.fromAxisAngle(roll, Vector3.Z_AXIS);
        return rollRotation.mul(yawRotation).mul(pitchRotation);
    }

    public Vector3 toDirectionVector() {
        return toRotationQuaternion().conjugate(Vector3.Z_AXIS);
    }

    public Matrix3 toRotationMatrix() {
        Matrix3 pitchRotation = Matrix3.rotateX(pitch);
        Matrix3 yawRotation   = Matrix3.rotateY(yaw);
        Matrix3 rollRotation  = Matrix3.rotateZ(roll);
        return rollRotation.mul(yawRotation).mul(pitchRotation);
    }

    @Override
    public String toString() {
        return String.format("(%s, %s, %s)", pitch, yaw, roll);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof EulerAngles) {
            val rhs = (EulerAngles) obj;
            return pitch == rhs.pitch && yaw == rhs.yaw && roll == rhs.roll;
        }

        return false;
    }
}
