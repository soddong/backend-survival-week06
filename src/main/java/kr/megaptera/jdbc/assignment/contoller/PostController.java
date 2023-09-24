package kr.megaptera.jdbc.assignment.contoller;

import kr.megaptera.jdbc.assignment.application.GetPostsService;
import kr.megaptera.jdbc.assignment.dtos.PostDto;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/posts")
public class PostController {
    private final GetPostsService getPostsService;

    public PostController(GetPostsService getPostsService) {
        this.getPostsService = getPostsService;
    }

    @GetMapping
    public List<PostDto> list(){
        List<PostDto> postDtos = getPostsService.getPostDtos();
        return postDtos;
    }
}
