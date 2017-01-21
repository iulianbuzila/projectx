package com.projectx.cwm.repositories;

import com.projectx.cwm.domain.Document;
import com.projectx.cwm.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.print.Doc;

/**
 * Created by sl0 on 1/21/17.
 */
@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {
    public Document findByName(String name);
}
