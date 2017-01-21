package com.projectx.cwm.resources;

import com.projectx.cwm.models.Response;
import com.projectx.cwm.models.UserLoginDetails;
import com.projectx.cwm.services.DocumentService;
import com.projectx.cwm.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.Map;

/**
 * Created by sl0 on 1/21/17.
 */
@RestController
@RequestMapping("/api/documents/")
public class Documents {
    final
    DocumentService documentService;

    @Autowired
    public Documents(DocumentService documentService) {
        this.documentService = documentService;
    }

    @PreAuthorize("hasAnyAuthority('ADMIN,MANAGER,CONTRIBUTOR,USER')")
    @RequestMapping(method = RequestMethod.POST, headers = "content-type=multipart/*")
    public ResponseEntity<?> upload(MultipartHttpServletRequest request,
                                    @RequestParam("name") String name,
                                    @RequestParam("keyword") String keyword,
                                    @RequestParam("abstract") String abstract_){
        UserLoginDetails loggedUser = Utils.getUserDetails();
        Map<String, MultipartFile> map = request.getFileMap();
        for (Map.Entry<String, MultipartFile> entry : map.entrySet()) {
            try {
                documentService.saveDocument(entry.getValue(), name, keyword, abstract_, loggedUser);

            } catch (Exception ex) {
                ex.printStackTrace();
                return new ResponseEntity(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return new ResponseEntity<>(new Response(true), HttpStatus.CREATED);

    }
}
