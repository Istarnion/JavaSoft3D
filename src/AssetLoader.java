import java.util.Arrays;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AssetLoader {
    public static Model loadOBJ(String filename) {
        float[] vertices = new float[3*64];
        int[] indices = new int[3*64];

        int numVectors = 0;
        int numFaces = 0;

        try(BufferedReader br = new BufferedReader(new FileReader(filename))) {
            for(String line; (line = br.readLine()) != null;) {
                String[] parts = line.split("\\s+");
                if("v".equals(parts[0].trim())) {
                    if(numVectors*3 == vertices.length) {
                        vertices = Arrays.copyOf(vertices, vertices.length*2);
                    }

                    float x = Float.parseFloat(parts[1].trim());
                    float y = Float.parseFloat(parts[2].trim());
                    float z = Float.parseFloat(parts[3].trim());
                    vertices[3*numVectors+0] = x;
                    vertices[3*numVectors+1] = y;
                    vertices[3*numVectors+2] = z;
                    ++numVectors;
                }
                else if("f".equals(parts[0].trim())) {
                    if(numFaces*3 == indices.length) {
                        indices = Arrays.copyOf(indices, indices.length*2);
                    }

                    int a = Integer.parseInt(parts[1].trim());
                    int b = Integer.parseInt(parts[2].trim());
                    int c = Integer.parseInt(parts[3].trim());
                    indices[3*numFaces+0] = a;
                    indices[3*numFaces+1] = b;
                    indices[3*numFaces+2] = c;
                    ++numFaces;
                }
            }
        }
        catch(IOException e) {
            return null;
        }

        if(vertices.length != numVectors*3) vertices = Arrays.copyOf(vertices, 3*numVectors);
        if(indices.length != numFaces*3)    indices = Arrays.copyOf(indices, 3*numFaces);

        System.out.println("Vertices: " + vertices.length/3 + ", Faces: " + indices.length/3);

        return new Model(vertices, indices);
    }
}

