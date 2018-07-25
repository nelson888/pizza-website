package com.tambapps.website.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "file")
@Data
@RequiredArgsConstructor
public class DBFile {
  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name = "uuid", strategy = "uuid2")
  private String id;

  @NonNull
  private String fileName;
  @NonNull
  private String fileType;

  @Lob
  @NonNull
  private byte[] data;

  public DBFile() { }

}
