public class Main {
    public static void main(String[] args) {
        Application app = new Application("Render test", 640, 480);
        Renderer gl = app.getRenderer();

        Matrix mvp = new Matrix();
        mvp.setScale(0.25f, 0.25f, 0.25f);

        Model teapot = AssetLoader.loadOBJ("../models/teapot.obj");

        while(true) {
            app.beginFrame();

            gl.setMVP(mvp);

            teapot.render(gl);

            /*
            gl.begin(PrimitiveType.QUADS);
            gl.vertex(-0.5f,  0.5f, 0);
            gl.vertex(-0.5f, -0.5f, 0);
            gl.vertex( 0.5f, -0.5f, 0);
            gl.vertex( 0.5f,  0.5f, 0);
            gl.end();
            */

            app.endFrame();
        }
    }
}

