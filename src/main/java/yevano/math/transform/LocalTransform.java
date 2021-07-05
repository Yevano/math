package yevano.math.transform;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import yevano.math.rotation.EulerAngles;
import yevano.math.rotation.Quaternion;
import yevano.math.vector.Vector3;

public class LocalTransform extends WorldTransform {
    public static LocalTransform of(@NonNull Transform parent) {
        return LocalTransform.of(parent, Quaternion.ONE, Vector3.ZERO);
    }

    public static LocalTransform of(@NonNull Transform parent, @NonNull Quaternion rotation) {
        return LocalTransform.of(parent, rotation, Vector3.ZERO);
    }

    public static LocalTransform of(@NonNull Transform parent, @NonNull Vector3 position) {
        return LocalTransform.of(parent, Quaternion.ONE, position);
    }

    public static LocalTransform of(@NonNull Transform parent, @NonNull Quaternion rotation, @NonNull Vector3 position) {
        return new LocalTransform(parent, rotation, position);
    }

    public static LocalTransform of(@NonNull Transform parent, @NonNull EulerAngles angles, @NonNull Vector3 position) {
        Quaternion q = Quaternion.fromAxisAngle(angles.pitch(), Vector3.X_AXIS);
        q = Quaternion.fromAxisAngle(angles.yaw(), Vector3.Y_AXIS).mul(q);
        q = Quaternion.fromAxisAngle(angles.roll(), Vector3.Z_AXIS).mul(q);
        return LocalTransform.of(parent, q, position);
    }

    @Getter @Setter @NonNull private Transform parent; 

    /**
     * Create a transform whose local coordinate system is itself embedded in the parent's
     * coordinate system.
     * @param parent
     * @param rotation
     * @param position
     */
    public LocalTransform(@NonNull Transform parent, @NonNull Quaternion rotation, @NonNull Vector3 position) {
        super(rotation, position);
        this.parent = parent;
    }

    @Override
    public Vector3 toWorld(@NonNull Vector3 localPosition) {
        return parent.toWorld(super.toWorld(localPosition));
    }

    @Override
    public Vector3 toLocal(@NonNull Vector3 worldPosition) {
        return super.toLocal(parent.toLocal(worldPosition));
    }
}
