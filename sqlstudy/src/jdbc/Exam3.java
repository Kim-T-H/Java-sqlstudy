package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

/*
 * dept 테이블의 모든 정보를 출력하기
 */
public class Exam3 {

	public static void main(String[] args) throws Exception {
		Class.forName("org.mariadb.jdbc.Driver");
		Connection conn = DriverManager.getConnection
				("jdbc:mariadb://localhost:3306/classdb","scott","1234");
		String sql="select * from dept";
		PreparedStatement pstmt= conn.prepareStatement(sql);
		ResultSet rs =pstmt.executeQuery();
		ResultSetMetaData rsmd = rs.getMetaData();
		for(int i=1; i<=rsmd.getColumnCount();i++) {
			System.out.print(rsmd.getColumnName(i)+"\t");
		}
		System.out.println();

	}

}
