import GUI.GUILogic;
import config.Configurations;

public class Main {
	//Starts public main method.
	
	public static void main(String[] args) {
		Configurations cf = new Configurations();
		
		cf.ReadFile();
		
		System.out.println(cf.getUsername());
		System.out.println(cf.getPassword());
		System.out.println(cf.getHost());
		System.out.println(cf.getFfcryptkey());
		System.out.println(cf.getDbname());
		new GUILogic().run();
	}

}
