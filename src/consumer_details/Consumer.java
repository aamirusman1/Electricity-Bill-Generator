package consumer_details;

public class Consumer {
    private int id;
    private String name, area, city, connection_type, password;
	public Consumer() {
		super();
	}
	public Consumer(int id, String name, String area, String city, String connection_type, String password) {
		super();
		this.id = id;
		this.name = name;
		this.area = area;
		this.city = city;
		this.connection_type = connection_type;
		this.password = password;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getConnection_type() {
		return connection_type;
	}
	public void setConnection_type(String connection_type) {
		this.connection_type = connection_type;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
    
	
    

}
