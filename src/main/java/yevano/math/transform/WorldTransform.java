package yevano.math.transform;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import yevano.math.rotation.EulerAngles;
import yevano.math.rotation.Quaternion;
import yevano.math.vector.Vector3;

public class WorldTransform implements Transform {
    public static final WorldTransform IDENTITY = WorldTransform.of(Quaternion.ONE, Vector3.ZERO);

    public static WorldTransform of(@NonNull Quaternion rotation) {
        return WorldTransform.of(rotation, Vector3.ZERO);
    }

    public static WorldTransform of(@NonNull Vector3 position) {
        return WorldTransform.of(Quaternion.ONE, position);
    }

    public static WorldTransform of(@NonNull Quaternion rotation, @NonNull Vector3 position) {
        return new WorldTransform(rotation, position);
    }

    public static WorldTransform of(@NonNull EulerAngles angles, @NonNull Vector3 position) {
        Quaternion q = Quaternion.fromAxisAngle(angles.pitch(), Vector3.X_AXIS);
        q = Quaternion.fromAxisAngle(angles.yaw(), Vector3.Y_AXIS).mul(q);
        q = Quaternion.fromAxisAngle(angles.roll(), Vector3.Z_AXIS).mul(q);
        return WorldTransform.of(q, position);
    }

    @Getter @Setter private Quaternion rotation;
    @Getter @Setter private Vector3 position;

    public WorldTransform(@NonNull Quaternion rotation, @NonNull Vector3 position) {
        this.rotation = rotation;
        this.position = position;
    }

    @Override
    public Vector3 toWorld(@NonNull Vector3 localPosition) {
        return this.position.add(rotation.conjugate(localPosition));
    }

    @Override
    public Vector3 toLocal(@NonNull Vector3 worldPosition) {
        return this.rotation.inv().conjugate(position.neg().add(worldPosition));
    }
}
