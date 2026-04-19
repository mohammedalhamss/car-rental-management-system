import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Mangement {

	public Connection getConnected() throws SQLException {
		return DriverManager.getConnection("jdbc:mysql://localhost:3306/rent", "root", "your_password_here\"");

	}

	public ArrayList<Information> getCars() throws SQLException {

		ArrayList<Information> car = new ArrayList<Information>();
		Statement st = getConnected().createStatement();
		ResultSet rs = st.executeQuery("select* from cars");

		while (rs.next()) {
			Information info = new Information();
			info.setCar_id(rs.getInt(1));
			info.setCar_brand(rs.getString(2));
			info.setModel(rs.getString(3));
			info.setYear(rs.getInt(4));
			info.setStstus(rs.getString(5));
			car.add(info);

		}

		return car;

	}

	public ArrayList<Details> getDetails() throws SQLException {

		ArrayList<Details> data = new ArrayList<Details>();
		Statement st = getConnected().createStatement();
		ResultSet rs = st.executeQuery("select* from rented");

		while (rs.next()) {
			Details detail = new Details();
			detail.setCar_id(rs.getInt(1));
			detail.setStart_date(rs.getString(2));
			detail.setDuration(rs.getString(3));
			detail.setCost(rs.getInt(4));
			data.add(detail);

		}

		return data;

	}

	public void rentCar(int carId, String startDate, String duration, int totalCost) throws SQLException {
		Connection con = getConnected();
		String sql = "INSERT INTO rented (car_id, start_date, duration, cost) VALUES (?, ?, ?, ?)";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, carId);
		ps.setString(2, startDate);
		ps.setString(3, duration);
		ps.setInt(4, totalCost);
		ps.executeUpdate();

		PreparedStatement ps2 = con.prepareStatement("UPDATE cars SET status='Rented' WHERE car_id=?");
		ps2.setInt(1, carId);
		ps2.executeUpdate();
	}

	public boolean logCheck(String username, String password) {
		boolean isValid = false;
		try {
			Connection con = getConnected();
			String sql = "SELECT * FROM user WHERE username = ? AND password = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, password);

			ResultSet rs = ps.executeQuery();
			isValid = rs.next();
			rs.close();
			ps.close();
			con.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return isValid;

	}

	public void add_new_car(String brand, String mode_l, int year_) throws SQLException {
		Connection con = getConnected();
		String sql = "INSERT INTO cars ( car_brand,model,year,status) VALUES (?, ?, ?, ?)";
		PreparedStatement ps = con.prepareStatement(sql);

		ps.setString(1, brand);
		ps.setString(2, mode_l);
		ps.setInt(3, year_);
		ps.setString(4, "available");
		ps.executeUpdate();
	}

}
