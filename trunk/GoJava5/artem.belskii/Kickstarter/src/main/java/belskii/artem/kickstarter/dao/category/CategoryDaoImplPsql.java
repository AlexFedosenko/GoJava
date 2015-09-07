package belskii.artem.kickstarter.dao.category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import belskii.artem.kickstarter.dao.DBConnector;

public class CategoryDaoImplPsql implements CategoryDao {

	private Connection connection;

	public CategoryDaoImplPsql(String dbConnectionConfig) {
		connection = new DBConnector(dbConnectionConfig).getConnection();
	};

	@Override
	public void addCategory(String categoryInfo) {
		String query = "INSERT INTO CATEGORYES (TITLE) VALUES (?)";
		try (PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setString(1, categoryInfo);
			statement.execute();
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Map<Integer, String> getCategoryList() {
		Map<Integer, String> categiryList = new HashMap<Integer, String>();
		String query = "SELECT TITLE FROM CATEGORYES";
		try (PreparedStatement statement = connection.prepareStatement(query)) {
			ResultSet rs = statement.executeQuery();
			int i = 0;
			while (rs.next()) {
				categiryList.put(i, rs.getString("TITLE").toString());
				i++;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return categiryList;
	}

	@Override
	public String getCategoryNameById(int id) {
		String answer = "";
		String query = "SELECT TITLE FROM CATEGORYES WHERE ID=?";
		try (PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setInt(1, id);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				answer = rs.getString("TITLE").toString();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return answer;
	}

}
