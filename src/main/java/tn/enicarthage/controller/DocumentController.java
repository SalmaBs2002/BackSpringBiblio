package tn.enicarthage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.enicarthage.entities.Document;
import tn.enicarthage.services.DocumentService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/documents")
public class DocumentController {

    @Autowired
    private DocumentService documentService;

    @GetMapping("/{bibliothequeId}")
    public ResponseEntity<List<Document>> getAllDocumentsByBibliothequeId(@PathVariable Long bibliothequeId) {
        List<Document> documents = documentService.getAllDocuments(bibliothequeId);
        return ResponseEntity.ok(documents);
    }

    @GetMapping("/{bibliothequeId}/{documentId}")
    public ResponseEntity<Document> getDocumentById(@PathVariable Long bibliothequeId, @PathVariable Long documentId) {
        Document document = documentService.getDocumentById(bibliothequeId, documentId);
        if (document != null) {
            return ResponseEntity.ok(document);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{bibliothequeId}")
    public ResponseEntity<Document> addDocumentToBibliotheque(@PathVariable Long bibliothequeId, @RequestBody Document document) {
        Document createdDocument = documentService.addDocumentToBibliotheque(bibliothequeId, document);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdDocument);
    }

    @PutMapping("/{bibliothequeId}/{documentId}")
    public ResponseEntity<Document> updateDocument(@PathVariable Long bibliothequeId, @PathVariable Long documentId, @RequestBody Document document) {
        Document updatedDocument = documentService.updateDocument(bibliothequeId, documentId, document);
        if (updatedDocument != null) {
            return ResponseEntity.ok(updatedDocument);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{bibliothequeId}/{documentId}")
    public ResponseEntity<Void> deleteDocument(@PathVariable Long bibliothequeId, @PathVariable Long documentId) {
        documentService.deleteDocument(bibliothequeId, documentId);
        return ResponseEntity.noContent().build();
    }
}
