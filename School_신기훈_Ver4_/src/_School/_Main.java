package _School;

public class _Main {

	public static void main(String[] args) {
		System.out.println(Utils.getInstance().getClass().getPackageName());
		Controller cont = new Controller();
		cont.run();
		//테스트
	}

}
