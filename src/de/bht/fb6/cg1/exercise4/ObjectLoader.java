package de.bht.fb6.cg1.exercise4;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import org.lwjgl.opengl.GL11;

/**
 * represents objectloader
 */
public class ObjectLoader{

    /** 
     * list of all vertices
     */
    protected final List<Float[]> vertices;
    /**
     * list of all normals
     */
    protected final List<Float[]> normales;
    /**
     * list of all textures
     */
    protected final List<Float[]> textures;
    /**
     * true if it has normal coordinates
     */
    protected boolean hasNormals;
    /**
     * true if it has texture coordinates
     */
    protected boolean hasTextures;
    /**
     * helper list which holds all face indices of the obj-file
     */
    protected final List<List<Integer[]>> faceIndices;

    /**
     * constructs objectloader
     * 
     * @param name
     * @param filename
     */
    protected ObjectLoader(String name,String filename) {
	vertices = new ArrayList<Float[]>();
	normales = new ArrayList<Float[]>();
	textures = new ArrayList<Float[]>();
	hasNormals = false;
	hasTextures = false;
	faceIndices = new ArrayList<List<Integer[]>>();
	loadFigure(filename);
    }
    
    /**
     * loads a obj
     * 
     * @param filename
     */
    protected void loadFigure(String filename) {
	Scanner fin = null;
	try {
	    fin = new Scanner(new BufferedReader(new InputStreamReader(
		    new FileInputStream(filename), "UTF-8")));
	    while (fin.hasNextLine()) {
		try {
		    String line = fin.nextLine();
		    // v
		    if(line == null || line.length() < 3) {
			continue;
		    }
		    if (line.charAt(0) == 'v') {
			// vn
			if (line.charAt(1) == 'n') {
			    hasNormals = true;
			    normales.add(parseFloat(line));
			}
			// vt
			else if (line.charAt(1) == 't') {
			    hasTextures = true;
			    textures.add(parseFloat(line));
			} else {
			    // v
			    vertices.add(parseFloat(line));
			}
			// f
		    } else if (line.charAt(0) == 'f') {
			// f v
			// f v/vt/vn
			// f v//vn
			faceIndices.add(parseFace(line));
		    }
		} catch (IllegalArgumentException e) {
		    // skip line if line is not important
		}
	    }
	} catch (FileNotFoundException e) {
	    e.printStackTrace();
	} catch (UnsupportedEncodingException e) {
	    e.printStackTrace();
	} finally {
	    fin.close();
	}
	normalizeVertices();
    }

    /**
     * parses float array in string
     * 
     * @param line
     * @return float[]
     */
    protected Float[] parseFloat(String line) {
	if (line == null) {
	    throw new NullPointerException("'line' must not be 'null'");
	}
	Scanner in = new Scanner(line);
	in.useLocale(Locale.US);
	in.next();
	Float[] element = new Float[3];
	for (int i = 0; in.hasNextFloat(); ++i) {
	    element[i] = in.nextFloat();
	}
	return element;
    }

    /**
     * parses a face in string
     * 
     * @param line
     * @return face
     */
    protected List<Integer[]> parseFace(String line) {
	if (line == null) {
	    throw new NullPointerException("'line' must not be 'null'");
	}
	int size = 1;
	if (line.contains("/")) {
	    hasNormals = true;
	    if (line.contains("//")) {
		size = 2;
	    } else {
		hasTextures = false;
		size = 3;
	    }
	    line = line.replace("/", " ");
	}
	Scanner in = new Scanner(line);
	in.next(); // "f"
	List<Integer[]> list = new ArrayList<Integer[]>();
	while (in.hasNext()) {
	    Integer[] indices = new Integer[size];
	    for (int i = 0; i < size; ++i) {
		if (in.hasNextInt()) {
		    indices[i] = in.nextInt();
		}
	    }
	    list.add(indices);
	}	
	return list;
    }

    /**
     * normalizes all vertices to 1
     */
    protected void normalizeVertices() {
	List<Float[]> normalizedVertices = new ArrayList<Float[]>();
	Float max = Float.MIN_VALUE;
	for (Float[] vertice : vertices) {
	    if (Math.abs(vertice[0]) > max) {
		max = vertice[0];
	    }
	    if (Math.abs(vertice[1]) > max) {
		max = vertice[1];
	    }
	    if (Math.abs(vertice[2]) > max) {
		max = vertice[2];
	    }
	}
	for (Float[] vertice : vertices) {
	    vertice[0] /= max;
	    vertice[1] /= max;
	    vertice[2] /= max;
	    normalizedVertices.add(vertice);
	}
	vertices.clear();
	vertices.addAll(normalizedVertices);
    }

    /**
     * draws object
     */
    public void draw() {
	draw(1);
    }
    
    /**
     * draws object with changed normals (some obj-files have upsidedown normals)
     * 
     * @param nMul
     */
    public void draw(final float nMul) {
	for (List<Integer[]> indices : faceIndices) {
	    GL11.glBegin(GL11.GL_TRIANGLE_FAN);
//	    GL11.glBegin(GL11.GL_TRIANGLE_STRIP);
//	    GL11.glBegin(GL11.GL_POLYGON);
	    List<Float[]> verticesThisList = new ArrayList<Float[]>();
	    for (Integer[] elements : indices) {
		verticesThisList.add(vertices.get(elements[0]-1));
	    }
	    for (Integer[] element : indices) {
		if(hasNormals) {
		    Float[] n = normales.get(element[(hasTextures)?2:1]-1);
		    GL11.glNormal3f(nMul*n[0], nMul*n[1], nMul*n[2]);
		} else {
//		    Float[] n = getTriangleNormal(vertices.get(indices.get(0)[0]-1), vertices.get(indices.get(1)[0]-1), vertices.get(indices.get(2)[0]-1));
		   
		    Float[] n = getArbitraryPolygonNormal(verticesThisList);
		    GL11.glNormal3f(nMul*n[0], nMul*n[1],nMul*n[2]);
		}
		if(hasTextures) {
		    Float[] t = textures.get(element[1]-1);
		    GL11.glTexCoord2f(t[0], t[1]);
		} else {
		    GL11.glTexCoord2f(vertices.get(element[0]-1)[0], vertices.get(element[0]-1)[1]);
		}
		Float[] v = vertices.get(element[0]-1);
		GL11.glVertex3f(v[0], v[1], v[2]);
	    }
	    GL11.glEnd();
	}
    }
    
    /**
     * gets arbitrary normal for a face
     * 
     * @param face
     * @return normal
     */
    protected static Float[] getArbitraryPolygonNormal(List<Float[]> face) {
	Float[] normal = new Float[] {0f,0f,0f};
	for(int i = 0; i < face.size(); ++i) {
	    Float[] current = face.get(i);
	    Float[] next = face.get((i+1) % face.size());
	    normal[0] += (current[1]-next[1])*(current[2]+next[2]);
	    normal[1] += (current[2]-next[2])*(current[0]+next[0]);
	    normal[2] += (current[0]-next[0])*(current[1]+next[1]);
	}
	return normal;
    }
}
