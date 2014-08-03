package de.bht.fb6.cg1.exercise4;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import org.lwjgl.opengl.GL11;

import config.Config;

/**
 * Handles all Textures as Singleton
 * 
 */
public class TextureLoader {

	/**
	 * singleton instance
	 */
	protected static final TextureLoader INSTANCE = new TextureLoader();
	/**
	 * represents textures
	 */
	protected final Map<String, Integer> textureList;
	/**
	 * if wrap true different filters will be applied
	 */
	protected boolean wrap;

	/**
	 * constructs textureloader
	 */
	protected TextureLoader() {
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		textureList = new HashMap<String, Integer>();
		wrap = false;
	}

	/**
	 * gets an instance of textureloader
	 * 
	 * @return instance
	 */
	public static TextureLoader getInstance() {
		return INSTANCE;
	}

	/**
	 * loads a texture
	 * 
	 * @param textureName
	 * @param filePath
	 */
	protected void load(String textureName, String filePath) {
		int textureId = GL11.glGenTextures();
		textureList.put(textureName, textureId);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, textureId);
		BufferedImage image = null;
		try {
			image = ImageIO.read(new File(filePath));
		} catch (IOException e) {
			e.printStackTrace(System.err);
		}
		int w = image.getWidth();
		int h = image.getHeight();
		int[] rgbs = new int[w * h];
		image.getRGB(0, 0, w, h, rgbs, 0, w);
		final ByteBuffer byteBuffer = ByteBuffer.allocateDirect(w * h * 4);
		for (final int p : rgbs) {
			final int r = (p & (0x00ff0000)) >> 16;
			final int g = (p & (0x0000ff00)) >> 8;
			final int b = p & (0x000000ff);
			byteBuffer.put((byte) r);
			byteBuffer.put((byte) g);
			byteBuffer.put((byte) b);
		}
		byteBuffer.rewind();
		GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, 3, w, h, 0, GL11.GL_RGB,
				GL11.GL_UNSIGNED_BYTE, byteBuffer);
		// GL11.glTexEnvf( GL11.GL_TEXTURE_ENV,
		// GL11.GL_TEXTURE_ENV_MODE,GL11.GL_MODULATE );
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER,
				wrap ? GL11.GL_NEAREST : GL11.GL_LINEAR);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER,
				wrap ? GL11.GL_NEAREST : GL11.GL_LINEAR);
		GL11.glTexParameterf(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S,
				wrap ? GL11.GL_CLAMP : GL11.GL_REPEAT);
		GL11.glTexParameterf(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T,
				wrap ? GL11.GL_CLAMP : GL11.GL_REPEAT);
	}

	/**
	 * switches wrapper
	 */
	public void switchWrap() {
		wrap = !wrap;
	}

	/**
	 * draws a texture, loads it, if it hasn't been loaded yet
	 * 
	 * @param name
	 */
	public void drawTexture(String name) {
		if (!textureList.containsKey(name)) {
			load(name, Config.TEXTURES.get(name));
		}
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, textureList.get(name));
	}

	/**
	 * sets default Texture
	 */
	public void defaultTexture() {
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
	}
}
