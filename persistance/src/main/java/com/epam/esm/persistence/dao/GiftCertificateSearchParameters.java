package com.epam.esm.persistence.dao;

import com.epam.esm.persistence.entity.GiftCertificate;
import lombok.Value;

/**
 * Data class for store search parameters
 *
 * @author Tamerlan Hurbanov
 * @see GiftCertificate
 * @since 1.0
 */
@Value
public class GiftCertificateSearchParameters {
  String tagName;
  String part;
  Sort sort;

  public boolean isTagNamePresent() {
    return tagName != null;
  }
  public boolean isPartPresent() {
    return part != null;
  }

  public boolean isSortPresent() {
    return sort != null && !sort.isEmpty();
  }

}
