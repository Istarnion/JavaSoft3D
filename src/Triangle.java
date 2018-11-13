public class Triangle {
    public Vector4 a, b, c;

    public Triangle(Vector a, Vector b, Vector c) {
        this.a = new Vector4(a);
        this.b = new Vector4(b);
        this.c = new Vector4(c);
    }

    public Triangle(Vector4 a, Vector4 b, Vector4 c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public boolean isCCW() {
        return (b.x - a.x) * (c.y - a.y) - (b.y - a.y) * (c.x - a.x) >= 0;
    }

    @Override
    public String toString() {
        return "(" + a + ", " + b + ", " + c + ")";
    }
}

