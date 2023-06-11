import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONArray;
import org.json.JSONObject;

@WebServlet("/")
public class EmployeeServlet extends HttpServlet {
    
    private static final long serialVersionUID = 1L;
    
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        try {
            //enter your database connection sting here replace postgres with your database user and password with your password
            String url = "jdbc:postgresql://localhost/myDB";
            String user = "postgres";
            String password = "root";
            Connection conn = DriverManager.getConnection(url, user, password);
            
            String insertQuery = "INSERT INTO employee (name,email,phone) VALUES(?,?,?)";
            PreparedStatement insertStatement = conn.prepareStatement(insertQuery);
            String name = req.getParameter("name");
            String email = req.getParameter("email");
            String phone = req.getParameter("phone");
            insertStatement.setString(1, name);
            insertStatement.setString(2, email);
            insertStatement.setString(3, phone);
            insertStatement.executeUpdate();
            
            String selectQuery = "SELECT * FROM employee";
            PreparedStatement selectStatement = conn.prepareStatement(selectQuery);
            ResultSet resultSet = selectStatement.executeQuery();
            
            JSONArray jsonArray = new JSONArray();
            while (resultSet.next()) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("id", resultSet.getInt("id"));
                jsonObject.put("name", resultSet.getString("name"));
                jsonObject.put("email", resultSet.getString("email"));
                jsonObject.put("phone", resultSet.getString("phone"));
                jsonArray.put(jsonObject);
            }
            
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(jsonArray.toString());
        } catch (SQLException e) {
            res.status(401).send("server error");
            e.printStackTrace();
        }
    }
    
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        try {
            //enter your database connection sting here replace postgres with your database user and password with your password
            String url = "jdbc:postgresql://localhost/myDB";
            String user = "postgres";
            String password = "root";
            Connection conn = DriverManager.getConnection(url, user, password);
            
            String selectQuery = "SELECT * FROM employee";
            PreparedStatement selectStatement = conn.prepareStatement(selectQuery);
            ResultSet resultSet = selectStatement.executeQuery();
            
            JSONArray jsonArray = new JSONArray();
            while (resultSet.next()) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("id", resultSet.getInt("id"));
                jsonObject.put("name", resultSet.getString("name"));
                jsonObject.put("email", resultSet.getString("email"));
                jsonObject.put("phone", resultSet.getString("phone"));
                jsonArray.put(jsonObject);
            }
            
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(jsonArray.toString());
        } catch (SQLException e) {
            res.status(401).send("server error");
            e.printStackTrace();
        }
    }
}
