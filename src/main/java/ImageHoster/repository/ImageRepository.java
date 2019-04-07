package ImageHoster.repository;

import ImageHoster.model.Comment;
import ImageHoster.model.Image;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.List;

//The annotation is a special type of @Component annotation which describes that the class defines a data repository

/**
 *
 */
@Repository
public class ImageRepository {

    //Get an instance of EntityManagerFactory from persistence unit with name as 'imageHoster'
    @PersistenceUnit(unitName = "imageHoster")
    private EntityManagerFactory emf;


    //The method receives the Image object to be persisted in the database
    //Creates an instance of EntityManager
    //Starts a transaction
    //The transaction is committed if it is successful
    //The transaction is rolled back in case of unsuccessful transaction

    /**
     *
     * @param newImage
     * @return
     */
    public Image uploadImage(Image newImage) {

        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            em.persist(newImage);
            em.flush();
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }
        return newImage;
    }

    //The method creates an instance of EntityManager
    //Executes JPQL query to fetch all the images from the database
    //Returns the list of all the images fetched from the database

    /**
     *
     * @return
     */
    public List<Image> getAllImages() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Image> query = em.createQuery("SELECT i from Image i", Image.class);
        List<Image> resultList = query.getResultList();

        return resultList;
    }

    //The method creates an instance of EntityManager
    //Executes JPQL query to fetch the image from the database with corresponding title
    //Returns the image in case the image is found in the database
    //Returns null if no image is found in the database

    /**
     *
     * @param id
     * @return
     */
    public Image getImageByTitle(Integer id) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Image> typedQuery = em.createQuery("SELECT i from Image i where i.id=:id ", Image.class);
            TypedQuery<Comment> typedQuery1 = em.createQuery("SELECT c from Comment  c where c.image.id=:id", Comment.class);
            typedQuery.setParameter("id", id);
            typedQuery1.setParameter("id", id);
            List<Comment> commentsOnImage = typedQuery1.getResultList();
            Image image = typedQuery.getSingleResult();
            image.setComments(commentsOnImage);
            return image;
        } catch (NoResultException nre) {
            return null;  //return null object basically
        }
    }

    //The method creates an instance of EntityManager
    //Executes JPQL query to fetch the image from the database with corresponding id
    //Returns the image fetched from the database

    /**
     *
     * @param imageId
     * @param userId
     * @return
     */
    public Image getImage(Integer imageId, Integer userId) {
        try {
            EntityManager em = emf.createEntityManager();
            TypedQuery<Image> typedQuery = em.createQuery("SELECT i from Image i where i.id =:imageId and i.user.id=:userId", Image.class);
            typedQuery.setParameter("imageId", imageId);
            typedQuery.setParameter("userId", userId);

            Image image = typedQuery.getSingleResult();
            return image;
        } catch (NoResultException nre) {
            return null;  //return null object basically
        }
    }

    //The method receives the Image object to be updated in the database
    //Creates an instance of EntityManager
    //Starts a transaction
    //The transaction is committed if it is successful
    //The transaction is rolled back in case of unsuccessful transaction

    /**
     *
     * @param updatedImage
     */
    public void updateImage(Image updatedImage) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            em.merge(updatedImage);
            em.flush();
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }
    }

    //The method receives the Image id of the image to be deleted in the database
    //Creates an instance of EntityManager
    //Starts a transaction
    //Get the image with corresponding image id from the database
    //This changes the state of the image model from detached state to persistent state, which is very essential to use the remove() method
    //If you use remove() method on the object which is not in persistent state, an exception is thrown
    //The transaction is committed if it is successful
    //The transaction is rolled back in case of unsuccessful transaction

    /**
     *
     * @param imageId
     * @param userId
     * @return
     */
    public Image deleteImage(Integer imageId, Integer userId) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            TypedQuery<Image> query = em.createQuery("select i from Image i where i.id=:imageId and i.user.id=:userId", Image.class);
            query.setParameter("userId", userId);
            query.setParameter("imageId", imageId);
            Image image = (Image) query.getSingleResult();
            em.remove(image);
            em.flush();
            transaction.commit();
            return image;
        } catch (Exception e) {
            transaction.rollback();
            return null;
        }

    }

    /**
     *
     * @param id
     * @param comment
     */
    public void saveCommentOfImage(Integer id, Comment comment) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            TypedQuery<Image> query = em.createQuery("select i from Image i where i.id=:id", Image.class);
            query.setParameter("id", id);
            Image image = query.getSingleResult();
            comment.setImage(image);
            em.persist(comment);
            em.flush();
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }

    }

}
