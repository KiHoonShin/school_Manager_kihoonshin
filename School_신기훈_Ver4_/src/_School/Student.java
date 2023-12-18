package _School;

public class Student {
	int stuNo;
	String stuName;
	String stuId;
	
	Student(int stuNo, String stuName, String stuId){
		this.stuNo = stuNo;
		this.stuName = stuName;
		this.stuId = stuId;
	}
	
	@Override
	public String toString() {
		String data = "";
		data += stuNo+"\t"+stuName+"\t"+stuId;
		return data;
	}
	
	
	
}
