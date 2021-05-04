package ar.unrn.tp3.bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import ar.unrn.tp3.modelo.Participante;
import ar.unrn.tp3.modelo.PersistenciaParticipantes;
import ar.unrn.tp3.modelo.Telefono;

public class JdbcParticipante implements PersistenciaParticipantes {
	private Connection dbConn;
	private String url = "jdbc:mysql://localhost:3306/tplayersoo2";
	private String user = "root";
	private String password = "";

	private void setupBaseDeDatos() throws SQLException {
		this.dbConn = DriverManager.getConnection(url, user, password);
	}

	public void altaParticipante(Participante participante) {
		PreparedStatement st = null;
		try {
			this.setupBaseDeDatos();
			this.dbConn.setAutoCommit(false);
			st = dbConn.prepareStatement("insert into participante(nombre,region,idtelefono)values(?,?,?)");
			st.setString(1, participante.nombre());
			st.setString(2, participante.region());
			st.setInt(3, altaTelefono(participante.telefono()));
			st.executeUpdate();
			this.dbConn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	private int altaTelefono(Telefono telefono) throws SQLException {
		int idTelefono = -1;
		ResultSet res = null;
		PreparedStatement st = dbConn.prepareStatement("insert into telefono(numero)values(?)",
				Statement.RETURN_GENERATED_KEYS);
		try {
			st.setString(1, telefono.numero());
			st.executeUpdate();
			res = st.getGeneratedKeys();
			res.next();
			idTelefono = res.getInt(1);
		} finally {
			st.close();
			res.close();
		}
		return idTelefono;
	}
}
