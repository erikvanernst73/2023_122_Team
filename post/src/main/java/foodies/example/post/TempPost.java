package foodies.example.post;




import java.util.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;  


public class TempPost {
    private int id;
    private String description;
    private Date createdDate;

    LocalDate currentDate=java.time.LocalDate.now();
   

    // public Post(int id, String description, Date createdDate) {
    //     this.id = id;
    //     this.description = description;
    //     this.createdDate = createdDate;
    // }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) throws ParseException {
        String sDate1="31/12/1998"; 
        Date date1=new SimpleDateFormat("dd/MM/yyyy").parse(sDate1);
        this.createdDate = date1;
    }

    public void addPost(){
        
        
        
            try  {
                java.sql.Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/mydb", "root", "Fallout2018*");
                String query = "INSERT INTO temppost (id, description) VALUES (?, ?)";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, 1234);//(1(1st column),1234(value))
                preparedStatement.setString(2,"Nice Day");
                // preparedStatement.setString(3, "31/12/1998");
              //  preparedStatement.setDate(3, new java.sql.Date(post.getCreatedDate().getTime()));
             //   preparedStatement.setString(4, post.getPhotos());
                preparedStatement.executeUpdate();
                System.out.println("Query Executed");
                connection.close(); // close connection
                System.out.println("Connection Closed....");
            } catch (SQLException e) {
                e.printStackTrace();
                // Handle any exceptions that may occur during database operations
            }
    
       
       
        
    }

    
}
