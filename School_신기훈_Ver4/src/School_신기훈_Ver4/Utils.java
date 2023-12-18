package School_신기훈_Ver4;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Utils {
	private static Scanner sc =  new Scanner(System.in);
	private static String CUR_PATH = System.getProperty("user.dir")+"\\src\\"+new Utils().getClass().getPackageName()+"\\";
	
	
	private static Utils instance = new Utils();
	
	public static Utils getInstance() {
		return instance;
	}
	
	// student 파일 저장 -- >
	public static void saveStuFile(StudentDAO stuDAO) {
		String fileName = "student.txt";
		try(FileWriter fw = new FileWriter(CUR_PATH+fileName)){
			String data = "";
			for(Student s : stuDAO.stuList) {
				data += s.stuNo+"/"+s.stuName+"/"+s.stuId+"\n";
			}
			fw.write(data);
			System.out.println(fileName+ " 저장 성공");
		} catch (IOException e) {
			System.out.println("파일 저장 실패");
		}
	}
	
	// subject파일 저장
	public static void saveSubFile(SubjectDAO subDAO) {
		String fileName = "subject.txt";
		try(FileWriter fw = new FileWriter(CUR_PATH+fileName)){
			String data = "";
			for(Subject s : subDAO.subList) {
				data += s.stuNo+"/"+s.subName+"/"+s.score+"\n";
			}
			fw.write(data);
			System.out.println(fileName+"  저장 성공");
		} catch (IOException e) {
			System.out.println("파일 저장 실패");
		}
		
	}
	
	
	
	
	
	// ------- 파일로드 ->
	
	public static String stuDataRoad() {
		String roadStuData = roadData("student.txt");
		return roadStuData;
	}
	
	public static String subDataRoad() {
		String roadSubData = roadData("subject.txt");
		return roadSubData;
	}
	
	public static void fileRoad() {
		System.out.println(stuDataRoad());
		System.out.println(subDataRoad());
	}
	
	private static String roadData(String fileName) {
		try(FileReader fr = new FileReader(CUR_PATH+fileName);
			BufferedReader br = new BufferedReader(fr);	){
			String data = "";
			while(true) {
				String line = br.readLine();
				if(line == null) break;
				data += line+"\n";
			}
			//System.out.println(data);
			//System.out.println(fileName+" 파일 로드 성공");
			return data;
		} catch (IOException e) {
			System.out.println("파일 로드 실패");
		}
			return null;
	}
	
	//------------------------------------------- 파일로드 끝
	
	public static int getInt(String msg, int start, int end) {
		while(true) {
			System.out.printf("▶ %s [%d~%d]입력 : %n", msg, start, end);
			try {
				int sel = sc.nextInt();
				sc.nextLine();
				if(sel < start || sel > end) {
					System.out.printf("[%d~%d]범위 입력 %n", start, end);
					continue;
				}
				return sel;
			} catch(Exception e) {
				System.out.println("숫자만 입력하세요");
				sc.nextLine();
			}
		}
	}
	
	public static String getStr(String msg) {
		System.out.printf("▶ %s 입력 : %n" , msg);
		String input = sc.next();
		return input;
	}
	
}
