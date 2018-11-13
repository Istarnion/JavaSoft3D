public class Vector4 {
    public float x, y, z, w;

    public Vector4() {
        x = 0;
        y = 0;
        z = 0;
        w = 0;
    }

    public Vector4(Vector3 v) {
        x = v.x;
        y = v.y;
        z = v.z;
        w = 0;
    }

    public Vector4(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = 0;
    }

    public Vector4(float x, float y, float z, float w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    public Vector4 inverted() {
        return new Vector4(-x, -y, -z, -w);
    }

    public static Vector4 add(Vector4 a, Vector4 b) {
        return new Vector4(a.x + b.x, a.y + b.y, a.z + b.z, a.w + b.w);
    }

    public static Vector4 minus(Vector4 a, Vector4 b) {
        return new Vector4(a.x - b.x, a.y - b.y, a.z - b.z, a.w - b.w);
    }

    public static float dot(Vector4 a, Vector4 b) {
        return a.x*b.x + a.y*b.y + a.z*b.z + a.w*b.w;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ", " + z + ", " + w + ")";
    }
}

