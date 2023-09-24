package kr.megaptera.jdbc.assignment.contoller;

import kr.megaptera.jdbc.assignment.application.CreatePostService;
import kr.megaptera.jdbc.assignment.application.GetPostsService;
import kr.megaptera.jdbc.assignment.dtos.PostCreateDto;
import kr.megaptera.jdbc.assignment.dtos.PostDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/posts")
public class PostController {
    private final GetPostsService getPostsService;
    private final CreatePostService createPostService;

    public PostController(GetPostsService getPostsService,
                          CreatePostService createPostService) {
        this.getPostsService = getPostsService;
        this.createPostService = createPostService;
    }

    @GetMapping
    public List<PostDto> list(){
        List<PostDto> postDtos = getPostsService.getPostDtos();
        return postDtos;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody PostCreateDto postCreateDto){
        createPostService.createPost(postCreateDto);

    }
}
