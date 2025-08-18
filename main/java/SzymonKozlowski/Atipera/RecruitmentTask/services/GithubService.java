package SzymonKozlowski.Atipera.RecruitmentTask.services;

import SzymonKozlowski.Atipera.RecruitmentTask.Models.GithubBranch;
import SzymonKozlowski.Atipera.RecruitmentTask.Models.GithubRepository;
import SzymonKozlowski.Atipera.RecruitmentTask.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import java.util.Collections;
import java.util.List;

@Service
public class GithubService {

    private final RestClient client;

    public GithubService(@Value("${github.base.url}") String baseUrl,
                         @Value("${github.token:}") String githubAuthToken) {

        RestClient.Builder builder = RestClient.builder()
                .baseUrl(baseUrl)
                .defaultHeader("User-Agent", "Atipera-Recruitment-Task/1.0")
                .defaultHeader("Accept", "application/vnd.github+json")
                .defaultHeader("X-GitHub-Api-Version", "2022-11-28");

        if (githubAuthToken != null && !githubAuthToken.isBlank()) {
            builder.defaultHeader("Authorization", "token " + githubAuthToken);
        }

        this.client = builder.build();
    }

    public List<GithubRepository> listUserRepositories(String username){
        List<GithubRepository> repos = client.get()
                .uri("/users/{username}/repos", username)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, (req, res) -> {
                    if (res.getStatusCode().value() == 404) {
                        throw new UserNotFoundException("User: " + username + " not found");
                    }
                })
                .body(new ParameterizedTypeReference<List<GithubRepository>>() {});

        if (repos == null) return Collections.emptyList();

        return repos.stream()
                .filter(repo -> !repo.fork())
                .toList();
    }

    public List<GithubBranch> listRepositoryBranches(String ownerUsername, String repositoryName){
        List<GithubBranch> branches = client.get()
                .uri("/repos/{owner}/{repo}/branches", ownerUsername, repositoryName)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, (req, res) -> {
                    if (res.getStatusCode().value() == 404) {
                        throw new RuntimeException("Repository " + ownerUsername + "/" + repositoryName + " not found");
                    }
                })
                .toEntity(new ParameterizedTypeReference<List<GithubBranch>>() {})
                .getBody();

        return branches == null ? Collections.emptyList() : branches;
    }
}
