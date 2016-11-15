package gui_package;

import gui_package.Screen;
import javafx.application.Application;
import javafx.stage.Stage;
/** This is the model/view/controller class for the 
 *  program that runs the program.
 * @author JSSP Engineers
 * @version 1.0
 */
public class runMVC extends Application{
	/**Main class calls the beginning of Screen
	 * @see gui_package.Screen
	 * @param args[]
	 */
	public static void main(String args[]){
	
		Screen gui = new Screen();
		launch(args);
				
	}//end main
	/**Extended method from Application package. Unimplemented to override
	 * start method from Application class.
	 * 
	 */
	@Override
	public void start(Stage arg0) throws Exception {
		
	}
}//end class