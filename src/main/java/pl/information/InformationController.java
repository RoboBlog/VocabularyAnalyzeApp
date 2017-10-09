package pl.information;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/information")
public class InformationController {

    private final InformationService informationService;

    public InformationController(InformationService informationService) {
        this.informationService = informationService;
    }

    //admin
    @GetMapping("/all")
    public List<Information> getAllInformations() {
        List<Information> allInformations = informationService.getAllInformations();
        return allInformations;
    }


    @GetMapping("/allactive")
    public List<Information> getAllActiveInformations() {
        List<Information> allActiveInformations = informationService.getAllActiveInformations();
        return allActiveInformations;
    }

    //admin
    //TODO ADD VALIDATION
    @PostMapping("/add")
    public void addInformation(@RequestBody Information information) {
        informationService.addInformation(information);
    }

    //admin
    @PutMapping("/edit/{informationId}")
    public void updateInformation(@RequestBody Information information, @PathVariable long informationId) {
        informationService.updateInformation(information, informationId);
    }
}
