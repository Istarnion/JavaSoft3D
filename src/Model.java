public class Model {
    private float[] vertices;
    private int[] indices;

    public Model(float[] vertices, int[] indices) {
        this.vertices = vertices;
        this.indices = indices;
    }

    public void render(Renderer gl) {
        gl.begin(PrimitiveType.TRIANGLES);

        for(int i=0; i<indices.length/3; ++i) {
            int i0 = indices[3*i+0]-1;
            int i1 = indices[3*i+1]-1;
            int i2 = indices[3*i+2]-1;

            gl.vertex(vertices[3*i0], vertices[3*i0+1], vertices[3*i0+2]);
            gl.vertex(vertices[3*i1], vertices[3*i1+1], vertices[3*i1+2]);
            gl.vertex(vertices[3*i2], vertices[3*i2+1], vertices[3*i2+2]);
        }

        gl.end();
    }
}

