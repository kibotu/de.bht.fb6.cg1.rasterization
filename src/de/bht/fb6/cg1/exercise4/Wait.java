package de.bht.fb6.cg1.exercise4;

public class Wait {

    /** 
     * static class
     */
    private Wait() {
    }
    
    /** 
     * pauses currentThread for that amount of seconds
     * 
     * @param seconds
     */
    public static void pause(float s) {
	try {
	    Thread.currentThread();
	    Thread.sleep((long)(s * 1000));
	} catch (InterruptedException e) {
	    e.printStackTrace(System.err);
	}
    }
}