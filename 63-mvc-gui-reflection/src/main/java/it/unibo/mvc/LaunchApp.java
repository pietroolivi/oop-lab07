package it.unibo.mvc;

import java.lang.reflect.InvocationTargetException;

import it.unibo.mvc.api.DrawNumberController;
import it.unibo.mvc.api.DrawNumberView;
import it.unibo.mvc.controller.DrawNumberControllerImpl;
import it.unibo.mvc.model.DrawNumberImpl;
import it.unibo.mvc.view.DrawNumberStdOutView;
import it.unibo.mvc.view.DrawNumberSwingView;

/**
 * Application entry-point.
 */
public final class LaunchApp {

    private LaunchApp() { }

    /**
     * Runs the application.
     *
     * @param args ignored
     * @throws ClassNotFoundException if the fetches class does not exist
     * @throws NoSuchMethodException if the 0-ary constructor do not exist
     * @throws InvocationTargetException if the constructor throws exceptions
     * @throws InstantiationException if the constructor throws exceptions
     * @throws IllegalAccessException in case of reflection issues
     * @throws IllegalArgumentException in case of reflection issues
     */

    @SuppressWarnings("unchecked")
    public static void main(final String... args) 
    throws ClassNotFoundException, 
    NoSuchMethodException, 
    SecurityException,
    InstantiationException,
    IllegalAccessException, 
    IllegalArgumentException, 
    InvocationTargetException {
        final var model = new DrawNumberImpl();
        final DrawNumberController app = new DrawNumberControllerImpl(model);
        final Class<DrawNumberView> cl1 = (Class<DrawNumberView>) Class.forName("it.unibo.mvc.view.DrawNumberSwingView");
        final Class<DrawNumberView> cl2 = (Class<DrawNumberView>) Class.forName("it.unibo.mvc.view.DrawNumberStdOutView");
        for (int i = 0; i < 3; i++) {
            app.addView((DrawNumberView) cl1.getConstructor().newInstance());
            app.addView((DrawNumberView) cl2.getConstructor().newInstance());
        }
    }
}
