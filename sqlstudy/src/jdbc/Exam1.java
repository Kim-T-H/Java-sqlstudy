package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*
 * ���� ���̺��� �о ������ȣ,�����̸�,�а��ڵ�, �޿�, ���ʽ� ����ϱ�
 */
public class Exam1 {

	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		Class.forName("org.mariadb.jdbc.Driver");
		Connection conn=DriverManager.getConnection("jdbc:mariadb://localhost:3306/classdb","scott","1234"); //�����ϴ� �κ�
		
		Statement stmt = conn.createStatement();
		ResultSet rs =stmt.executeQuery("select no,name,deptno,salary,bonus from professor order by no");
		while(rs.next()) { 
			System.out.print("������ȣ:"+rs.getString(1));
			System.out.print(",�����̸�:"+rs.getString(2));
			System.out.print(",�а��ڵ�:"+rs.getString(3));
			System.out.print(",�޿�:"+rs.getString(4));
			System.out.println(",���ʽ�:"+rs.getString(5));
		}

	}

}
