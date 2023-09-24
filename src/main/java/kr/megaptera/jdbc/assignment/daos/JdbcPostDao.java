package kr.megaptera.jdbc.assignment.daos;

import kr.megaptera.jdbc.assignment.model.MultilineText;
import kr.megaptera.jdbc.assignment.model.Post;
import kr.megaptera.jdbc.assignment.model.PostId;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcPostDao implements PostDao {
    private final JdbcTemplate jdbcTemplate;

    public JdbcPostDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Post> findAll() {
        List<Post> posts = new ArrayList<>();

        String query = "SELECT * FROM posts";

        jdbcTemplate.query(query, resultSet -> {
            while (resultSet.next()){
                Post post = extractPost(resultSet);
                posts.add(post);
            }
        });

        return posts;
    }

    @Override
    public Post find(PostId id) {
        return null;
    }

    @Override
    public void save(Post post) {
        if (post.id() != null) {
            updatePost(post);
            return;
        }

        insertPost(post);
    }

    @Override
    public void delete(PostId id) {

    }

    private Post extractPost(ResultSet resultSet) throws SQLException {
        String postId = resultSet.getString("id");
        String title = resultSet.getString("title");
        String author = resultSet.getString("author");
        String content = resultSet.getString("content");
        System.out.println(postId + title + author + content);
        Post post = new Post(
                PostId.of(postId),
                title, author,
                new MultilineText(content)
        );

        return post;
    }

    private void insertPost(Post post) {
        String query = """
            INSERT INTO posts (id, title, author, content) VALUES (?, ?, ?, ?)
            """;

        jdbcTemplate.update(
                query,
                PostId.generate().toString(),
                post.title(),
                post.author(),
                post.content().toString()
        );
    }

    private void updatePost(Post post) {
        String query = """
            UPDATE posts SET title=?, content=? WHERE id=?
            """;

        jdbcTemplate.update(
                query,
                post.title(),
                post.content().toString(),
                post.id().toString()
        );
    }
}
