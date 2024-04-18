package tn.enicarthage.services;

import java.util.List;

import tn.enicarthage.entities.Document;

public interface DocumentService {
	    List<Document> getAllDocuments(Long bibliothequeId);
	    Document getDocumentById(Long bibliothequeId, Long documentId);
	    Document addDocumentToBibliotheque(Long bibliothequeId, Document document);
	    Document updateDocument(Long bibliothequeId, Long documentId, Document document);
	    void deleteDocument(Long bibliothequeId, Long documentId);
}
