package SzymonKozlowski.Atipera.RecruitmentTask.controllers;

import SzymonKozlowski.Atipera.RecruitmentTask.Dtos.RepositoryDto;
import SzymonKozlowski.Atipera.RecruitmentTask.services.RecruitmentTaskService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/repositories", produces = MediaType.APPLICATION_JSON_VALUE)
public class RecruitmentTaskController {

    private final RecruitmentTaskService recruitmentTaskService;

    public RecruitmentTaskController(RecruitmentTaskService recruitmentTaskService) {
        this.recruitmentTaskService = recruitmentTaskService;
    }

    @GetMapping("/{username}")
    public List<RepositoryDto> getUserRepositories(@PathVariable String username) {
        return recruitmentTaskService.getUserRepositories(username);
    }
}
