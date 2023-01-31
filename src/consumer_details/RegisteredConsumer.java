package consumer_details;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bill_details.Bill;
import jdbc.MyConnection;

public class RegisteredConsumer extends Consumer {
	
	public static Consumer login(int id, String password) throws ClassNotFoundException, SQLException{
		Connection con = MyConnection.getConnection();	
		PreparedStatement pst = con.prepareStatement("Select * from consumer where consumerId = ? and consumer_password = ?");
        pst.setInt(1, id);
        pst.setString(2, password);
        ResultSet rs = pst.executeQuery();
        if (rs.next() == false)
            return null;
        return new Consumer(rs.getInt("consumerId"), rs.getString("consumerName"), rs.getString("area"), rs.getString("city"),
                rs.getString("connectionType"), rs.getString("consumer_password"));
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
	}
	
	

}
