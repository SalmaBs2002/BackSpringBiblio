package tn.enicarthage.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import tn.enicarthage.entities.Document;

public interface DocumentRepository extends JpaRepository<Document, Long>{
	
}
