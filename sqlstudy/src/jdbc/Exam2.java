package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/*
 * �̸� temp1 ���̺� �����ϱ�
 * ȭ�鿡�� ���ڿ� ���ڸ� �Է¹޾Ƽ� ���̺� �߰��ϱ�(����:9999)
 * ����ÿ� temp1 ���̺��� ������ ȭ�� ����ϱ�
 */
public class Exam2 {

	public static void main(String[] args) throws Exception {
		Class.forName("org.mariadb.jdbc.Driver");
		Connection conn = DriverManager.getConnection
				("jdbc:mariadb://localhost:3306/classdb","scott","1234");
		Statement stmt= conn.createStatement();
	Scanner scan=new Scanner(System.in); 
	
	while(true) {
		System.out.println("���ڿ� ���ڸ� �Է��ϼ���.(����:9999)");
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
