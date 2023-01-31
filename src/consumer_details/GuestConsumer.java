package consumer_details;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jdbc.MyConnection;

public class GuestConsumer extends Consumer {
	public static int register(String name, String city, String area,String connection_type, String password)throws ClassNotFoundException, SQLException{
		Connection con = MyConnection.getConnection();
        String sqlInsert="insert into consumer(consumername,area,city,connectiontype,consumer_password ) value(?,?,?,?,?)";
		PreparedStatement pst = con.prepareStatement(sqlInsert);
		pst.setString(1,name);
		pst.setString(2,area);
        pst.setString(3,city);
        pst.setString(4,connection_type);
        pst.setString(5,password);
        int rowAffected = pst.executeUpdate();
        return rowAffected;
	}
	public static int getConsumerId(String name, String password) throws SQLException, ClassNotFoundException {
        Connection con = MyConnection.getConnection();
        PreparedStatement pst = con.prepareStatement("Select * from consumer where consumerName=? and consumer_password=?");
        pst.setString(1, name);
        pst.setString(2, password);
        ResultSet rs = pst.executeQuery();
        if(rs.next()==false) {
        	return -1;
        }else return rs.getInt("consumerId");
    }

}
