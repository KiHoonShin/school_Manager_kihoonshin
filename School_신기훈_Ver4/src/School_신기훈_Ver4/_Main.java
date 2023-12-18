package School_신기훈_Ver4;

public class _Main {

	public static void main(String[] args) {

		System.out.println(Utils.getInstance().getClass().getPackageName());
		Controller cont = new Controller();
		cont.run();
		
	}

}
