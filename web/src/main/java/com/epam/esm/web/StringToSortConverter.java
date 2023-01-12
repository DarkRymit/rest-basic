package com.epam.esm.web;

import com.epam.esm.persistence.dao.Sort;
import com.epam.esm.persistence.dao.Sort.Direction;
import com.epam.esm.persistence.dao.Sort.Order;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

@Slf4j
public class StringToSortConverter implements Converter<String, Sort> {

  public static final String DELIMITER = ",";

  @Override
  public Sort convert(String source) {
    log.debug("Start convert String to Sort");
    if (!StringUtils.hasText(source)) {
      return new Sort(List.of());
    }
    return new Sort(List.of(processOrderString(source)));
  }

  private Order processOrderString(String order) {
    List<String> parsed = Arrays.stream(order.split(DELIMITER)).limit(2)
        .collect(Collectors.toList());
    if (parsed.size() == 1) {
      return new Order(parsed.get(0), Direction.ASC);
    } else {
      return new Order(parsed.get(0), Direction.valueOf(parsed.get(1)));
    }
  }
}