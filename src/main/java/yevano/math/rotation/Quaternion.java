package yevano.math.rotation;

import lombok.NonNull;
import lombok.val;
import yevano.math.matrix.Matrix3;
import yevano.math.vector.Vector3;

/**
 * Represents a quaternion, a number composed of one real part and multiples of the 3 basic
 * quaternions 𝒊, 𝒋, 𝒌.
 */
public class Quaternion {
    public static final Quaternion ZERO = Quaternion.of(0, 0, 0, 0);

    /** The real number 1. */
    public static final Quaternion ONE  = Quaternion.of(1, 0, 0, 0);
    /** The basic quaternion 𝒊. */
    public static final Quaternion I    = Quaternion.of(0, 1, 0, 0);
    /** The basic quaternion 𝒋. */
    public static final Quaternion J    = Quaternion.of(0, 0, 1, 0);
    /** The basic quaternion 𝒌. */
    public static final Quaternion K    = Quaternion.of(0, 0, 0, 1);

    /**
     * This static constructor creates a {@link Quaternion} by applying each component explicitly.
     * @param a The real part 𝑎
     * @param b The imaginary coefficient 𝑏
     * @param c The imaginary coefficient 𝑐
     * @param d The imaginary coefficient 𝑑
     * @return The quaternion 𝑎 + 𝑏𝒊 + 𝑐𝒋 + 𝑑𝒌.
     */
    public static Quaternion of(double a, double b, double c, double d) {
        return new Quaternion(a, b, c, d);
    }

    /**
     * This static constructor creates a {@link Quaternion} by supplying a real part and vector
     * part, the latter of which is represented as a {@link Vector3}.
     * @param r Real part 𝑟
     * @param v Vector part 𝑣
     * @return The quaternion with real part 𝑟 and vector part 𝑣.
     */
    public static Quaternion of(double r, @NonNull Vector3 v) {
        return new Quaternion(r, v.x(), v.y(), v.z());
    }

    /**
     * This static constructor creates a vector {@link Quaternion} (with real part 0), by supplying
     * the vector part as a {@link Vector3}.
     * @param v Vector 𝑣
     * @return The quaternion with real part 0 and vector part 𝑣.
     */
    public static Quaternion of(@NonNull Vector3 v) {
        return Quaternion.of(0, v);
    }

    /**
     * @param t Angle θ
     * @param axis Vector 𝑣
     * @return The quaternion which encodes rotation with angle θ about the axis 𝑣.
     */
    public static Quaternion fromAxisAngle(double t, @NonNull Vector3 axis) {
        axis = axis.normalized();
        double ht = t / 2;
        return Quaternion.of(Math.cos(ht), axis.mul(Math.sin(ht)));
    }

    private final double a;
    private final double b;
    private final double c;
    private final double d;

    /**
     * Constructs the quaternion 𝑎 + 𝑏𝒊 + 𝑐𝒋 + 𝑑𝒌.
     * @param a Real number 𝑎
     * @param b Real number 𝑏
     * @param c Real number 𝑐
     * @param d Real number 𝑑
     */
    public Quaternion(double a, double b, double c, double d) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }

    /**
     * Add this quaternion with another.
     * @param rhs A quaternion 𝒒
     * @return The sum 𝒑 + 𝒒, where 𝒑 = {@code this}.
     */
    public Quaternion add(@NonNull Quaternion rhs) {
        double a1 = this.a;
        double b1 = this.b;
        double c1 = this.c;
        double d1 = this.d;
        double a2 = rhs.a();
        double b2 = rhs.b();
        double c2 = rhs.c();
        double d2 = rhs.d();

        return Quaternion.of(a1 + a2, b1 + b2, c1 + c2, d1 + d2);
    }

    /**
     * Adds this quaternion with a real number.
     * @param rhs A real number 𝑎
     * @return The sum 𝒑 + 𝑎, where 𝒑 = {@code this}.
     */
    public Quaternion add(double rhs) {
        return Quaternion.of(a + rhs, b, c, d);
    }

    /**
     * Multiplies this quaternion with a scalar.
     * @param rhs A real number 𝑎
     * @return The product 𝒑𝑎, where 𝒑 = {@code this}.
     */
    public Quaternion mul(double rhs) {
        return Quaternion.of(a * rhs, b * rhs, c * rhs, d * rhs);
    }

    /**
     * Divide this quaternion by a scalar.
     * @param rhs A real number 𝑎
     * @return The quotient 𝒑 / 𝑎, where 𝒑 = {@code this}.
     */
    public Quaternion div(double rhs) {
        return Quaternion.of(a / rhs, b / rhs, c / rhs, d / rhs);
    }

    /**
     * Multiplies this quaternion with another.
     * @param rhs A quaternion 𝒒
     * @return The Hamilton product 𝒑𝒒, where 𝒑 = {@code this}.
     */
    public Quaternion mul(@NonNull Quaternion rhs) {
        double a1 = this.a;
        double b1 = this.b;
        double c1 = this.c;
        double d1 = this.d;
        double a2 = rhs.a();
        double b2 = rhs.b();
        double c2 = rhs.c();
        double d2 = rhs.d();

        double a3 = a1 * a2 - b1 * b2 - c1 * c2 - d1 * d2;
        double b3 = a1 * b2 + b1 * a2 + c1 * d2 - d1 * c2;
        double c3 = a1 * c2 - b1 * d2 + c1 * a2 + d1 * b2;
        double d3 = a1 * d2 + b1 * c2 - c1 * b2 + d1 * a2;

        return Quaternion.of(a3, b3, c3, d3);
    }

    /**
     * Get the conjugate of this quaternion 𝒑.
     * @return The conjugate 𝒑*.
     */
    public Quaternion conj() {
        return Quaternion.of(a, -b, -c, -d);
    }

    /**
     * Conjugates a quaternion by this quaternion.
     * @param q A quaternion 𝒒
     * @return The conjugation of 𝒑 by 𝒒, where 𝒑 = {@code this}.
     */
    public Quaternion conjugate(@NonNull Quaternion q) {
        val p = this;
        return p.mul(q).mul(p.inv());
    }

    /**
     * Conjugates a {@link Vector3} by this quaternion, treating the vector as a vector quaternion.
     * @param v A 3-vector 𝑣
     * @return The conjugation of 𝒑 by 𝑣, where 𝒑 = {@code this}.
     */
    public Vector3 conjugate(@NonNull Vector3 v) {
        val p = Quaternion.of(v);
        val q = this;
        return q.conjugate(p).vector();
    }

    public double normSquared() {
        return a * a + b * b + c * c + d * d;
    }

    public double norm() {
        return Math.sqrt(normSquared());
    }

    public Quaternion unit() {
        return this.div(norm());
    }

    /**
     * Get the inverse of this quaternion 𝒑.
     * @return The inverse of 𝒑 with respect to the Hamilton product, 𝒑⁻¹.
     */
    public Quaternion inv() {
        return this.conj().div(normSquared());
    }

    /**
     * Extracts the real part of {@code this} quaternion.
     * @return The real component.
     */
    public double a() {
        return this.a;
    }

    /**
     * Extracts the coefficient part of {@code this} quaternion.
     * @return The real component.
     */
    public double b() {
        return this.b;
    }

    /**
     * Extracts the real part of {@code this} quaternion.
     * @return The real component.
     */
    public double c() {
        return this.c;
    }

    /**
     * Extracts the real part of {@code this} quaternion.
     * @return The real component.
     */
    public double d() {
        return this.d;
    }

    /**
     * Get the vector part of <code>this</code> quaternion. The vector part is simply (𝑏, 𝑐, 𝑑) for
     * any quaternion 𝑎 + 𝑏𝒊 + 𝑐𝒋 + 𝑑𝒌
     * @return the vector part of <code>this</code> quaternion
     */
    public Vector3 vector() {
        return Vector3.of(b, c, d);
    }

    public Vector3 left() {
        return this.conjugate(Vector3.X_AXIS);
    }

    public Vector3 up() {
        return this.conjugate(Vector3.Y_AXIS);
    }
    
    public Vector3 forward() {
        return this.conjugate(Vector3.Z_AXIS);
    }

    public EulerAngles toEulerAngles() {
        return forward().toAzimuthElevation();
    }

    public Matrix3 toRotationMatrix() {
        return toEulerAngles().toRotationMatrix();
    }

    @Override
    public boolean equals(Object obj) {
        if(!Quaternion.class.isInstance(obj)) return false;
        val q = (Quaternion) obj;
        return a == q.a && b == q.b && c == q.c && d == q.d;
    }

    @Override
    public String toString() {
        return String.format("%s + %si + %sj + %sk", a, b, c, d);
    }
}
