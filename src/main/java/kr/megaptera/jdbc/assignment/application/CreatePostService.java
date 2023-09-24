package kr.megaptera.jdbc.assignment.application;

import kr.megaptera.jdbc.assignment.daos.JdbcPostDao;
import kr.megaptera.jdbc.assignment.dtos.PostCreateDto;
import kr.megaptera.jdbc.assignment.model.MultilineText;
import kr.megaptera.jdbc.assignment.model.Post;
import org.springframework.stereotype.Service;

@Service
public class CreatePostService {
    private final JdbcPostDao postDao;

    public CreatePostService(JdbcPostDao postDao) {
        this.postDao = postDao;
    }

    public void createPost(PostCreateDto postCreateDto) {
        Post post = new Post(
                postCreateDto.getTitle(),
                postCreateDto.getAuthor(),
                MultilineText.of(postCreateDto.getContent())
        );

        postDao.save(post);
    }
}