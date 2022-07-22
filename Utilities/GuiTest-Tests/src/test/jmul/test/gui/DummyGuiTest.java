package test.jmul.test.gui;


import java.awt.event.KeyEvent;

import jmul.test.classification.GUITest;
import jmul.test.gui.GUITestBase;
import jmul.test.gui.GuiApplicationStarter;
import jmul.test.gui.KeyboardInputHelper;
import jmul.test.gui.PauseHelper;

import org.junit.Test;

import test.jmul.test.gui.dummy.DummyGui;


/**
 * An example of a GUI test with in built java features.
 *
 * @author Kristian Kutin
 */
@GUITest
public class DummyGuiTest extends GUITestBase {

    /**
     * The default constructor.
     */
    public DummyGuiTest() {

        super(new DummyGuiStarter());
    }

    /**
     * The actual GUI test (i.e. start and close a dummy application).
     */
    @Test
    public void testStartAndExit() {

        PauseHelper.waitForComponentWithFocus();
        PauseHelper.sleepSeconds(5);

        KeyboardInputHelper.holdKey(getRobot(), "ALT", KeyEvent.VK_ALT);
        KeyboardInputHelper.pressKey(getRobot(), "ALT", KeyEvent.VK_F);
        KeyboardInputHelper.releaseKey(getRobot(), "ALT", KeyEvent.VK_ALT);
        PauseHelper.sleepSeconds(1);

        KeyboardInputHelper.holdKey(getRobot(), "ALT", KeyEvent.VK_ALT);
        KeyboardInputHelper.pressKey(getRobot(), "ALT", KeyEvent.VK_E);
        KeyboardInputHelper.releaseKey(getRobot(), "ALT", KeyEvent.VK_ALT);
        PauseHelper.sleepSeconds(5);
    }

}


/**
 * A starter for the dummy GGUI application.
 *
 * @author Kristian Kutin
 */
class DummyGuiStarter implements GuiApplicationStarter {

    /**
     * A GUI application starter.
     */
    @Override
    public void start() {

        DummyGui.main();
    }

}
