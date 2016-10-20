import gui_package.Screen;
import user_package.Generator;
import user_package.Generator_Heartbeat;
import user_package.Generator_Steps;
import user_package.Generator_Temperature;
import user_package.User;

//Can be renamed as main
public class runMVC {
	//Model, View, Controller Class (a.k.a. main)
	public static void main(String args[]){
		
		//Screen gui = new Screen();
		
		//GENERATORS
		Generator gen = new Generator(new User("Henry", 24, 70, 164, 5, "henry@hotmail.com"));
		Generator gen1 = new Generator(new User("Monica", 36, 52, 152, 3, "monicaLeal@hotmail.com"));
		Generator gen2 = new Generator(new User("Usain", 30, 95, 195, 7, "usainbolt@hotmail.com"));
		
		//DEBUG
		gen.printGenerator();
		
		//STEPS GENERATOR
		//gen.user.printUser();
		Generator_Steps steps = new Generator_Steps(gen.getUser());
		//steps.printGeneratorInfo();
		//steps.printUser();
		//steps.printDetailDistance();
		Generator_Steps steps1 = new Generator_Steps(gen1.getUser());
		Generator_Steps steps2 = new Generator_Steps(gen2.getUser());
		//gen1.user.printUser();
		//steps1.printDetailDistance();
		//gen2.user.printUser();
		//steps2.printDetailDistance();
		
		//TEMPERATURE GENERATOR
		Generator_Temperature temp = new Generator_Temperature(gen.getUser());
		//temp.testRandomness();
		//temp.printDetailTemperature();
		
		//HEARTBEAT GENERATOR
		Generator_Heartbeat heart = new Generator_Heartbeat(gen.getUser());
		heart.testRandomness();
		heart.printDetailTemperature();
		
	}//end main
	

}
