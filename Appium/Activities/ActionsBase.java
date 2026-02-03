package activitiesAppium;

import org.openqa.selenium.Point;
import org.openqa.selenium.devtools.v135.page.model.Viewport;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.PointerInput.Kind;
import org.openqa.selenium.interactions.Sequence;
import static java.time.Duration.ofMillis;
import static org.openqa.selenium.interactions.PointerInput.Origin.viewport;

import java.util.Arrays;

import static org.openqa.selenium.interactions.PointerInput.MouseButton.LEFT;

import io.appium.java_client.AppiumDriver;

public class ActionsBase {
    // Set the pointer type
  private final static	PointerInput finger = new PointerInput(Kind.TOUCH,"finger");
	
  //Create swipe action
  public static void doSwipe(AppiumDriver driver , Point start , Point end, int duration) {
	
	  //create the sequence of actions
	  Sequence swipe = new Sequence(finger, 1);
	  swipe.addAction(finger.createPointerMove(ofMillis(0), viewport(),start.getX(), start.getY()));
      swipe.addAction(finger.createPointerDown(0));   // 0 =LEFT CLICK ON MOBILE  or finger.createPointerDown(LEFT.asArg())
      swipe.addAction(finger.createPointerMove(ofMillis(duration), viewport(), end.getX(), end.getY()));
      swipe.addAction(finger.createPointerUp(LEFT.asArg()));
      driver.perform(Arrays.asList(swipe));
	  
		
	}
		
	
}