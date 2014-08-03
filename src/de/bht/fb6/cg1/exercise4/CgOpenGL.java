package de.bht.fb6.cg1.exercise4;

import java.util.HashMap;
import java.util.Map;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

import config.Config;

public final class CgOpenGL{

    /**
     * static class
     */
    private CgOpenGL() {
    }

    /**
     * framerate
     */
    private static final int FRAMERATE = 60;
    /**
     * This flag is true if the application should run. If it's set to false the
     * application will terminate.
     */
    private static boolean finished;

    private final static Map<String, Drawable> drawables = new HashMap<String, Drawable>();
    private final static Camera camera = new Camera();
    private final static Light light = Light.getInstance();

    /**
     * The main class.
     * 
     * @param args
     *            To arguments are consumed.
     */
    public static void main(final String[] args) {
	try {
	    init();
	    run();
	} catch (final Exception e) {
	    e.printStackTrace(System.err);
	} finally {
	    cleanup();
	}
	System.exit(0);
    }

    /**
     * The method creates the the window and sets up the projection matrix, the
     * viewport, and the initial model matrix.
     * 
     * @throws java.lang.Exception
     *             Is thrown on any possible error.
     */
    private static void init() throws Exception {
	Display.setTitle("OpenGL excercise");
	Display.setVSyncEnabled(true);
	final DisplayMode[] modes = Display.getAvailableDisplayModes();
	for (int i = 0; i < modes.length; ++i) {
	    if (modes[i].getWidth() == Config.DISPLAY_WIDTH) {
		Display.setDisplayMode(modes[i]);
		break;
	    }
	}
	Display.create();
	light.enableAll();
	initRendering();
	initDrawables();
	camera.switchToPerspView();
    }

    /**
     * inits rendering options
     */
    private static void initRendering() {
	GL11.glEnable(GL11.GL_DEPTH_TEST);
	GL11.glEnable(GL11.GL_NORMALIZE);
	GL11.glShadeModel(GL11.GL_SMOOTH);
	GL11.glHint(GL11.GL_PERSPECTIVE_CORRECTION_HINT, GL11.GL_NICEST);
    }

    /**
     * This method runs the application.
     */
    private static void run() {

	while (!finished) {
	    Display.update();
	    if (Display.isCloseRequested()) {
		finished = true;
	    } else if (Display.isActive()) {
		logic();
		render();
		Display.sync(FRAMERATE);
	    } else {
		try {
		    Thread.sleep(100);
		} catch (final InterruptedException e) {
		}
		logic();
		if (Display.isVisible() || Display.isDirty()) {
		    render();
		}
	    }
	}
    }

    /**
     * The clean up is called if the application fails.
     */
    private static void cleanup() {
//	Display.destroy();
    }

    /**
     * The logic of the application. Currently it processes the key input to
     * rotate the polygon.
     */
    private static void logic() {
	if (Keyboard.isKeyDown(Keyboard.KEY_O)) {
	    camera.switchToOrthView();
	}
	if (Keyboard.isKeyDown(Keyboard.KEY_P)) {
	    camera.switchToPerspView();
	}
	if (Keyboard.isKeyDown(Keyboard.KEY_W)) {
	    camera.move(Config.CAMERA_MOVE_SPEED);
	}
	if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
	    camera.move(-Config.CAMERA_MOVE_SPEED);
	}
	if (Keyboard.isKeyDown(Keyboard.KEY_A)) {
	    camera.strafe(-Config.CAMERA_MOVE_SPEED);
	}
	if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
	    camera.strafe(Config.CAMERA_MOVE_SPEED);
	}
	if (Keyboard.isKeyDown(Keyboard.KEY_R)) {
	    camera.zoom(-Config.CAMERA_ROTATE_SPEED);
	}
	if (Keyboard.isKeyDown(Keyboard.KEY_F)) {
	    camera.zoom(Config.CAMERA_ROTATE_SPEED);
	}	
	if(Keyboard.isKeyDown(Keyboard.KEY_I)) {
	   light.switchLight(GL11.GL_LIGHT0);
	   Wait.pause(0.075f);
	}
	if(Keyboard.isKeyDown(Keyboard.KEY_K)) {
	    light.switchLight(GL11.GL_LIGHT1);
	    Wait.pause(0.075f);
	}
	if (Mouse.isInsideWindow()) {
	    float mX = Mouse.getX() - Config.DISPLAY_WIDTH / 2;
	    float mY = Mouse.getY() - Config.DISPLAY_HEIGHT / 2;

	    if (mY > Config.DISPLAY_HEIGHT / 2 - 150) {
		// if (mY > 0) {
		camera.lookVertically(Config.CAMERA_ROTATE_SPEED);
	    }
	    if (mY < -Config.DISPLAY_HEIGHT / 2 + 150) {
		// if (mY < 0) {
		camera.lookVertically(-Config.CAMERA_ROTATE_SPEED);
	    }
	    if (mX > Config.DISPLAY_WIDTH / 2 - 150) {
		// if (mX > 0) {
		camera.lookHorizontally(Config.CAMERA_ROTATE_SPEED);
	    }
	    if (mX < -Config.DISPLAY_WIDTH / 2 + 150) {
		// if (mX < 0) {
		camera.lookHorizontally(-Config.CAMERA_ROTATE_SPEED);
	    }
	}
	
	// robitic arm
	if(Keyboard.isKeyDown(Keyboard.KEY_DOWN)) {
	    ((RoboticArm)drawables.get("robotic-arm")).moveZ(1f);
	}
	if(Keyboard.isKeyDown(Keyboard.KEY_UP)) {
	    ((RoboticArm)drawables.get("robotic-arm")).moveZ(-1f);
	}
	if(Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) {
	    ((RoboticArm)drawables.get("robotic-arm")).moveX(1f);
	}
	if(Keyboard.isKeyDown(Keyboard.KEY_LEFT)) {
	    ((RoboticArm)drawables.get("robotic-arm")).moveX(-1f);
	}
	
	if(Keyboard.isKeyDown(Keyboard.KEY_T)) {
	    ((RoboticArm)drawables.get("robotic-arm")).rotateArm1Y(1f);
	}
	if(Keyboard.isKeyDown(Keyboard.KEY_G)) {
	    ((RoboticArm)drawables.get("robotic-arm")).rotateArm1Y(-1f);
	}
	if(Keyboard.isKeyDown(Keyboard.KEY_B)) {
	    ((RoboticArm)drawables.get("robotic-arm")).rotateArm1XZ(1f);
	}
	
	if(Keyboard.isKeyDown(Keyboard.KEY_Z)) {
	    ((RoboticArm)drawables.get("robotic-arm")).rotateArm2Y(1f);
	}
	if(Keyboard.isKeyDown(Keyboard.KEY_H)) {
	    ((RoboticArm)drawables.get("robotic-arm")).rotateArm2Y(-1f);
	}
	if(Keyboard.isKeyDown(Keyboard.KEY_N)) {
	    ((RoboticArm)drawables.get("robotic-arm")).rotateArm2XZ(1f);
	}
	
	if(Keyboard.isKeyDown(Keyboard.KEY_U)) {
	    ((RoboticArm)drawables.get("robotic-arm")).rotateArm3Y(1f);
	}
	if(Keyboard.isKeyDown(Keyboard.KEY_J)) {
	    ((RoboticArm)drawables.get("robotic-arm")).rotateArm3Y(-1f);
	}
	if(Keyboard.isKeyDown(Keyboard.KEY_M)) {
	    ((RoboticArm)drawables.get("robotic-arm")).rotateArm3XZ(1f);
	}
	
	if(Keyboard.isKeyDown(Keyboard.KEY_Q)) {
	    Light.getInstance().changeCut(GL11.GL_LIGHT0, 1);
	}
	if(Keyboard.isKeyDown(Keyboard.KEY_E)) {
	    Light.getInstance().changeCut(GL11.GL_LIGHT0, -1);
	}
    }

    /**
     * inits some drawables
     */
    private static void initDrawables() {
	drawables.put("bottom-plane", new Plane("bottom-plane", 1000, 1000, 1000, 8, true));
	drawables.put("skyBox11", new SkyBox("skyBox11", 1024*2, 1024*2, 1024*2, 3, false));
	drawables.put("robotic-arm",new RoboticArm("robotic-arm", 30,30,30,3, false));
	drawables.put("teddy", new ObjPolygon("teddy", 10,10,10,0, true));
//	drawables.put("girl", new Polygon("girl", 10,10,10,0, true));
	drawables.put("f-16", new ObjPolygon("f-16", 100,100,100,0, true));
	drawables.put("porsche", new ObjPolygon("porsche", 100,100,100,0, true));
    }

    /**
     * draws a teddy
     */
    private static void drawTeddy() {
	GL11.glPushMatrix();
	GL11.glRotatef(180, 1f, 0f, 0f);
	GL11.glTranslatef(-200f, 0f, 200f);
	TextureLoader.getInstance().drawTexture("bottom-plane");
	drawables.get("teddy").draw();
	GL11.glPopMatrix();
    }
    
    /**
     * drwas bottom-plane
     */
    private static void drawBottomPlane() {
	GL11.glPushMatrix();
	GL11.glTranslatef(0f, -50f, 0f);
	drawables.get("bottom-plane").draw();
	GL11.glPopMatrix();
    }
    
    /**
     * draws aircraft
     */
    private static void drawAircraft() {
	GL11.glPushMatrix();
	GL11.glTranslatef(200f, -80f, 200f);
	TextureLoader.getInstance().drawTexture("bottom-plane");
	drawables.get("f-16").draw();
	GL11.glPopMatrix();
    }
    
    /**
     * draws roboticarm
     */
    private static void drawRoboticArm() {
	GL11.glPushMatrix();
	GL11.glTranslatef(100f, 0f, 100f);
	drawables.get("robotic-arm").draw();
	GL11.glPopMatrix();
    }
    
    /**
     * draws porsche
     */
    private static void drawPorsche() {
	GL11.glPushMatrix();
	GL11.glTranslatef(-200f, -20f, 200f);
	drawables.get("porsche").draw();
	GL11.glPopMatrix();
    }
    
    /**
     * This method renders the image.
     */
    private static void render() {
	GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_STENCIL_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
	light.addAmbient(new float[] { 0.2f, 0.2f, 0.2f, 1f });
	light.addDiffuse(GL11.GL_LIGHT1, new float[] { 1f, 1f, 1f, 1f });
	light.addSpecular(GL11.GL_LIGHT1, new float[] { 0.5f, 0.5f, 5f, 1f });
	light.setPosition(GL11.GL_LIGHT1, new float[]{0f, 100f, 0f, 1f});
	drawables.get("skyBox11").draw();
	drawBottomPlane();
	drawRoboticArm();
	drawTeddy();
	drawPorsche();
//	drawables.get("girl").draw();
	drawAircraft();
    }
}