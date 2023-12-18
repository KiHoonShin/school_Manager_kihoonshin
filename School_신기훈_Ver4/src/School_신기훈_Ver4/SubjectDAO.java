package School_신기훈_Ver4;

import java.util.ArrayList;
import java.util.Random;

public class SubjectDAO {
	public ArrayList<Subject> subList;
	//private Utils sc;
	private int size;
	
	SubjectDAO(){
		//sc = Utils.getInstance();
		tempSubData();
	}
	
	private void tempSubData() {
		String data = Utils.getInstance().subDataRoad();
		//System.out.println(data);
		String[] temp = data.split("\n");
		this.size = temp.length;
		subList = new ArrayList<Subject>(temp.length);
		for(int i = 0; i < temp.length; i++) {
			String[] info = temp[i].split("/");
			Subject s = new Subject(Integer.parseInt(info[0]),info[1],Integer.parseInt(info[2]));
			subList.add(s);
		}
	}
	
	public void printSub(Student stu) {
		//if(!hasData(stu)) return;
		for(Subject s : subList) {
			if(stu.stuNo == s.stuNo)
			System.out.print(s);
		}
		System.out.println();
	}
	
	public boolean hasData(Student stu) {
		for(Subject sub : subList) {
			if(stu.stuNo==sub.stuNo) {
				return true;
			}
		}
			System.out.println("[no subject data]");
			return false;
	}
	
	// 한 학생 삭제시 과목까지 삭제
	public void delOneStuOneSub(Student stu) {
		if(!hasData(stu)) return;
		//ArrayList<Subject> temp = new ArrayList<Subject>(subList);
		int idx = 0;
		if(subList == null) return;
		for(int i = 0; i < subList.size(); i++) {
			if(stu.stuNo==subList.get(i).stuNo) {
				subList.remove(i);
				i -=1;
			}
		}
//		for(Subject sub : temp) {
//			if(stu.stuNo == sub.stuNo) {
//				//삭제 메서드(idx)
//				removeOneSub(idx);
//				cnt +=1;
//			}
//			idx +=1;
//		}
	}
	
	//삭제 틀
	private void removeOneSub(int delIdx) {
		//if(!hasData()) return;
		for(int i = 0; i < subList.size(); i++) {
			if(delIdx == i) {
				subList.remove(delIdx);
			}
		}
	}
	
	// 한 과목 추가
	public boolean plusOneSub(Student stu) {
		Random rd = new Random();
		String subName = Utils.getStr("신규 과목 이름");
		Subject sub = hasSameSub(subName, stu);
		if(sub != null) {
			System.out.println("이미 해당 과목이 존재합니다.");
			return false;
		}
		Subject s = new Subject(stu.stuNo, subName, rd.nextInt(51)+50);
		subList.add(s);
		System.out.println(s);
		return true;
	}
	
	// 과목 중복 체크
	private Subject hasSameSub(String subName, Student stu) {
		if(subList == null) return null;
		for(Subject sub : subList) {
			if(stu.stuNo == sub.stuNo) {
				if(subName.equals(sub.subName)) {
					return sub;
				}
			}
		}
		return null;
	}
	
	// 한 과목 과목 삭제
	public boolean deleteOneSub(Student stu) {
		//if(!hasData()) return false;
		String subName = Utils.getStr("삭제 과목 이름");
		Subject sub = hasSameSub(subName, stu);
		if(sub == null) {
			System.out.println("해당 과목 이름 없습니다.");
			return false;
		}
		int delIdx = findDelIdx(sub);
		removeOneSub(delIdx);
		System.out.println(sub+" 과목 삭제 완료");
		return true;
	}
	
	// 삭제 idx 찾기
	private int findDelIdx(Subject sub) {
		int idx = 0;
		if(subList == null) return -1;
		for(Subject s : subList) {
			if(sub == s) {
				return idx;
			}
			idx +=1;
		}
		return -1;
	}
	
	// 평균
	public double makeAvg(int stuNo) {
		double total = 0;
		int cnt = 0;
		for(Subject sub : subList) {
			if(stuNo == sub.stuNo) {
				total += sub.score;
				cnt +=1;
			}
		}
		if(cnt == 0) return 0.0;
		return total*1.0 / cnt;
		
	}
	
//	// 한 과목 학생 목록
//	void findOneSubStu(StudentDAO stuDAO) {
//		String subName = sc.getStr("찾을 과목 이름");
//		Subject sub = isSub(subName);
//		if(sub == null) {
//			System.out.println("해당 과목은 학생 데이터가 없습니다.");
//			return;
//		}
//		for(Student s : stuDAO.stuList) {
//			if(s.stuNo == sub.stuNo) {
//				stuDAO.printOne(sub);
//			}
//		}
//	}
	
	// 과목 체크
	public void isSub(StudentDAO stuDAO) {
		String subName = Utils.getStr("찾을 과목 이름");
		if(!hasSub(subName)) {
			System.out.println("해당 과목은 학생 데이터가 없습니다.");
			return;
		}
		for(Subject sub : subList) {
			if(subName.equals(sub.subName)) {
			 stuDAO.printOne(sub);
			}
		}
	}
	
	// 과목 있는지 없는지
	private boolean hasSub(String subName) {
		for(Subject s : subList) {
			if(subName.equals(s.subName)) {
				return true;
			}
		}
		return false;
	}
	
}
