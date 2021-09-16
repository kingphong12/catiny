package com.regitiny.catiny.service.criteria;

import com.regitiny.catiny.GeneratedByJHipster;
import java.io.Serializable;
import java.util.Objects;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.BooleanFilter;
import tech.jhipster.service.filter.DoubleFilter;
import tech.jhipster.service.filter.Filter;
import tech.jhipster.service.filter.FloatFilter;
import tech.jhipster.service.filter.IntegerFilter;
import tech.jhipster.service.filter.LongFilter;
import tech.jhipster.service.filter.StringFilter;
import tech.jhipster.service.filter.UUIDFilter;

/**
 * Criteria class for the {@link com.regitiny.catiny.domain.Album} entity. This class is used
 * in {@link com.regitiny.catiny.web.rest.AlbumResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /albums?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@GeneratedByJHipster
public class AlbumCriteria implements Serializable, Criteria {

  private static final long serialVersionUID = 1L;

  private LongFilter id;

  private UUIDFilter uuid;

  private StringFilter name;

  private StringFilter note;

  private LongFilter infoId;

  private LongFilter imageId;

  private Boolean distinct;

  public AlbumCriteria() {}

  public AlbumCriteria(AlbumCriteria other) {
    this.id = other.id == null ? null : other.id.copy();
    this.uuid = other.uuid == null ? null : other.uuid.copy();
    this.name = other.name == null ? null : other.name.copy();
    this.note = other.note == null ? null : other.note.copy();
    this.infoId = other.infoId == null ? null : other.infoId.copy();
    this.imageId = other.imageId == null ? null : other.imageId.copy();
    this.distinct = other.distinct;
  }

  @Override
  public AlbumCriteria copy() {
    return new AlbumCriteria(this);
  }

  public LongFilter getId() {
    return id;
  }

  public LongFilter id() {
    if (id == null) {
      id = new LongFilter();
    }
    return id;
  }

  public void setId(LongFilter id) {
    this.id = id;
  }

  public UUIDFilter getUuid() {
    return uuid;
  }

  public UUIDFilter uuid() {
    if (uuid == null) {
      uuid = new UUIDFilter();
    }
    return uuid;
  }

  public void setUuid(UUIDFilter uuid) {
    this.uuid = uuid;
  }

  public StringFilter getName() {
    return name;
  }

  public StringFilter name() {
    if (name == null) {
      name = new StringFilter();
    }
    return name;
  }

  public void setName(StringFilter name) {
    this.name = name;
  }

  public StringFilter getNote() {
    return note;
  }

  public StringFilter note() {
    if (note == null) {
      note = new StringFilter();
    }
    return note;
  }

  public void setNote(StringFilter note) {
    this.note = note;
  }

  public LongFilter getInfoId() {
    return infoId;
  }

  public LongFilter infoId() {
    if (infoId == null) {
      infoId = new LongFilter();
    }
    return infoId;
  }

  public void setInfoId(LongFilter infoId) {
    this.infoId = infoId;
  }

  public LongFilter getImageId() {
    return imageId;
  }

  public LongFilter imageId() {
    if (imageId == null) {
      imageId = new LongFilter();
    }
    return imageId;
  }

  public void setImageId(LongFilter imageId) {
    this.imageId = imageId;
  }

  public Boolean getDistinct() {
    return distinct;
  }

  public void setDistinct(Boolean distinct) {
    this.distinct = distinct;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    final AlbumCriteria that = (AlbumCriteria) o;
    return (
      Objects.equals(id, that.id) &&
      Objects.equals(uuid, that.uuid) &&
      Objects.equals(name, that.name) &&
      Objects.equals(note, that.note) &&
      Objects.equals(infoId, that.infoId) &&
      Objects.equals(imageId, that.imageId) &&
      Objects.equals(distinct, that.distinct)
    );
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, uuid, name, note, infoId, imageId, distinct);
  }

  // prettier-ignore
    @Override
    public String toString() {
        return "AlbumCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (uuid != null ? "uuid=" + uuid + ", " : "") +
            (name != null ? "name=" + name + ", " : "") +
            (note != null ? "note=" + note + ", " : "") +
            (infoId != null ? "infoId=" + infoId + ", " : "") +
            (imageId != null ? "imageId=" + imageId + ", " : "") +
            (distinct != null ? "distinct=" + distinct + ", " : "") +
            "}";
    }
}
