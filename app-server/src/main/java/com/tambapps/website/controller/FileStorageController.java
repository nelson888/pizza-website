package com.tambapps.website.controller;

import com.tambapps.website.model.payload.FileUploadResponse;
import com.tambapps.website.model.request.DBFile;
import com.tambapps.website.service.DBFileStorageService;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class FileStorageController {

  private final DBFileStorageService fileStorageService;

  public FileStorageController(DBFileStorageService fileStorageService) {
    this.fileStorageService = fileStorageService;
  }

  @PostMapping("/uploadFile")
  public FileUploadResponse uploadFile(@RequestParam("file") MultipartFile file) {
    DBFile dbFile = fileStorageService.storeFile(file);

    String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
        .path("/downloadFile/")
        .path(dbFile.getId())
        .toUriString();

    return new FileUploadResponse(dbFile.getFileName(), fileDownloadUri,
        file.getContentType(), file.getSize());
  }

  @PostMapping("/uploadMultipleFiles")
  public List<FileUploadResponse> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
    return Arrays.stream(files)
        .map(this::uploadFile)
        .collect(Collectors.toList());
  }

  @GetMapping("/downloadFile/{fileId}")
  public ResponseEntity<Resource> downloadFile(@PathVariable String fileId) {
    DBFile dbFile = fileStorageService.getFile(fileId);

    return ResponseEntity.ok()
        .contentType(MediaType.parseMediaType(dbFile.getFileType()))
        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + dbFile.getFileName() + "\"")
        .body(new ByteArrayResource(dbFile.getData()));
  }

}
