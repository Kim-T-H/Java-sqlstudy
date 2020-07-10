package jdbc;
/*
 * jdbc 
 * 1. java.sql ��Ű���� Ŭ������.
 * 2. mariadb ����̹� �����ϱ�.=> driver �� path�� �����ϱ�
 * 		drivar �ٿ�ޱ�: mariadb.org  =>Download MariaDB Connector/J    2.5.4     MariaDB Connector/J .jar files
 * 						mariadb-java-client-2.5.4.jar  �����ؼ� ��Ŭ������ �ٿ��ֱ�  �����о� ���̺귯�� Ŭ���� �о� jars
 * 3.���ᰴü ����:Connection ��ü
 * 4.statement ��ü ����.=> 
 * 				int  executeUpdate(sql): insert,update,delete,create,drop....
 * 				resultSat		executeQuery(sql): select ����
 * 5.��� ó��
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
		//����̹� Ŭ���� �޸� �ε� 
		Class.forName("org.mariadb.jdbc.Driver");
		//url: jdbc:mariadb://localhost:3306/classdb
		//db username:"scott"
		//db password:"1234"
		Connection conn = DriverManager.getConnection
				("jdbc:mariadb://localhost:3306/classdb","scott","1234");
		System.out.println("Connection ��ü ���� �Ϸ�");
		//Statement ��ü ����: sql ��� ���� ��ü
		Statement stmt = conn.createStatement();
		//Resultset : select ������ ��� ���� ��ü.
		ResultSet rs = stmt.executeQuery("select * from student order by studno");
		while(rs.next()) { 
			System.out.print("�й�:"+rs.getString("studno"));
			System.out.print(",�̸�:"+rs.getString("name"));
			System.out.print(",�г�:"+rs.getString("grade"));
			System.out.println(",�����а�1:"+rs.getString("major1"));
		}

	}

}
