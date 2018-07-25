package com.tambapps.website.service;

import com.tambapps.website.exception.DBFileNotFoundException;
import com.tambapps.website.exception.DBFileStorageException;
import com.tambapps.website.model.request.DBFile;
import com.tambapps.website.repository.DBFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;

@Service
public class DBFileStorageService {

  private final Path root;
  private final DBFileRepository dbFileRepository;

  public DBFileStorageService(@Qualifier("fileStorageRoot") Path root,
      DBFileRepository dbFileRepository) {
    this.root = root;
    this.dbFileRepository = dbFileRepository;
  }

  public DBFile storeFile(MultipartFile file) {
    // Normalize file name
    String fileName = StringUtils.cleanPath(file.getOriginalFilename());

    try {
      // Check if the file's name contains invalid characters
      if(fileName.contains("..")) {
        throw new DBFileStorageException("Filename contains invalid path sequence " + fileName);
      }

      DBFile dbFile = new DBFile(fileName, file.getContentType(), file.getBytes());

      return dbFileRepository.save(dbFile);
    } catch (IOException ex) {
      throw new DBFileStorageException("Could not store file " + fileName, ex);
    }
  }

  public DBFile getFile(String fileId) {
    return dbFileRepository.findById(fileId)
        .orElseThrow(() -> new DBFileNotFoundException("File not found with id " + fileId));
  }
}
