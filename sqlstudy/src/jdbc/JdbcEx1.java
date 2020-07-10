package jdbc;
/*
 * jdbc 
 * 1. java.sql 패키지의 클래스들.
 * 2. mariadb 드라이버 선택하기.=> driver 를 path에 설정하기
 * 		drivar 다운받기: mariadb.org  =>Download MariaDB Connector/J    2.5.4     MariaDB Connector/J .jar files
 * 						mariadb-java-client-2.5.4.jar  복사해서 이클립스에 붙여넣기  빌드패쓰 라이브러리 클래스 패쓰 jars
 * 3.연결객체 생성:Connection 객체
 * 4.statement 객체 생성.=> 
 * 				int  executeUpdate(sql): insert,update,delete,create,drop....
 * 				resultSat		executeQuery(sql): select 구문
 * 5.결과 처리
 * 
 * 
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcEx1 {

	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		//드라이버 클래스 메모리 로드 
		Class.forName("org.mariadb.jdbc.Driver");
		//url: jdbc:mariadb://localhost:3306/classdb
		//db username:"scott"
		//db password:"1234"
		Connection conn = DriverManager.getConnection
				("jdbc:mariadb://localhost:3306/classdb","scott","1234");
		System.out.println("Connection 객체 생성 완료");
		//Statement 객체 생성: sql 명령 전달 객체
		Statement stmt = conn.createStatement();
		//Resultset : select 구문의 결과 저장 객체.
		ResultSet rs = stmt.executeQuery("select * from student order by studno");
		while(rs.next()) { 
			System.out.print("학번:"+rs.getString("studno"));
			System.out.print(",이름:"+rs.getString("name"));
			System.out.print(",학년:"+rs.getString("grade"));
			System.out.println(",전공학과1:"+rs.getString("major1"));
		}

	}

}
