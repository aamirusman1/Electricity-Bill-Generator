package admin_details;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Scanner;

import bill_details.Bill;
import jdbc.MyConnection;

public class Admin  {
	
	 private static HashMap<String, String> adminDetails = new HashMap<String, String>();

	static {
		adminDetails.put("admin", "admin123");
	}
	

	public static boolean login(String userName, String password) {
		if (adminDetails.containsKey(userName)) {
			if (adminDetails.get(userName).equals(password)) 
				return true;
			 else 
				return false;			
		}
		else return false;
	}
			
		

	public static void addUnitsConsumed() throws ClassNotFoundException, SQLException {
		Scanner sc = new Scanner(System.in);
		Connection con = MyConnection.getConnection();
		String sqlInsert = "insert into Bill(consumerId,unitsconsumed,year,month) values(?,?,?,?)";
		PreparedStatement pst = con.prepareStatement(sqlInsert);
		
		System.out.print("Enter the consumerID of consumer : ");
		pst.setInt(1, sc.nextInt());
		
		System.out.print("Enter units consumed by consumer : ");
		pst.setInt(2, sc.nextInt());
		
		System.out.print("Enter the year : ");
		pst.setInt(3, sc.nextInt());
		
		sc.nextLine();
	
		System.out.print("Enter the month : ");
		pst.setString(4, sc.next());

		pst.executeUpdate();
		System.out.println("Successfully Inserted!!");
		pst.clearParameters();
		con.close();
	}


	//bill of specific month and year of a particular consumer
	public static Bill getBillByYearAndMonth(int id,int year, String month) throws SQLException, ClassNotFoundException{
		Connection con = MyConnection.getConnection();	
		PreparedStatement pst = con.prepareStatement("Select * from Bill where consumerId = ? and year = ? and month = ?");
        pst.setInt(1, id);
        pst.setInt(2, year);
        pst.setString(3, month);
        ResultSet rs = pst.executeQuery();
        if (rs.next() == false)
            return null;
        return new Bill(rs.getInt("billId"),rs.getInt("consumerId"), rs.getInt("unitsConsumed"), rs.getInt("year"),rs.getString("month"),rs.getInt("totalAmount"));
		
	}
	
	//all bills of all months and year for which a particular consumer has consumed electricity
    public static void getAllBillsByYearAndMonth(int id)throws SQLException, ClassNotFoundException{
    	Connection con = MyConnection.getConnection();	
		PreparedStatement pst = con.prepareStatement("Select * from Bill where consumerId = ?");
        pst.setInt(1, id);
        ResultSet rs = pst.executeQuery();
        System.out.println("billId "+ " consumerId "+" unitsConsumed "+" year "+" month "+" totalAmount ");
        while(rs.next()) {
        	System.out.println(rs.getInt(1)+" , "+rs.getInt(2)+" , "+rs.getInt(3)+" , "+rs.getInt(4)+" , "+rs.getString(5)+" , "+rs.getInt(6));
        }
		pst.clearParameters();
		con.close();
	}
    
    //Bill as per city and area 
    public static void getAllBillsByCityAndArea()throws SQLException, ClassNotFoundException{
    	Connection con = MyConnection.getConnection();		
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery("select consumer.city, consumer.area, consumer.consumerId, Bill.year, Bill.month, Bill.unitsconsumed from consumer join Bill on consumer.consumerId = Bill.consumerId");
		System.out.println("city "+ " area "+ " consumerId "+" year "+" month "+" unitsConsumed ");
		while(rs.next()) {
			System.out.println(rs.getString(1)+" , "+rs.getString(2)+" , "+ rs.getInt(3)+" , "+rs.getInt(4)+" , "+ rs.getString(5)+" , "+rs.getInt(6));
		}

		con.close();
    }

}
