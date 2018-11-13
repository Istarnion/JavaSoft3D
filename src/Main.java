public class Main {
    public static void main(String[] args) {
        Application app = new Application("Render test", 640, 480);
        Renderer gl = app.getRenderer();

        while(true) {
            app.beginFrame();

            gl.begin(PrimitiveType.QUADS);
            gl.vertex(-0.5f,  0.5f, 0);
            gl.vertex(-0.5f, -0.5f, 0);
            gl.vertex( 0.5f, -0.5f, 0);
            gl.vertex( 0.5f,  0.5f, 0);
            gl.end();

            app.endFrame();
        }
    }
}

