package _School;

import java.util.ArrayList;
import java.util.Arrays;

public class StudentDAO {
	public ArrayList<Student> stuList; 
	//private Utils sc;
	private int maxNo;
	private int count;
	
	private ArrayList<Double> tempList;
	public StudentDAO(){
		//sc = Utils.getInstance();
		tempStuData();
	}
	
	private void tempStuData() {
		String data = Utils.stuDataRoad();
		//System.out.println(data);
		String[] temp = data.split("\n");
		stuList = new ArrayList<Student>(temp.length);
		int maxNo = 0;
		for(int i = 0; i < temp.length; i++) {
			String[] info = temp[i].split("/");
			Student s = new Student(Integer.parseInt(info[0]),info[1],info[2]);
			stuList.add(s);
			maxNo = Integer.parseInt(info[0]);
		}
		this.maxNo = maxNo;
		this.count = stuList.size();
	}
	
	// 
	public void makeAvg(SubjectDAO subDAO) {
		
//		ArrayList<Double> tempList = new ArrayList<Double>(stuList.size());
		tempList = new ArrayList<Double>(stuList.size());
		for(int i = 0; i < stuList.size(); i++) {
			int stuNo = stuList.get(i).stuNo;
			double avg = subDAO.makeAvg(stuNo);
			tempList.add(avg);
		}
		
		for(int i = 0; i < tempList.size(); i++) {
			double min = tempList.get(i);
			for(int k = i; k < tempList.size(); k++) {
				if(min < tempList.get(k)) {
					min = tempList.get(k);
					
					double temp2 = tempList.get(k);
					tempList.set(k, tempList.get(i));
					tempList.set(i, temp2);
					
					Student temp = stuList.get(k);
					stuList.set(k, stuList.get(i));
					stuList.set(i , temp);
				}
			}
		}
		
		// set 이용해서 정렬 다시하는법 ->
//			Student temp = stuList.get(0);
//			stuList.set(0, stuList.get(2));
//			stuList.set(2, temp);
		//printStu(subDAO);
	}
	
	
	
	public void printStu(SubjectDAO subDAO) {
		makeAvg(subDAO);
		for(int i = 0; i < stuList.size(); i++) {
			Student stu = new Student(stuList.get(i).stuNo,stuList.get(i).stuName,stuList.get(i).stuId);
			System.out.println(stu);
			//String data = "";
			
			for(Subject sub : subDAO.subList) {
				if(stu.stuNo == sub.stuNo) {
				//	data += sub;
				//	data = data.substring(0, data.length()-1);
					System.out.print(sub);
				}
			}
			
			System.out.println();
			if(tempList.get(i) != 0) {
				System.out.printf("평균 : %.2f점 %n",tempList.get(i));
			} else {
				System.out.println("[no subject data]");
			}
			System.out.println("----------------");
		}
	}
	
	public void printOne(Subject sub) {
		for(Student stu : stuList) {
			if(stu.stuNo == sub.stuNo) {
				System.out.println(stu);
			}
				}
	}
	
	
	
	public void delOneStu(SubjectDAO subDAO) {
		 if(!hasData()) return;
		 if(count == 0) {
			 System.out.println("[ no student data ]");
			 return;
		 }
		String id = Utils.getStr("아이디");
		Student stu = isId(id);
		if(stu == null) {
			System.out.println("존재하지 않는 아이디 입니다.");
			return;
		}
		int delIdx = findIdIdx(stu);
		//System.out.println(delIdx);
		for(int i = 0; i < stuList.size(); i ++) {
			if(delIdx == i) {
				stuList.remove(delIdx);
			}
		}
		subDAO.delOneStuOneSub(stu);
		System.out.println(stu);
		System.out.println("학생 한명 삭제 완료");
		count -=1;
	}
	
	// id 체크
	private Student isId(String id) {
		if(stuList == null) return null;
		for(Student s : stuList) {
			if(id.equals(s.stuId)) {
				return s;
			}
		}
		return null;
	}
	
	// id idx찾기
	private int findIdIdx(Student stu) {
	//	int idx = 0;
		for(int i = 0; i < stuList.size(); i++) {
			if(stu.stuNo == stuList.get(i).stuNo) {
				return i;
			}
		}
//		for(Student s: stuList) {
//			if(stu == s) {
//				return idx;
//			}
//			idx +=1;
//		}
		return -1;
	}
	
	// 학생 추가
	public void plusOne() {
		String id = Utils.getStr("아이디");
		Student stu = isId(id);
		if(stu != null) {
			System.out.println("이미 존재하는 아이디가 있습니다.");
			return;
		}
		String name = Utils.getStr("이름");
		Student s = new Student(++maxNo, name, id);
		stuList.add(s);
		System.out.println(s);
		System.out.println("학생 한명 추가 완료");
	}
	
	//학번 체크
	private Student isHakbun(int num) {
		if(stuList == null) return null;
		for(Student s : stuList) {
			if(num == s.stuNo) {
				return s;
			}
		}
		return null;
	}
	
	// 한 과목 추가
	void plusOneSub(SubjectDAO subDAO) {
		int num = Utils.getInt("학번", 1001, maxNo);
		Student stu = isHakbun(num);
		if(stu == null) {
			System.out.println("해당 학번 학생이 존재하지 않습니다.");
			return;
		}
		System.out.println(stu);
		subDAO.printSub(stu);
		if(!subDAO.plusOneSub(stu)) {
			return;
		}
		System.out.println(stu);
		subDAO.printSub(stu);
		System.out.println("과목 추가 완료");
	}
	
	// 한 과목 삭제 
	public void deleteOneSub(SubjectDAO subDAO) {
		int num = Utils.getInt("학번", 1001, maxNo);
		Student stu = isHakbun(num);
		if(stu == null) {
			System.out.println("해당 학번 학생이 존재하지 않습니다.");
			return;
		}
		System.out.println(stu);
		subDAO.printSub(stu);
		if(!subDAO.hasData(stu)) return;
		if(!subDAO.deleteOneSub(stu)) {
			return;
		}
		subDAO.printSub(stu);
		System.out.println("과목 삭제 완료");
	}
	
	private boolean hasData() {
		if(stuList == null) {
			System.out.println("[no student data]");
			return false;
		}
		return true;
	}
}
