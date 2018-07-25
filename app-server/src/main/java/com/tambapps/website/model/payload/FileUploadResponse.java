package com.tambapps.website.model.payload;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FileUploadResponse {

  private String fileName;
  private String fileDownloadUri;
  private String fileType;
  private long size;

}
