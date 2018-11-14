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

    public void setScale(float x, float y, float z) {
        f00 = x;
        f11 = y;
        f22 = z;
    }

    // Perform A*v
    public Vector3 transform(Vector3 v) {
        float x = f00 * v.x + f01 * v.y + f02 * v.z;
        float y = f10 * v.x + f11 * v.y + f12 * v.z;
        float z = f20 * v.x + f21 * v.y + f22 * v.z;
        return new Vector3(x, y, z);
    }

    public Vector4 transform(Vector4 v) {
        float x = f00 * v.x + f01 * v.y + f02 * v.z + f03 * v.w;
        float y = f10 * v.x + f11 * v.y + f12 * v.z + f13 * v.w;
        float z = f20 * v.x + f21 * v.y + f22 * v.z + f23 * v.w;
        float w = f30 * v.x + f31 * v.y + f32 * v.z + f33 * v.w;
        return new Vector4(x, y, z, w);
    }

    public static Matrix mul(Matrix a, Matrix b) {
        float f00 = a.f00*b.f00+a.f01*b.f10+a.f02*b.f20+a.f03*b.f30;
        float f01 = a.f10*b.f00+a.f11*b.f10+a.f12*b.f20+a.f13*b.f30;
        float f02 = a.f20*b.f00+a.f21*b.f10+a.f22*b.f20+a.f23*b.f30;
        float f03 = a.f30*b.f00+a.f31*b.f10+a.f32*b.f20+a.f33*b.f30;
        float f10 = a.f00*b.f01+a.f01*b.f11+a.f02*b.f21+a.f03*b.f31;
        float f11 = a.f10*b.f01+a.f11*b.f11+a.f12*b.f21+a.f13*b.f31;
        float f12 = a.f20*b.f01+a.f21*b.f11+a.f22*b.f21+a.f23*b.f31;
        float f13 = a.f30*b.f01+a.f31*b.f11+a.f32*b.f21+a.f33*b.f31;
        float f20 = a.f00*b.f02+a.f01*b.f12+a.f02*b.f22+a.f03*b.f32;
        float f21 = a.f10*b.f02+a.f11*b.f12+a.f12*b.f22+a.f13*b.f32;
        float f22 = a.f20*b.f02+a.f21*b.f12+a.f22*b.f22+a.f23*b.f32;
        float f23 = a.f30*b.f02+a.f31*b.f12+a.f32*b.f22+a.f33*b.f32;
        float f30 = a.f00*b.f03+a.f01*b.f13+a.f02*b.f23+a.f03*b.f33;
        float f31 = a.f10*b.f03+a.f11*b.f13+a.f12*b.f23+a.f13*b.f33;
        float f32 = a.f20*b.f03+a.f21*b.f13+a.f22*b.f23+a.f23*b.f33;
        float f33 = a.f30*b.f03+a.f31*b.f13+a.f32*b.f23+a.f33*b.f33;

        return new Matrix(f00, f01, f02, f03,
                          f10, f11, f12, f13,
                          f20, f21, f22, f23,
                          f30, f31, f32, f33);
    }

    public static Matrix mul(Matrix... matrices) {
        Matrix result = matrices[0];
        for(int i=1; i<matrices.length; ++i) {
            result = Matrix.mul(result, matrices[i]);
        }

        return result;
    }
}

