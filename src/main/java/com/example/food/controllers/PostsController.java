package com.example.food.controllers;

import com.example.food.db.entities.Account;
import com.example.food.db.entities.Post;
import com.example.food.db.entities.PostComment;
import com.example.food.db.repositories.PostCommentRepo;
import com.example.food.db.repositories.PostRepo;
import com.example.food.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(path = {"/posts"})
public class PostsController {
    private final PostRepo postRepo;
    private final PostCommentRepo commentRepo;
    private final UserService userService;

    @Autowired
    public PostsController(PostRepo postRepo, PostCommentRepo commentRepo, UserService userService) {
        this.postRepo = postRepo;
        this.commentRepo = commentRepo;
        this.userService = userService;
    }

    @GetMapping
    public String getPosts(ModelMap modelMap, HttpServletRequest request) {
        modelMap.put("posts", postRepo.findAll());
        return "PostsIndex";
    }

    @GetMapping(path = "/{id}")
    public String getPostById(@PathVariable("id") int postId, ModelMap modelMap, HttpServletRequest request) {
        Post post = postRepo.findById(postId).orElse(Post.builder().content("No such post!").title("No such post!").build());
        modelMap.put("post", post);
        return "PostsId";
    }

    @GetMapping(path = "/{id}/edit")
    public String getPostEditPage(@PathVariable("id") int postId,
                                  HttpServletRequest request,
                                  ModelMap modelMap) {
        Optional<Post> post = postRepo.findById(postId);
        Optional<Account> account = userService.getCurrentUser(request);

        Optional<Account> postAuthor = post.map(Post::getAuthor);
        boolean canEdit = postAuthor.equals(account);

        if (!canEdit) {
            return "redirect:/posts?error='you cannot edit this post'";
        }
        modelMap.put("post", post.get());
        return "PostsIdEdit";
    }

    @DeleteMapping(path = "/{id}")
    public String deletePost(@PathVariable("id") int postId, HttpServletRequest request) {
        Optional<Post> post = postRepo.findById(postId);
        Optional<Account> account = userService.getCurrentUser(request);

        Optional<Account> postAuthor = post.map(Post::getAuthor);
        Optional<Boolean> canDelete = postAuthor.map(a -> a.equals(account.orElse(null)));

        if (!canDelete.orElse(false)) {
            return "redirect:/posts?error='you cannot delete this post'";
        }
        postRepo.delete(post.get());
        return "PostIdEdit";
    }

    @PostMapping(path = "/{id}")
    public String editPost(Post editedPost, @PathVariable("id") int postId, HttpServletRequest request) {
        Optional<Post> post = postRepo.findById(postId);
        Optional<Account> account = userService.getCurrentUser(request);

        Optional<Account> postAuthor = post.map(Post::getAuthor);
        Optional<Boolean> canEdit = postAuthor.map(a -> a.equals(account.orElse(null)));

        if (!canEdit.orElse(false)) {
            return "redirect:/posts?error='you cannot edit this post'";
        }

        post.get().setContent(editedPost.getContent());
        post.get().setTitle(editedPost.getTitle());
        postRepo.save(post.get());

        return "redirect:/posts/" + post.get().getId();
    }

    @GetMapping(path = "/new")
    public String getPostsForm(ModelMap modelMap, HttpServletRequest request) {
        return "PostsNew";
    }

    @PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String createPost(@RequestParam("title") String title,
                             @RequestParam("content") String content,
                             HttpServletRequest request) {
        Optional<Account> account = userService.getCurrentUser(request);

        assert account.isPresent();

        Post post = Post.builder()
                .title(title)
                .content(content)
                .author(account.get())
                .build();

        postRepo.save(post);
        return "redirect:/posts/" + post.getId();
    }

    @ResponseBody
    @GetMapping(path = "{id}/comments", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PostComment> getComments(@PathVariable("id") int postId) {
        Optional<Post> post = postRepo.findById(postId);
        return post.map(Post::getComments).orElse(Collections.emptyList());
    }

    @PostMapping(path = "{id}/comments")
    public ResponseEntity<PostComment> createComment(@PathVariable("id") int postId,
                                                     @RequestParam("text") String content,
                                                     HttpServletRequest request) {
        Post post = postRepo.findById(postId).get();
        Optional<Account> account = userService.getCurrentUser(request);

        PostComment comment = PostComment.builder()
                .content(content)
                .author(account.get())
                .post(post)
                .build();
        post.getComments().add(comment);

        commentRepo.save(comment);
        postRepo.save(post);

        return ResponseEntity.ok(comment);
    }
}
