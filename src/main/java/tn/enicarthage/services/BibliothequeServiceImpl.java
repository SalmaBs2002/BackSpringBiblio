package tn.enicarthage.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.enicarthage.entities.Bibliotheque;
import tn.enicarthage.entities.Document;
import tn.enicarthage.repository.BibliothequeRepository;
import tn.enicarthage.repository.DocumentRepository;

import java.util.List;

@Service
public class BibliothequeServiceImpl implements BibliothequeService {

    @Autowired
    private BibliothequeRepository bibliothequeRepository;

    @Autowired
    private DocumentRepository documentRepository;

    @Override
    public Bibliotheque createBibliotheque(Bibliotheque bibliotheque) {
        return bibliothequeRepository.save(bibliotheque);
    }

    @Override
    public Bibliotheque getBibliotheque() {
        // Assume there's only one bibliotheque for now
        return bibliothequeRepository.findAll().get(0);
    }

    @Override
    public void deleteBibliotheque(Long id) {
        bibliothequeRepository.deleteById(id);
    }

    @Override
    public Document addDocumentToBibliotheque(Long bibliothequeId, Document document) {
        Bibliotheque bibliotheque = bibliothequeRepository.findById(bibliothequeId).orElse(null);
        if (bibliotheque != null) {
            document.setBibliotheque(bibliotheque);
            // Correction de l'appel à la méthode save
            return documentRepository.save(document);
        } else {
            return null;
        }
    }
    @Override
    public Bibliotheque getBibliothequeById(Long id) {
        return bibliothequeRepository.findById(id).orElse(null);
    }


    @Override
    public void deleteDocumentFromBibliotheque(Long bibliothequeId, Long documentId) {
        documentRepository.deleteById(documentId);
    }
}
