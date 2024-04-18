package tn.enicarthage.services;

import tn.enicarthage.entities.Bibliotheque;
import tn.enicarthage.entities.Document;


public interface BibliothequeService {
	Bibliotheque createBibliotheque(Bibliotheque bibliotheque);
    Bibliotheque getBibliotheque();
    void deleteBibliotheque(Long id);
    Document addDocumentToBibliotheque(Long bibliothequeId, Document document);
    void deleteDocumentFromBibliotheque(Long bibliothequeId, Long documentId);
    Bibliotheque getBibliothequeById(Long id);
}
