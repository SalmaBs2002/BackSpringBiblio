package tn.enicarthage.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.enicarthage.entities.Bibliotheque;
import tn.enicarthage.entities.Document;
import tn.enicarthage.repository.DocumentRepository;
import java.util.List;

@Service
public class DocumentServiceImpl implements DocumentService {

    @Autowired
    private DocumentRepository documentRepository;

    @Autowired
    private BibliothequeService bibliothequeService;

    @Override
    public List<Document> getAllDocuments(Long bibliothequeId) {
        Bibliotheque bibliotheque = bibliothequeService.getBibliothequeById(bibliothequeId);
        if (bibliotheque != null) {
            return bibliotheque.getDocuments();
        }
        return null;
    }

    @Override
    public Document getDocumentById(Long bibliothequeId, Long documentId) {
        Bibliotheque bibliotheque = bibliothequeService.getBibliothequeById(bibliothequeId);
        if (bibliotheque != null) {
            return bibliotheque.getDocuments().stream()
                    .filter(document -> document.getId().equals(documentId))
                    .findFirst()
                    .orElse(null);
        }
        return null;
    }

    @Override
    public Document addDocumentToBibliotheque(Long bibliothequeId, Document document) {
        Bibliotheque bibliotheque = bibliothequeService.getBibliothequeById(bibliothequeId);
        if (bibliotheque != null) {
            document.setBibliotheque(bibliotheque);
            return documentRepository.save(document);
        }
        return null;
    }

    @Override
    public Document updateDocument(Long bibliothequeId, Long documentId, Document document) {
        Bibliotheque bibliotheque = bibliothequeService.getBibliothequeById(bibliothequeId);
        if (bibliotheque != null) {
            Document existingDocument = getDocumentById(bibliothequeId, documentId);
            if (existingDocument != null) {
                // Update existing document properties
                existingDocument.setNom(document.getNom());
                existingDocument.setDescription(document.getDescription());
                existingDocument.setLien(document.getLien());
                // Save and return the updated document
                return documentRepository.save(existingDocument);
            }
        }
        return null;
    }

    @Override
    public void deleteDocument(Long bibliothequeId, Long documentId) {
        documentRepository.deleteById(documentId);
    }
}
