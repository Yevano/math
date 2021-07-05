package yevano.math.transform;

import lombok.NonNull;
import yevano.math.rotation.Quaternion;
import yevano.math.vector.Vector3;

public interface TransformAdapter extends Transform {
    public Vector3 getPosition();
    public Quaternion getRotation();

    @Override
    public default Vector3 toWorld(@NonNull Vector3 localPosition) {
        return this.getPosition().add(getRotation().conjugate(localPosition));
    }

    @Override
    public default Vector3 toLocal(@NonNull Vector3 worldPosition) {
        return this.getRotation().inv().conjugate(getPosition().neg().add(worldPosition));
    }
}
