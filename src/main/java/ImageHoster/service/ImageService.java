package ImageHoster.service;

import ImageHoster.model.Comment;
import ImageHoster.model.Image;
import ImageHoster.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
/**
 *
 */
@Service
public class ImageService {
    @Autowired
    private ImageRepository imageRepository;

    //Call the getAllImages() method in the Repository and obtain a List of all the images in the database

    /**
     *
     * @return
     */
    public List<Image> getAllImages() {
        return imageRepository.getAllImages();
    }


    //The method calls the createImage() method in the Repository and passes the image to be persisted in the database

    /**
     *
     * @param image
     */
    public void uploadImage(Image image) {
        imageRepository.uploadImage(image);
    }


    //The method calls the getImageByTitle() method in the Repository and passes the title of the image to be fetched

    /**
     *
     * @param id
     * @return
     */
    public Image getImageByTitle(Integer id) {
        return imageRepository.getImageByTitle(id);
    }

    /**
     *
     * @param id
     * @param comment
     */
    public void saveCommentOfImage(Integer id, Comment comment) {
        LocalDate createdDate = LocalDate.now();
        comment.setCreatedDate(createdDate);
        imageRepository.saveCommentOfImage(id, comment);
    }

    //The method calls the getImage() method in the Repository and passes the id of the image to be fetched

    /**
     *
     * @param imageId
     * @param userId
     * @return
     */
    public Image getImage(Integer imageId, Integer userId) {
        return imageRepository.getImage(imageId, userId);
    }

    //The method calls the updateImage() method in the Repository and passes the Image to be updated in the database

    /**
     *
     * @param updatedImage
     */
    public void updateImage(Image updatedImage) {
        imageRepository.updateImage(updatedImage);
    }

    //The method calls the deleteImage() method in the Repository and passes the Image id of the image to be deleted in the database

    /**
     *
     * @param imageId
     * @param userId
     * @return
     */
    public Image deleteImage(Integer imageId, Integer userId) {
        return imageRepository.deleteImage(imageId, userId);
    }

}
