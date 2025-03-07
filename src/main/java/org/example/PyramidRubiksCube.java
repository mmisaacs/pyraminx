package org.example;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

enum Color {red, green, blue, yellow}

class Face{
    private Color[] tiles;
    public Face(Color[] colors){
        tiles = new Color[9];
        tiles = Arrays.copyOf(colors, 9);
    }

    public Color[] getTiles(){
        return tiles;
    }
}

public class PyramidRubiksCube {
    // the yellow face is the primary face, initializing in relation to yellow face
    /* array indexes are in the format of
            0
          1 2 3
        4 5 6 7 8   */

    private Face[] faces;
    private final HashMap<String, Color[]> tips;
    private final HashMap<String, Color[]> edges;
    private HashMap<Integer, List<Integer>> adjacencyFaces;

    //private HashMap<String, Color[]> faces;
    public PyramidRubiksCube(){
        faces = new Face[4];
        tips = new HashMap<String, Color[]>();
        edges = new HashMap<String, Color[]>();

        initializeTips();
        initializeEdges();
        initializeFaces();
    }

    public void initializeTips(){
        //HashMap<String, Color[]> tips = new HashMap<>();
        tips.put("Top", new Color[]{Color.yellow, Color.blue, Color.green});
        tips.put("Left", new Color[]{Color.yellow, Color.green, Color.red});
        tips.put("Right", new Color[]{Color.yellow, Color.red, Color.blue});
        tips.put("Back", new Color[]{Color.blue, Color.green, Color.red});
    }

    public void initializeEdges(){
        //initialize edges so that their adjacent colors are assigned together
        edges.put("Edge1", new Color[]{Color.yellow, Color.blue});
        edges.put("Edge2", new Color[]{Color.yellow, Color.red});
        edges.put("Edge3", new Color[]{Color.yellow, Color.green});
        edges.put("Edge4", new Color[]{Color.blue, Color.red});
        edges.put("Edge5", new Color[]{Color.blue, Color.green});
        edges.put("Edge6", new Color[]{Color.green, Color.red});
    }
    public void initializeFaces(){
        //make it easy to identify what faces are next to each other
        //listed starting from yellow side: List.of(left of face, right of face, under face)
        adjacencyFaces = new HashMap<>();
        adjacencyFaces.put(0, List.of(1, 2, 3));
        adjacencyFaces.put(1, List.of(2, 0, 3));
        adjacencyFaces.put(2, List.of(0, 1, 3));
        adjacencyFaces.put(3, List.of(1, 0, 2));


        //an array identifying the edge indices
        //each line is in (face1, index1, face2, index2)
        int[][] edgeIndices = {
                {0, 1, 1, 3},
                {0, 3, 2, 1},
                {0, 6, 3, 3},
                {1, 1, 3, 3},
                {1, 6, 3, 1},
                {2, 6, 3, 6},
        };

        //get color values in an array
        Color[] faceColors = Color.values();

        //for loop to loop through all faces, filling the tiles array with a singluar color
        for(int i = 0; i < 4; i++){
            Color[] tiles = new Color[9];
            Arrays.fill(tiles, faceColors[i]);
            faces[i] = new Face(tiles);
        }
    }
    public boolean validateCube(){
        //catch statement for future if mixed tiles faces are implemented
        //checks if faces is empty or not initialized
        if (faces == null || faces.length == 0){
            return false;
        }

        HashMap<Color, Integer> colorCount = new HashMap<>();

        //fill hashmap with all color values
        for(Color color : Color.values()){
            colorCount.put(color, 0);
        }

        // count tiles on faces
        for (Face face : faces) {
            Color[] tiles = face.getTiles();

            for (Color tile : tiles) {
                colorCount.put(tile, colorCount.get(tile) + 1);
            }
        }

        for (Color color : Color.values()) {
            if (colorCount.get(color) != 9) {
                return false;
            }
        }

        //all checks have been passed and hashmap colors = 9
        return true;
    }
    
    public static void main(String[] args){
        PyramidRubiksCube pyraminx = new PyramidRubiksCube();
        System.out.println(pyraminx.validateCube()); //should produce true!
    }
}