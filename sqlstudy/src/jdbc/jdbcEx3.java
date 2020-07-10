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
 * �̸� temp1 ���̺� �����ϱ�
 * ȭ�鿡�� ���ڿ� ���ڸ� �Է¹޾Ƽ� ���̺� �߰��ϱ�(����:9999)
 * ����ÿ� temp1 ���̺��� ������ ȭ�� ����ϱ�
 * 
 * preparedStatement ����ϱ�(����) => 1.Statement�� ���� �������̽�. statement ��ü�� db������ sql������ ���� ����.
 * 							2. �̸� sql ���� �����ϰ�, ���� ���� ? �Ķ���Ͱ��� �����Ͽ� ����.
 */
public class jdbcEx3 {

	public static void main(String[] args) throws Exception {
		Class.forName("org.mariadb.jdbc.Driver");
		Connection conn = DriverManager.getConnection
				("jdbc:mariadb://localhost:3306/classdb","scott","1234");
		String sql="insert into temp1 values(?,?)";
		PreparedStatement pstmt= conn.prepareStatement(sql);
	Scanner scan=new Scanner(System.in); 
	
	while(true) {
		System.out.println("���ڿ� ���ڸ� �Է��ϼ���.(����:9999)");
		int no =scan.nextInt();
		if(no==9999) break;
		String text =scan.next();
		pstmt.setInt(1,  no);	//ù��°?
		pstmt.setString(2, text); //�ι�°?
		int result = pstmt.executeUpdate();
		
	}
	
	//ResultSetMetaData ����ϱ�
	//ResultSet : select ���� ���� �� ����� ����
	//ResultSetMetaData : ������� ���� �����ϰ� �ִ� ��ü
	ResultSet rs = pstmt.executeQuery("select * from temp1 order by no");
	ResultSetMetaData rsmd =rs.getMetaData();		//������� ��������
	//rsmd.getColumnCount() : ��ȸ�� �÷��� ����
	//rsmd.getColumnName(1) : ��ȸ�� ù��° �÷��� �̸�.
	for(int i=1; i<=rsmd.getColumnCount();i++) {
		System.out.print(rsmd.getColumnName(i)+"\t");
	}
	System.out.println("\n=======================");
	while(rs.next()) {
		for(int i=1; i<=rsmd.getColumnCount();i++) {
		System.out.print(rs.getString(rsmd.getColumnName(i))+"\t");
		}
		System.out.println();
	}
	}

}