public class Matrix {
    public float f00, f01, f02, f03;
    public float f10, f11, f12, f13;
    public float f20, f21, f22, f23;
    public float f30, f31, f32, f33;

    // Identity matrix
    public Matrix() {
        f00 = f11 = f22 = f33 = 1.0f;
    }

    public Matrix(float f00, float f01, float f02, float f03,
                  float f10, float f11, float f12, float f13,
                  float f20, float f21, float f22, float f23,
                  float f30, float f31, float f32, float f33) {
        this.f00 = f00; this.f01 = f01; this.f02 = f02; this.f03 = f03;
        this.f10 = f10; this.f11 = f11; this.f12 = f12; this.f13 = f13;
        this.f20 = f20; this.f21 = f21; this.f22 = f22; this.f23 = f23;
        this.f30 = f30; this.f31 = f31; this.f32 = f32; this.f33 = f33;
    }

    public void makeIdentity() {
        f01 = f02 = f03 = 0.0f;
        f10 = f12 = f13 = 0.0f;
        f20 = f21 = f23 = 0.0f;
        f30 = f31 = f32 = 0.0f;
        f00 = f11 = f22 = f33 = 1.0f;
    }

    // Perform A*v
    public Vector mul(Vector v) {
        float x = f00 * v.x + f01 * v.y + f02 * v.z;
        float y = f10 * v.x + f11 * v.y + f12 * v.z;
        float z = f20 * v.x + f21 * v.y + f22 * v.z;
        return new Vector(x, y, z);
    }

    public Vector4 mul(Vector4 v) {
        float x = f00 * v.x + f01 * v.y + f02 * v.z + f03 * v.w;
        float y = f10 * v.x + f11 * v.y + f12 * v.z + f13 * v.w;
        float z = f20 * v.x + f21 * v.y + f22 * v.z + f23 * v.w;
        float w = f30 * v.x + f31 * v.y + f32 * v.z + f33 * v.w;
        return new Vector4(x, y, z, w);
    }
}

