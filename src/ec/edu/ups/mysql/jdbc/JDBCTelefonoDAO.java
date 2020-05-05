/**
 * 
 */
package ec.edu.ups.mysql.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import ec.edu.ups.dao.TelefonoDAO;
import ec.edu.ups.modelo.Telefono;


/**
 * @author Orlando Real
 *
 */
public class JDBCTelefonoDAO extends JDBCGenericDAO<Telefono, String> implements TelefonoDAO{

	@Override
	public void createTable() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void create(Telefono telefono) {
		// TODO Auto-generated method stub
		conexionDos.update("INSERT Telefono VALUES (" + product.getId() + ", " + product.getAmount() + ", '"
				+ telefono.getDescription() + "', " + product.getShoppingBasket().getId() + ")");
	}

	@Override
	public void update(Telefono telefono) {
		// TODO Auto-generated method stub
		conexionUno.update("UPDATE Telefono SET amount = " + product.getAmount() + ", description = '"
				+ telefono.getDescription() + "' WHERE id = " + telefono.getId());
	}

	@Override
	public void delete(Telefono telefono) {
		// TODO Auto-generated method stub
		conexionUno.update("DELETE FROM Telefono WHERE id = " + telefono.getId());
	}

	@Override
	public List<Telefono> find() {
		// TODO Auto-generated method stub
		List<Telefono> list = new ArrayList<Telefono>();
		ResultSet rsTelefono = conexionUno.query("SELECT * FROM Product");
		try {
			while (rsTelefono.next()) {
				Telefono telefono = new Telefono(rsTelefono.getInt("id"), rsTelefono.getInt("amount"),
						rsTelefono.getString("description"));
				ResultSet rsShoppingBasket = conexionDos
						.query("SELECT * FROM Shopping_Basket WHERE id=" + rsTelefono.getInt("shopping_basket_id"));

				if (rsShoppingBasket != null && rsShoppingBasket.next()) {
					Calendar calendar = new GregorianCalendar();
					calendar.setTime(rsShoppingBasket.getDate("date"));
					ShoppingBasket shoppingBasket = new ShoppingBasket(rsShoppingBasket.getInt("id"), calendar);
					product.setShoppingBasket(shoppingBasket);
				}
				list.add(telefono);
			}

		} catch (SQLException e) {
			System.out.println(">>>WARNING (JDBCProductDAO:find): " + e.getMessage());
		}

		return list;
	}


	@Override
	public Set<Telefono> findByUserId(String cedulaUser) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Telefono read(String userID) {
		// TODO Auto-generated method stub
		Telefono telefono = null;
		ResultSet rsTelefono = conexionUno.query("SELECT * FROM Telefono WHERE id=" + userID);
		try {
			if (rsTelefono != null && rsTelefono.next()) {
				telefono = new Telefono(rsTelefono.getInt("userID"), rsTelefono.getInt("amount"), rsTelefono.getString("description"));
				ResultSet rsShoppingBasket = conexionDos
						.query("SELECT * FROM Shopping_Basket WHERE id=" + rsTelefono.getInt("shopping_basket_id"));

			}
		} catch (SQLException e) {
			System.out.println(">>>WARNING (JDBCTelefonoDAO:read): " + e.getMessage());
		}
		if (telefono == null) {
			return null;
		}
		return telefono;
	}

	@Override
	public Set<Telefono> findByEmailId(String correo) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
