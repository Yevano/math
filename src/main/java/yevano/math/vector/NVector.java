package yevano.math.vector;

import java.util.Arrays;

import lombok.NonNull;
import lombok.val;

public class NVector implements Vector {
    public static NVector variadic(double... components) {
        return new NVector(components);
    }

    private final double[] components;

    protected NVector(@NonNull double... components) {
        this.components = Arrays.copyOf(components, components.length);
    }

    protected NVector(@NonNull Vector vector) {
        this.components = vector.getComponents();
    }

    @Override
    public int dimensions() {
        return components.length;
    }

    @Override
    public double getComponent(int i) {
        return components[i];
    }

    @Override
    public double[] getComponents() {
        return Arrays.copyOf(components, components.length);
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Vector && equals((Vector) obj);
    }

    @Override
    public String toString() {
        val sb = new StringBuilder();

        sb.append("[");
        sb.append(Double.toString(this.components[0]));
        
        for(int i = 1; i < dimensions(); i++) {
            sb.append(", ");
            sb.append(Double.toString(this.components[i]));
        }

        sb.append("]");
        return sb.toString();
    }
}
