package yevano.math;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.ThreadLocalRandom;

import org.junit.Test;

import lombok.val;
import yevano.math.matrix.MNMatrix;
import yevano.math.matrix.Matrix;
import yevano.math.matrix.Matrix2;
import yevano.math.matrix.Matrix3;
import yevano.math.matrix.Matrix4;
import yevano.math.vector.Vector2;
import yevano.math.vector.Vector3;
import yevano.math.vector.Vector4;

public class MathTests {
    ThreadLocalRandom rnd = ThreadLocalRandom.current();

    double rand() {
        return rnd.nextDouble();
    }

    Vector2 rand2() {
        return Vector2.of(rand(), rand());
    }

    Vector3 rand3() {
        return Vector3.of(rand(), rand(), rand());
    }

    Vector4 rand4() {
        return Vector4.of(rand(), rand(), rand(), rand());
    }

    Matrix2 rand22() {
        return Matrix2.of(
            rand(), rand(),
            rand(), rand()
        );
    }

    Matrix3 rand33() {
        return Matrix3.of(
            rand(), rand(), rand(),
            rand(), rand(), rand(),
            rand(), rand(), rand()
        );
    }

    Matrix4 rand44() {
        return Matrix4.of(
            rand(), rand(), rand(), rand(),
            rand(), rand(), rand(), rand(),
            rand(), rand(), rand(), rand(),
            rand(), rand(), rand(), rand()
        );
    }

    @Test
    public void vectorComponents() {
        Vector2 a = rand2();
        assertEquals(a.x(), a.getComponent(0), 0.0);
        assertEquals(a.y(), a.getComponent(1), 0.0);

        Vector3 b = rand3();
        assertEquals(b.x(), b.getComponent(0), 0.0);
        assertEquals(b.y(), b.getComponent(1), 0.0);
        assertEquals(b.z(), b.getComponent(2), 0.0);
        
        Vector4 c = rand4();
        assertEquals(c.x(), c.getComponent(0), 0.0);
        assertEquals(c.y(), c.getComponent(1), 0.0);
        assertEquals(c.z(), c.getComponent(2), 0.0);
        assertEquals(c.w(), c.getComponent(3), 0.0);
    }

    @Test
    public void matrixComponents() {
        double m11 = rand();
        double m12 = rand();
        double m21 = rand();
        double m22 = rand();
        Matrix2 a = Matrix2.of(m11, m12, m21, m22);

        assertEquals(m11, a.getComponent(0), 0.0);
        assertEquals(m12, a.getComponent(1), 0.0);
        assertEquals(m21, a.getComponent(2), 0.0);
        assertEquals(m22, a.getComponent(3), 0.0);

        assertEquals(m11, a.getComponent(0, 0), 0.0);
        assertEquals(m12, a.getComponent(0, 1), 0.0);
        assertEquals(m21, a.getComponent(1, 0), 0.0);
        assertEquals(m22, a.getComponent(1, 1), 0.0);

        double x = rand();
        double y = rand();
        double z = rand();

        val columnVector3 = MNMatrix.of(3, 1,
            x,
            y,
            z
        );

        assertEquals(x, columnVector3.getComponent(0, 0), 0.0);
        assertEquals(y, columnVector3.getComponent(1, 0), 0.0);
        assertEquals(z, columnVector3.getComponent(2, 0), 0.0);
    }

    @Test
    public void rowVectors() {
        Vector2 a = rand2();
        Matrix expected = MNMatrix.of(2, 1,
            a.x(), a.y());
        assertEquals(expected, a.toColumnVector());
    }

    @Test
    public void identityMatrices() {
        Vector2 a2 = rand2();
        Vector2 b2 = Matrix2.IDENTITY.mul(a2);
        assertEquals(a2, b2);

        Vector3 a3 = rand3();
        Vector3 b3 = Matrix3.IDENTITY.mul(a3);
        assertEquals(a3, b3);

        Vector4 a4 = rand4();
        Vector4 b4 = Matrix4.IDENTITY.mul(a4);
        assertEquals(a4, b4);
    }

    @Test
    public void vectorOps() {
        //double  a1 = rand();
        Vector2 a2 = rand2();
        Vector3 a3 = rand3();
        Vector4 a4 = rand4();

        double  b1 = rand();
        Vector2 b2 = rand2();
        Vector3 b3 = rand3();
        Vector4 b4 = rand4();

        Vector2 add2 = a2.add(b2);
        Vector3 add3 = a3.add(b3);
        Vector4 add4 = a4.add(b4);

        Vector2 mul2 = a2.mul(b1);
        Vector3 mul3 = a3.mul(b1);
        Vector4 mul4 = a4.mul(b1);

        double dot2 = a2.dot(b2);
        double dot3 = a3.dot(b3);
        double dot4 = a4.dot(b4);

        Vector2 neg2 = a2.neg();
        Vector3 neg3 = a3.neg();
        Vector4 neg4 = a4.neg();

        assertEquals(add2, Vector2.of(a2.x() + b2.x(), a2.y() + b2.y()));
        assertEquals(mul2, Vector2.of(a2.x() * b1, a2.y() * b1));
        assertEquals(dot2, a2.x() * b2.x() + a2.y() * b2.y(), 0);
        assertEquals(neg2, Vector2.of(-a2.x(), -a2.y()));

        assertEquals(add3, Vector3.of(a3.x() + b3.x(), a3.y() + b3.y(), a3.z() + b3.z()));
        assertEquals(mul3, Vector3.of(a3.x() * b1, a3.y() * b1, a3.z() * b1));
        assertEquals(dot3, a3.x() * b3.x() + a3.y() * b3.y() + a3.z() * b3.z(), 0);
        assertEquals(neg3, Vector3.of(-a3.x(), -a3.y(), -a3.z()));

        assertEquals(add4, Vector4.of(a4.x() + b4.x(), a4.y() + b4.y(), a4.z() + b4.z(), a4.w() + b4.w()));
        assertEquals(mul4, Vector4.of(a4.x() * b1, a4.y() * b1, a4.z() * b1, a4.w() * b1));
        assertEquals(dot4, a4.x() * b4.x() + a4.y() * b4.y() + a4.z() * b4.z() + a4.w() * b4.w(), 0);
        assertEquals(neg4, Vector4.of(-a4.x(), -a4.y(), -a4.z(), -a4.w()));
    }
}
