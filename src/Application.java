import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

public class Application {
    private final int WIDTH;
    private final int HEIGHT;

    private static Canvas canvas;
    private static Renderer gl;

    private final static long targetFrameTimeMillis = 16;
    private long frameStartTime;

    public Application(String title, int width, int height) {
        WIDTH = width;
        HEIGHT = height;

        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        canvas.setFocusable(true);

        JFrame window = new JFrame(title);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.add(canvas);
        window.pack(); // Packs the frame around the canvas
        window.setResizable(false);
        window.setLocationRelativeTo(null); // Centers the window
        window.setVisible(true);

        gl = new Renderer(WIDTH, HEIGHT);
    }

    public void beginFrame() {
        frameStartTime = System.nanoTime();
    }

    public void endFrame() {
        render(); // Render everything to the screen

        long timeSpent = (System.nanoTime() - frameStartTime) / 1000000L;
        long timeToSleep = targetFrameTimeMillis - timeSpent;
        if(timeSpent > 1) {
            try {
                Thread.sleep(16);
            }
            catch(InterruptedException e) {
                // Ignore
            }
        }
    }

    private void render() {
        BufferStrategy bs = canvas.getBufferStrategy();
        if(bs == null) {
            canvas.createBufferStrategy(2);
            canvas.requestFocus();
            return;
        }

        Graphics2D g = (Graphics2D)bs.getDrawGraphics();

        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        gl.render(g);

        g.dispose();
        bs.show();
        Toolkit.getDefaultToolkit().sync();
    }

    public Renderer getRenderer() {
        return gl;
    }
}

