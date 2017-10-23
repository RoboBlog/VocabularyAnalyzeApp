package pl.information;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InformationService {
    private final InformationRepository informationRepository;

    public InformationService(InformationRepository informationRepository) {
        this.informationRepository = informationRepository;
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public List<Information> getAllInformations() {
        List<Information> allInformations = informationRepository.findAll();
        return allInformations;
    }

    public List<Information> getAllActiveInformations() {
        List<Information> allActiveInformations = informationRepository.findAllByIsActiveTrue();
        return allActiveInformations;
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public void addInformation(Information information, String name) {
        information.setActive(true);
        information.setAuthor(name);
        informationRepository.save(information);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public void updateInformation(Information information, long informationId) {
        Information byId = informationRepository.getById(informationId);
        information.setId(byId.getId());
        informationRepository.save(information);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public void deactivateInformation(Information information) {
        information.setActive(false);
        informationRepository.save(information);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public void activeInformation(Information information) {
        information.setActive(true);
    }
}
