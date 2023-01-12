package com.epam.esm.service.impl;

import com.epam.esm.persistence.dao.TagRepository;
import com.epam.esm.persistence.entity.Tag;
import com.epam.esm.service.TagService;
import com.epam.esm.service.exceptions.NoSuchTagException;
import com.epam.esm.service.payload.request.TagCreateRequest;
import java.util.List;
import java.util.function.Supplier;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {

  private final TagRepository tagRepository;

  private static Supplier<NoSuchTagException> getNoSuchTagException(Long id) {
    return () -> new NoSuchTagException(id);
  }

  @Override
  public List<Tag> findAll() {
    return tagRepository.findAllAsList();
  }

  @Override
  public Tag getById(long id) {
    return tagRepository.findById(id).orElseThrow(getNoSuchTagException(id));
  }

  @Override
  @Transactional
  public void deleteById(long id) {
    Tag tag = tagRepository.findById(id).orElseThrow(getNoSuchTagException(id));
    tagRepository.delete(tag);
  }

  @Override
  @Transactional
  public Tag create(TagCreateRequest request) {
    Tag tag = new Tag(request.getName());
    return tagRepository.save(tag);
  }
}
