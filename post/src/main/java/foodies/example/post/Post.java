package foodies.example.post;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Post {
    private int id;
    private String description;
    private Date createdDate;
    //private List<BufferedImage> photos = new ArrayList<>(4);
    private byte[] photo1;
    private byte[] photo2;
    private byte[] photo3;
    private byte[] photo4;

    

    public Post(int id, String description, Date createdDate) {
        this.id = id;
        this.description = description;
        this.createdDate = createdDate;
    }

    public Post() {
       
    }

    public Post(int id, String description) {
        this.id = id;
        this.description = description;
        
    }

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

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    /*public void addPhoto(BufferedImage photo) {
        if (photos.size() < 4) {
            photos.add(photo);
        }
    }

    public List<BufferedImage> getPhotos() {
        return photos;
    }

    public void setPhotos(List<BufferedImage> photos) {
        this.photos = photos;
    }*/

    public byte[] getPhoto1() {
        return photo1;
    }

    public void setPhoto1(byte[] photo1) {
        this.photo1 = photo1;
    }

    public byte[] getPhoto2() {
        return photo2;
    }

    public void setPhoto2(byte[] photo2) {
        this.photo2 = photo2;
    }

    public byte[] getPhoto3() {
        return photo3;
    }

    public void setPhoto3(byte[] photo3) {
        this.photo3 = photo3;
    }

    public byte[] getPhoto4() {
        return photo4;
    }

    public void setPhoto4(byte[] photo4) {
        this.photo4 = photo4;
    }
}
