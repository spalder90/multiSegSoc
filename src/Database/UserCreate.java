package Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

//Clase para el alta de usuarios a la base de datos

public class UserCreate extends Conexion {

	public boolean registrar(User user) {

		PreparedStatement ps = null;
		Connection con = getConexion();

		String sql = "INSERT INTO user (name, surname, rol, email, password) values (?,?,?,?)";

		try {
			ps = con.prepareStatement(sql);
			ps.setString(2, user.getName());
			ps.setString(3, user.getSurname());
			ps.setString(4, "Funcionario");
			ps.setString(5, user.getEmail());
			ps.setString(6, user.getPassword());
			ps.execute();
			return true;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

	}

}
