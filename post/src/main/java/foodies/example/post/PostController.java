package foodies.example.post;


import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

// import java.util.HashMap;
// import java.util.Map;

@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private PostService postService;

    // POST request to create a new Post
    @PostMapping
    @CrossOrigin(origins = "http://localhost:3000")
    public void createPost(@RequestParam("id") Integer id,
    @RequestParam("description") String description,
    @RequestParam("photo1") MultipartFile photo1,
    @RequestParam("photo2") MultipartFile photo2,
    @RequestParam("photo3") MultipartFile photo3,
    @RequestParam("photo4") MultipartFile photo4){
        try {

            Post post = new Post();
            post.setId(id);
            post.setDescription(description);
            post.setPhoto1(photo1.getBytes());
            post.setPhoto2(photo2.getBytes());
            post.setPhoto3(photo3.getBytes());
            post.setPhoto4(photo4.getBytes());
            postService.createPost(post);
            System.out.println("Post Inserted successfully!");
        } catch (SQLException | IOException e) {
            System.err.println("Error Inserting post: " + e.getMessage());
        }
        
    }

    // GET request to get a post using ID
    @GetMapping("/{id}")
    @CrossOrigin(origins = "http://localhost:3000")
    public Post getPostById(@PathVariable int id) throws IOException {
       
             Post post1=postService.getPostById(id);
             System.out.println("Getting Post by ID");
             return post1;
    }

    // GET request to get all Posts
    @GetMapping
    @CrossOrigin(origins = "http://localhost:3000")
    public List<Post> getAllPosts() {
        try {
            List<Post> posts = postService.getAllPosts();
            if (!posts.isEmpty()) {
                return posts;
            } else {
                System.out.println("No posts found.");
                return null;
            }
        } catch (SQLException | IOException e) {
            System.err.println("Error getting posts: " + e.getMessage());
            return null;
        }
    }

    // DELETE request to delete a Post
    @DeleteMapping("/{id}")
    @CrossOrigin(origins = "http://localhost:3000")
    public void deletePost(@PathVariable int id) {
       
        try {
            postService.deletePost(id);
            System.out.println("Post deleted successfully!");
        } catch (SQLException e) {
            System.err.println("Error deleting post: " + e.getMessage());
        }
    }

    // PUT request to update a Post
    @PutMapping("/{id}")
    @CrossOrigin(origins = "http://localhost:3000")
    public void updatePost(@PathVariable int id, @RequestBody Post post) {
        
        try {
            post.setId(id);
            postService.updatePost(post);
            System.out.println("Post updated successfully!");
        } catch (SQLException | IOException e) {
            System.err.println("Error updating post: " + e.getMessage());
        }
        
    }
    
        
    
}