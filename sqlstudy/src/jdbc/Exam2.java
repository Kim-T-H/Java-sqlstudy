package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/*
 * 미리 temp1 테이블 생성하기
 * 화면에서 숫자와 문자를 입력받아서 테이블에 추가하기(종료:9999)
 * 종료시에 temp1 테이블의 내용을 화면 출력하기
 */
public class Exam2 {

	public static void main(String[] args) throws Exception {
		Class.forName("org.mariadb.jdbc.Driver");
		Connection conn = DriverManager.getConnection
				("jdbc:mariadb://localhost:3306/classdb","scott","1234");
		Statement stmt= conn.createStatement();
	Scanner scan=new Scanner(System.in); 
	
	while(true) {
		System.out.println("숫자와 문자를 입력하세요.(종료:9999)");
		int no =scan.nextInt();
		if(no==9999) break;
		String text =scan.next();
		String sql = "insert into temp1 values("+no+",'"+text+"')";
		int result = stmt.executeUpdate(sql);
		
	}
	ResultSet rs = stmt.executeQuery("select * from temp1 order by no");
	while(rs.next()) {
		System.out.print("no:"+ rs.getString(1));
		System.out.println(",text:"+rs.getString(2));
	}
	}

}
