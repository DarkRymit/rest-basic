package com.epam.esm.web.controllers;

import com.epam.esm.persistence.entity.Tag;
import com.epam.esm.service.TagService;
import com.epam.esm.service.payload.request.TagCreateRequest;
import com.epam.esm.web.dto.Tags;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


@RestController
@RequestMapping(path = "/tags", consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
@RequiredArgsConstructor
public class TagController {
    private final TagService tagService;

    @GetMapping
    public Tags allTags(){
        List<Tag> tags = tagService.findAll();
        return new Tags(tags,tags.size());
    }

    @PostMapping
    public ResponseEntity<Tag> createTag(@Valid @RequestBody TagCreateRequest request){
        Tag tag = tagService.create(request);
        String location = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(tag.getId())
            .toUriString();
        return ResponseEntity.status(HttpStatus.CREATED).header(HttpHeaders.LOCATION, location).body(tag);
    }

    @GetMapping("/{id}")
    public Tag tagById(@PathVariable Long id){
        return tagService.getById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteTag(@PathVariable Long id){
        tagService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
