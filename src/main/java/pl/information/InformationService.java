package pl.information;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InformationService {
    private final InformationRepository informationRepository;

    public InformationService(InformationRepository informationRepository) {
        this.informationRepository = informationRepository;
    }

    //admin
    public List<Information> getAllInformations() {
        List<Information> allInformations = informationRepository.findAll();
        return allInformations;
    }

    public List<Information> getAllActiveInformations() {
        List<Information> allActiveInformations = informationRepository.findAllByIsActiveTrue();
        return allActiveInformations;
    }

    //admin
    public void addInformation(Information information, String name) {
        information.setActive(true);
        information.setAuthor(name);
        informationRepository.save(information);
    }

    //admin
    public void updateInformation(Information information, long informationId) {
        Information byId = informationRepository.getById(informationId);
        information.setId(byId.getId());
        informationRepository.save(information);
    }

    public void deactivateInformation(Information information) {
        information.setActive(false);
        informationRepository.save(information);
    }

    public void activeInformation(Information information) {
        information.setActive(true);
    }
}
