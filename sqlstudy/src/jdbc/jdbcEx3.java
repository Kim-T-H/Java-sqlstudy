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
 * 미리 temp1 테이블 생성하기
 * 화면에서 숫자와 문자를 입력받아서 테이블에 추가하기(종료:9999)
 * 종료시에 temp1 테이블의 내용을 화면 출력하기
 * 
 * preparedStatement 사용하기(권장) => 1.Statement의 하위 인터페이스. statement 객체는 db서버에 sql문장을 전달 가능.
 * 							2. 미리 sql 문장 전달하고, 실행 전에 ? 파라미터값을 전달하여 실행.
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
		System.out.println("숫자와 문자를 입력하세요.(종료:9999)");
		int no =scan.nextInt();
		if(no==9999) break;
		String text =scan.next();
		pstmt.setInt(1,  no);	//첫번째?
		pstmt.setString(2, text); //두번째?
		int result = pstmt.executeUpdate();
		
	}
	
	//ResultSetMetaData 사용하기
	//ResultSet : select 구문 실행 후 결과값 저장
	//ResultSetMetaData : 결과값의 정보 저장하고 있는 객체
	ResultSet rs = pstmt.executeQuery("select * from temp1 order by no");
	ResultSetMetaData rsmd =rs.getMetaData();		//결과값의 정보리턴
	//rsmd.getColumnCount() : 조회된 컬럼의 갯수
	//rsmd.getColumnName(1) : 조회된 첫번째 컬럼의 이름.
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