package SzymonKozlowski.Atipera.RecruitmentTask.controllers;

import SzymonKozlowski.Atipera.RecruitmentTask.Dtos.RepositoryDto;
import SzymonKozlowski.Atipera.RecruitmentTask.services.GithubService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/repositories", produces = MediaType.APPLICATION_JSON_VALUE)
public class GithubController {

    private final GithubService githubService;

    public GithubController(GithubService githubService) {
        this.githubService = githubService;
    }

    @GetMapping("/{username}")
    public List<RepositoryDto> getUserRepositories(@PathVariable String username) {
        return githubService.getUserRepositories(username);
    }
}
