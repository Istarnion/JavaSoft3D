import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Rectangle;
import java.util.ArrayList;

public class Renderer {
    public boolean backFaceCulling = true;

    private PrimitiveType primitive;
    private ArrayList<Vector4> vertices;
    private ArrayList<Triangle> tris = new ArrayList<Triangle>();

    private Matrix mvp;

    private float targetWidth, targetHeight;

    public Renderer(float width, float height) {
        targetWidth = width;
        targetHeight = height;

        mvp = new Matrix();
    }

    public void render(Graphics2D g) {
        g.setColor(Color.WHITE);

        float halfW = targetWidth / 2.0f;
        float halfH = targetHeight / 2.0f;

        // Make origo be in the center of the render target
        g.translate(halfW, halfH);

        for(Triangle tri : tris) {
            g.drawLine((int)( tri.a.x * halfW),
                       (int)(-tri.a.y * halfH),
                       (int)( tri.b.x * halfW),
                       (int)(-tri.b.y * halfH));
            g.drawLine((int)( tri.b.x * halfW),
                       (int)(-tri.b.y * halfH),
                       (int)( tri.c.x * halfW),
                       (int)(-tri.c.y * halfH));
            g.drawLine((int)( tri.c.x * halfW),
                       (int)(-tri.c.y * halfH),
                       (int)( tri.a.x * halfW),
                       (int)(-tri.a.y * halfH));
        }

        tris.clear();

        // Reset the translation
        g.translate(-halfW, -halfH);
    }

    public void resize(float newWidth, float newHeight) {
        targetWidth = newWidth;
        targetHeight = newHeight;
    }

    public Matrix getMVP() {
        return mvp;
    }

    public void setMVP(Matrix newMVP) {
        if(newMVP == null) {
            throw new IllegalArgumentException("The MVP matrix can not be null");
        }

        mvp = newMVP;
    }

    public void begin(PrimitiveType primitive) {
        this.primitive = primitive;
        vertices = new ArrayList<Vector4>();
    }

    public void end() {
        if(vertices == null) {
            throw new RuntimeException("Renderer: begin() must be called before end()");
        }

        switch(primitive) {
            case TRIANGLES:
                for(int i=0; i<vertices.size()/3; ++i) {
                    Vector4 a, b, c;
                    a = mvp.transform(vertices.get(3*i+0));
                    b = mvp.transform(vertices.get(3*i+1));
                    c = mvp.transform(vertices.get(3*i+2));
                    Triangle tri = new Triangle(a, b, c);

                    if(!backFaceCulling || tri.isCCW()) {
                        tris.add(tri);
                    }
                }
                break;
            case QUADS:
                for(int i=0; i<vertices.size()/4; ++i) {
                    Vector4 a, b, c;
                    a = mvp.transform(vertices.get(4*i+0));
                    b = mvp.transform(vertices.get(4*i+1));
                    c = mvp.transform(vertices.get(4*i+2));
                    Triangle tri1 = new Triangle(a, b, c);
                    a = mvp.transform(vertices.get(4*i+0));
                    b = mvp.transform(vertices.get(4*i+2));
                    c = mvp.transform(vertices.get(4*i+3));
                    Triangle tri2 = new Triangle(a, b, c);

                    if(!backFaceCulling || (tri1.isCCW() && tri2.isCCW())) {
                        tris.add(tri1);
                        tris.add(tri2);
                    }
                }
                break;
            default: break;
        }

        vertices = null;
    }

    public void vertex(float x, float y, float z) {
        vertices.add(new Vector4(x, y, z, 1));
    }

    public void vertex(Vector3 v) {
        vertices.add(new Vector4(v.x, v.y, v.z, 1));
    }
}

