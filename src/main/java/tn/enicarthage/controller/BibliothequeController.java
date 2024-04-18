package tn.enicarthage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.enicarthage.entities.Bibliotheque;
import tn.enicarthage.entities.Document;
import tn.enicarthage.services.BibliothequeService;




@RestController
@RequestMapping("/api/v1/bibliotheque")
public class BibliothequeController {

    @Autowired
    private BibliothequeService bibliothequeService;

    @PostMapping
    public ResponseEntity<Bibliotheque> createBibliotheque(@RequestBody Bibliotheque bibliotheque) {
        Bibliotheque createdBibliotheque = bibliothequeService.createBibliotheque(bibliotheque);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdBibliotheque);
    }

    @GetMapping
    public ResponseEntity<Bibliotheque> getBibliotheque() {
        Bibliotheque bibliotheque = bibliothequeService.getBibliotheque();
        if (bibliotheque != null) {
            return ResponseEntity.ok(bibliotheque);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBibliotheque(@PathVariable Long id) {
        bibliothequeService.deleteBibliotheque(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{bibliothequeId}/documents")
    public ResponseEntity<Document> addDocumentToBibliotheque(@PathVariable Long bibliothequeId,
                                                              @RequestBody Document document) {
        Document createdDocument = bibliothequeService.addDocumentToBibliotheque(bibliothequeId, document);
        if (createdDocument != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(createdDocument);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{bibliothequeId}/documents/{documentId}")
    public ResponseEntity<Void> deleteDocumentFromBibliotheque(@PathVariable Long bibliothequeId,
                                                                @PathVariable Long documentId) {
        bibliothequeService.deleteDocumentFromBibliotheque(bibliothequeId, documentId);
        return ResponseEntity.noContent().build();
    }
}
