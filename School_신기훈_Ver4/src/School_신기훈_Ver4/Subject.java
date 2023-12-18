package School_신기훈_Ver4;

import java.util.Arrays;

public class Subject {
	int stuNo;
	String subName;
	int score;
	
	Subject(int stuNo, String subName, int score){
		this.stuNo = stuNo;
		this.subName = subName;
		this.score = score;
	}

	@Override
	public String toString() {
		String data = "";
		data += subName +" " + score+"점 " ;
		//data = data.substring(0, data.length()-1);
		return data;
	}
	
	
	
}
