package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/*
 * sql 구문을 입력받아 결과 출력하기
 */
public class Exam4 {

	public static void main(String[] args) throws Exception {
		Class.forName("org.mariadb.jdbc.Driver");
		Connection conn = DriverManager.getConnection
				("jdbc:mariadb://localhost:3306/classdb","scott","1234");
	Scanner scan= new Scanner(System.in);
	String sql=null;
	while(true) {
		try {
			System.out.println("sql 구문을 입력하세요(종료:exit)");
			sql= scan.nextLine();
			if (sql.equals("exit")) break;
			System.out.println();
		PreparedStatement pstmt2 = conn.prepareStatement("select count(*) from ("+sql+") a");
		ResultSet rs2 =pstmt2.executeQuery();
		rs2.next();	//레코드 선택
		int cnt= rs2.getInt(1); //rs.getInt(1): 컬럼의 값을 정수형   rs.getString:컬럼의 값을 문자열형
		System.out.print("조회 레코드수:"+cnt);
		PreparedStatement pstmt = conn.prepareStatement(sql);
		//rs : 화면에 출력할 레코드 저장
		ResultSet rs =pstmt.executeQuery();
		ResultSetMetaData rsmd = rs.getMetaData();
		System.out.println(",\t+조회 컬럼수:"+rsmd.getColumnCount());
		for(int i=1; i<=rsmd.getColumnCount(); i++) {
			System.out.print(rsmd.getColumnName(i)+"\t");
		}
		System.out.println("\n=====================================");
		while(rs.next()) {
			for (int i=1; i<=rsmd.getColumnCount(); i++) {
				System.out.print(rs.getString(i)+"\t");
			}
			System.out.println();
		}
			
	}catch(SQLException e) {
		System.out.println("구문에서 오류가 발생했습니다.");
		System.out.println("sql:"+sql);
	}
	
System.out.println("프로그램 종료");
	}

	}
}
