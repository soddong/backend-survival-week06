package kr.megaptera.jdbc.assignment.daos;

import kr.megaptera.jdbc.assignment.model.Post;
import kr.megaptera.jdbc.assignment.model.PostId;

import java.util.List;

public interface PostDao {
    List<Post> findAll();

    Post find(PostId id);

    void save(Post post);

    void delete(PostId id);
}
