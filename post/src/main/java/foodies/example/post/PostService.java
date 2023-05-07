package foodies.example.post;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.sql.*;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;//An import for accesing databases through the REST API
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;



@Service
public class PostService {
    private static final String DB_URL = "jdbc:mysql://localhost:3307/mydb"; // Update with your MySQL database URL
    private static final String DB_USERNAME = "root"; // Update with your MySQL database username
    private static final String DB_PASSWORD = "Fallout2018*"; // Update with your MySQL database password
    


public List<Post> getAllPosts() throws SQLException, IOException {//Creating the Database layer for the GET all posts method in the PostController
    
    Connection connection = null;//Creates the Database connection
    Statement statement = null;//Passing the sql statement to the Database(Select Query)
    ResultSet resultSet = null;//Getting the values in the database

    List<Post> posts = new ArrayList<>();

    try {
        connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        //statement = connection.createStatement();
        statement=connection.createStatement();
        String query = "SELECT * FROM temppost";
        resultSet = statement.executeQuery(query);

        while (resultSet.next()) {
            Post post = new Post();
            post.setId(resultSet.getInt("id")); 
            post.setDescription(resultSet.getString("description"));
            post.setCreatedDate(resultSet.getDate("createdDate"));
            post.setPhoto1(resultSet.getBytes("photo1"));
            post.setPhoto2(resultSet.getBytes("photo2"));
            post.setPhoto3(resultSet.getBytes("photo3"));
            post.setPhoto4(resultSet.getBytes("photo4"));
            
            posts.add(post);
            System.out.println(post.getPhoto1());

        }
    } finally {
        closeResources(connection, statement, resultSet);
    }

    



    return posts;
}
private void closeResources(Connection connection, Statement statement, ResultSet resultSet) throws SQLException {
    if (resultSet != null) {
        resultSet.close();
    }
    if (statement != null) {
        statement.close();
    }
    if (connection != null) {
        connection.close();
    }
}

//Get Posts By ID
@GetMapping(path = "/{postId}")
@CrossOrigin(origins = "http://localhost:3000")
public Post getPostById(@PathVariable("postId") int postId) throws IOException {
    try {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Post post = new Post();


        System.out.println("This is the GET Method");
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            String query = "SELECT * FROM temppost WHERE id=?";
            statement = connection.prepareStatement(query);
            statement.setInt(1, postId);
            resultSet = statement.executeQuery();



            if (resultSet.next()) {
                post.setId(resultSet.getInt("id"));
                post.setDescription(resultSet.getString("description"));
                post.setCreatedDate(resultSet.getDate("createdDate"));
                post.setPhoto1(resultSet.getBytes("photo1"));
                post.setPhoto2(resultSet.getBytes("photo2"));
                post.setPhoto3(resultSet.getBytes("photo3"));
                post.setPhoto4(resultSet.getBytes("photo4"));
                System.out.println(post.getDescription());
            } else {
                System.out.println("Post not found.");
                return null;
            }
        } finally {
            closeResources(connection, statement, resultSet);
        }

        return post;
    } catch (SQLException e) {
        System.err.println("Error getting post: " + e.getMessage());
        return null;
    }
}

// Delete a post
public void deletePost(int postId) throws SQLException {
    Connection conn = null;
    PreparedStatement stmt = null;

    try {
        conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        String sql = "DELETE FROM temppost WHERE id = ?";
        stmt = conn.prepareStatement(sql);
        stmt.setInt(1, postId);
        stmt.executeUpdate();
    } finally {
        closeResources(conn, stmt, null);
    }
}

//Update a Post
public void updatePost(Post post) throws SQLException, IOException {
    Connection connection = null;
    PreparedStatement preparedStatement = null;

    try {
        connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        String query = "UPDATE temppost SET description = ?   WHERE id = ?";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1 , post.getDescription());
        preparedStatement.setInt(2, post.getId());

        preparedStatement.executeUpdate();
    } finally {
        closeResources(connection, preparedStatement, null);
    }
}

//Insert/Create a Post
@PostMapping(path = "/", consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE })
public void createPost(Post post) throws SQLException, IOException {
    
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    
    if (post.getCreatedDate() == null) {
        post.setCreatedDate(new Date(System.currentTimeMillis()));
    }

    

    try {
        connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        String query = "INSERT INTO temppost (id,description,createdDate,photo1,photo2,photo3,photo4) VALUES (?,?,?,?,?,?,?);";
        
        

        preparedStatement = connection.prepareStatement(query);

        
        
        preparedStatement.setInt(1, post.getId()); 
        preparedStatement.setString(2, post.getDescription());
        preparedStatement.setDate(3, new java.sql.Date(post.getCreatedDate().getTime()));
        preparedStatement.setBytes(4, post.getPhoto1());
        preparedStatement.setBytes(5, post.getPhoto2()); 
        preparedStatement.setBytes(6, post.getPhoto3());
        preparedStatement.setBytes(7, post.getPhoto4());


        preparedStatement.executeUpdate();
    } finally {
        closeResources(connection, preparedStatement, null);
    }
}

}
