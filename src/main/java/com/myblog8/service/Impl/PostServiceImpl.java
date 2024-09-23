package com.myblog8.service.Impl;

import com.myblog8.exception.ResourceNotFound;
import com.myblog8.entity.Post;
import com.myblog8.payload.PostDto;
import com.myblog8.payload.PostResponse;
import com.myblog8.repository.PostRepository;
import com.myblog8.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    private PostRepository postRepository;

    private ModelMapper modelMapper;
    
    //constructor base injection insted of Autowired
   public PostServiceImpl(PostRepository postRepository,ModelMapper modelMapper) {

        this.postRepository = postRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public PostDto createPost(PostDto postDto) {

        //copying dto content to entity
        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setDiscription(postDto.getDiscription());
        post.setContent(postDto.getContent());


        Post updatedPost = postRepository.save(post);

        //copying updated Content from entity to dto
        PostDto dto = new PostDto();
        dto.setId(post.getId());
        dto.setTitle(post.getTitle());
        dto.setDiscription(post.getDiscription());
        dto.setContent(post.getContent());

        return dto;
    }

    @Override
    public void deletePostById(int postId) {
        //verifying post exist or not
        Post post = postRepository.findById((long) postId)
                   .orElseThrow(() -> new ResourceNotFound("post not found with id: "+postId));
        postRepository.deleteById((long) postId);
    }

    @Override
    public PostDto getPostByPostId(int postId) {

        Post post =
                postRepository.findById((long) postId)
                .orElseThrow(() -> new ResourceNotFound("post not found with id: "+postId));

        return mapToDto(post);

    }

    @Override
    public PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir) {

        //pagenation concept below three line
        //Sort.by(sortBy):-helps it to convert object to String

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())?Sort.by(sortBy).ascending()
                :Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<Post> listofPageObject = postRepository.findAll(pageable);
        List<Post> posts = listofPageObject.getContent();


        List<PostDto> dtos = posts.stream().map(post -> mapToDto(post)).collect(Collectors.toList());

        PostResponse postResponse =new PostResponse();

        postResponse.setContent(dtos);
        postResponse.setPageNo(listofPageObject.getNumber());
        postResponse.setPageSize(listofPageObject.getSize());
        postResponse.setTotalElements((int) listofPageObject.getTotalElements());
        postResponse.setTotalPages(listofPageObject.getTotalPages());
        postResponse.setLast(listofPageObject.isLast());
        return postResponse;
    }

    PostDto mapToDto(Post post){

        PostDto dto = modelMapper.map(post,PostDto.class);

//       PostDto dto = new PostDto();
//        dto.setId(post.getId());
//        dto.setTitle(post.getTitle());
//        dto.setDiscription(post.getDiscription());
//        dto.setContent(post.getContent());

        return dto;
    }
}
