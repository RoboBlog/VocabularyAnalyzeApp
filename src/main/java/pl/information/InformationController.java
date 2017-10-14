package pl.information;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api/information")
public class InformationController {

    private final InformationService informationService;

    public InformationController(InformationService informationService) {
        this.informationService = informationService;
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/all")
    public List<Information> getAllInformations(Authentication authentication) {
        System.out.println(authentication.getAuthorities());
        List<Information> allInformations = informationService.getAllInformations();
        return allInformations;
    }


    @GetMapping("/allactive")
    public List<Information> getAllActiveInformations() {
        List<Information> allActiveInformations = informationService.getAllActiveInformations();
        return allActiveInformations;
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("/add")
    public void addInformation(@RequestBody @Valid Information information, Authentication authentication) {
        String name = authentication.getName();
        informationService.addInformation(information, name);
    }

    //TODO put vs post
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PutMapping("/edit/{informationId}")
    public void updateInformation(@RequestBody Information information, @PathVariable long informationId) {
        informationService.updateInformation(information, informationId);
    }
}
