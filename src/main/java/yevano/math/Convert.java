package yevano.math;

public class Convert {
    public static double radToDeg(double t) {
        return t / Math.PI * 180.0;
    }

    public static double degToRad(double t) {
        return t / 180.0 * Math.PI;
    }
}
