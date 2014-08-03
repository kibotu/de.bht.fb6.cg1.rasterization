package config;

import java.util.HashMap;
import java.util.Map;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;

/**
 * config file for filepaths and static stuff
 */
public class Config {

	/**
	 * static class
	 */
	private Config() {
	}

	final public static int TRIANGLE_DRAWING_STYLE = GL11.GL_TRIANGLES;
	// final public static int TRIANGLE_DRAWING_STYLE = GL11.GL_LINE_LOOP;
	// final public static int SPHERE_DRAWING_STYLE = GLU.GLU_SILHOUETTE;
	final public static int SPHERE_DRAWING_STYLE = GLU.GLU_FILL;

	final public static String OBJECT_PATH = "objs/";
	final public static String TEXTURE_PATH = "textures/";
	final public static String FIGURE_PATH = "figures/";
	final public static int DISPLAY_WIDTH = 800;
	final public static int DISPLAY_HEIGHT = 600;

	/**
	 * holds all texture paths
	 */
	final public static Map<String, String> TEXTURES = getTextures();
	/**
	 * holds all obj-file paths
	 */
	final public static Map<String, String> OBJECTS = getObjects();

	public static final String OBJECT_TEDDY = OBJECT_PATH + "teddy2.obj";
	public static final String OBJECT_PHOBOS = OBJECT_PATH + "phobos.obj";
	public static final String OBJECT_DEIMOS = OBJECT_PATH + "deimos.obj";
	public static final String OBJECT_PORSCHE = OBJECT_PATH + "porsche.obj";
	public static final String OBJECT_TEAPOT = OBJECT_PATH + "teapot.obj";
	public static final String OBJECT_FIGURE = OBJECT_PATH + "monkey.obj";
	public static final String OBJECT_CANDLE = OBJECT_PATH + "candle.obj";
	public static final String OBJECT_TREE = OBJECT_PATH + "Double10.obj";
	public static final String OBJECT_WOMAN = OBJECT_PATH
			+ "victoria-standing.obj";

	public static final float WORLD_SIZE = 10000f;

	public static final float CAMERA_MOVE_SPEED = 1f;
	public static final float CAMERA_ROTATE_SPEED = 0.02f;

	/**
	 * generates texture filepaths
	 * 
	 * @return map
	 */
	private static Map<String, String> getTextures() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("cube", TEXTURE_PATH + "whiteMarble.jpg");
		map.put("bottom-plane", TEXTURE_PATH + "bricks.bmp");

		map.put("moon", TEXTURE_PATH + "moonmap1k.jpg");
		map.put("mars", TEXTURE_PATH + "mars_1k_color.jpg");
		map.put("earth", TEXTURE_PATH + "earthmap1k.jpg");
		map.put("sun", TEXTURE_PATH + "sunmap.jpg");

		String cubeMapPath[] = new String[] { "cubemap_unwrapped/unwrapped_",
				"cubemap_redsky/redsky_", "cubemap_basic/",
				"cubemap_quadrangle/quadrangle_", "cubemap_opensea/opensea_",
				"cubemap_nvlobby_new/nvlobby_new_", "cubemap_night/night_",
				"cubemap_landscape/landscape_", "cubemap_hotdesert/hotdesert_",
				"cubemap_blue_sofa/cubemap_blue_sofa_",
				"cubemap_berkeley/berkeley_", "h_skyboxpack2_rays/" };

		for (int i = 0; i < cubeMapPath.length; ++i) {
			map.put("skyBox" + i + "-top", TEXTURE_PATH + cubeMapPath[i]
					+ "positive_y.png");
			map.put("skyBox" + i + "-bottom", TEXTURE_PATH + cubeMapPath[i]
					+ "negative_y.png");
			map.put("skyBox" + i + "-left", TEXTURE_PATH + cubeMapPath[i]
					+ "negative_x.png");
			map.put("skyBox" + i + "-right", TEXTURE_PATH + cubeMapPath[i]
					+ "positive_x.png");
			map.put("skyBox" + i + "-near", TEXTURE_PATH + cubeMapPath[i]
					+ "positive_z.png");
			map.put("skyBox" + i + "-far", TEXTURE_PATH + cubeMapPath[i]
					+ "negative_z.png");
		}
		return map;
	}

	/**
	 * generates obj-file paths
	 * 
	 * @return map
	 */
	private static Map<String, String> getObjects() {
		Map<String, String> map = new HashMap<String, String>();

		map.put("teddy", OBJECT_PATH + "teddy.obj");
		map.put("girl", OBJECT_PATH + "girl.obj");
		map.put("f-16", OBJECT_PATH + "f-16.obj");
		map.put("porsche", OBJECT_PATH + "porsche.obj");

		return map;
	}
}