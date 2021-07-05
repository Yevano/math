package yevano.math.transform;

import lombok.NonNull;
import yevano.math.vector.Vector3;

public interface Transform {
    /**
     * Converts coordinates relative to this transform to world coordinates.
     * @param localPosition
     * @return
     */
    public Vector3 toWorld(@NonNull Vector3 localPosition);

    /**
     * Converts world coordinates to this transform's local coordinate system.
     * @param worldPosition
     * @return
     */
    public Vector3 toLocal(@NonNull Vector3 worldPosition);
}
