package com.projectx.cwm.services;

import com.projectx.cwm.domain.Document;
import com.projectx.cwm.domain.User;
import com.projectx.cwm.exceptions.ResourceAlreadyExists;
import com.projectx.cwm.exceptions.ResourceNotFound;
import com.projectx.cwm.exceptions.UserNotAllowed;
import com.projectx.cwm.models.UserLoginDetails;
import com.projectx.cwm.repositories.DocumentRepository;
import com.projectx.cwm.repositories.LogRepository;
import com.projectx.cwm.repositories.UserRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDateTime;

/**
 * Created by sl0 on 1/21/17.
 */
@Service
public class DocumentService {
    final UserRepository userRepository;
    final LogRepository logRepository;
    final DocumentRepository documentRepository;
    private final Logger logger = Logger.getLogger(GroupService.class);

    @Autowired
    public DocumentService(UserRepository userRepository, LogRepository logRepository, DocumentRepository documentRepository) {
        this.userRepository = userRepository;
        this.logRepository = logRepository;
        this.documentRepository = documentRepository;
    }

    public void saveDocument(MultipartFile doc, String name, String keyword, String abstract_, UserLoginDetails loggedUser) {
        User user = userRepository.findByUsername(loggedUser.getUsername());
        Document document = documentRepository.findByName(name);
        if (document != null){
            throw new ResourceAlreadyExists("Document with same name already exists.");
        }
        document = new Document();
        document.setCreator(user);
        document.setName(name);
        document.setKeyword(keyword);
        document.setAbstract_(abstract_);
        document.setDateOfCreation(LocalDateTime.now());
        document.setDateOfLastUpdate(LocalDateTime.now());
        document.setVersion("1.0");
        String path = saveToFile(doc, document);
        document.setPath(path);
        documentRepository.save(document);
        documentRepository.flush();
    }

    private String saveToFile(MultipartFile doc, Document document) {
        String path = Paths.get("/src/main/resources/uploaded").toAbsolutePath().toString();
        String fileName = document.getName() + "_" + document.getVersion();
        path = System.getProperty("user.dir") + "/" + path + "/" + fileName;
        File f = new File(path);
        System.out.println("Working Directory = " +
                System.getProperty("user.dir"));
        try {
            f.createNewFile();
            doc.transferTo(f);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return path;
    }

    public void deleteDocument(Long documentId, UserLoginDetails loggedUser) {
        Document document = documentRepository.findOne(documentId);
        if (document == null){
            throw new ResourceNotFound("Document not found!");
        }

        User user = userRepository.findByUsername(loggedUser.getUsername());
        if (!user.getCreatedDocuments().contains(document)){
            throw new UserNotAllowed("User not allowed to delete document.");
        }

        File docFile = new File(document.getPath());
        boolean succ = docFile.delete();

        documentRepository.delete(document);
        documentRepository.flush();
    }
}
