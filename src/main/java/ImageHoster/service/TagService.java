package ImageHoster.service;

import ImageHoster.model.Tag;
import ImageHoster.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class TagService {
    @Autowired
    private TagRepository tagRepository;

    /**
     *
     * @param title
     * @return
     */
    public Tag getTagByName(String title) {
        return tagRepository.findTag(title);
    }

    /**
     *
     * @param tag
     * @return
     */
    public Tag createTag(Tag tag) {
        return tagRepository.createTag(tag);
    }
}
