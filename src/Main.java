public class Main {
    public static void main(String[] args) {
        Application app = new Application("Render test", 640, 480);
        Renderer gl = app.getRenderer();

        Matrix projection = Matrix.ortho(-320, 320, -240, 240, 1, 100);
        Matrix model = new Matrix();
        model.setScale(64, 64, 64);

        Model teapot = AssetLoader.loadOBJ("../models/teapot.obj");

        while(true) {
            app.beginFrame();

            Matrix mp = Matrix.mul(projection, model);
            gl.setMVP(mp);

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

