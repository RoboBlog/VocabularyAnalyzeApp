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
        List<Information> allInformations = informationRepository.getAll();
        return allInformations;
    }

    public List<Information> getAllActiveInformations() {
        List<Information> allActiveInformations = informationRepository.getAllByActiveTrue();
        return allActiveInformations;
    }

    //admin
    public void addInformation(Information information) {
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
        //save?
    }

    public void activeInformation(Information information) {
        information.setActive(true);
    }
}
