package SzymonKozlowski.Atipera.RecruitmentTask.services;

import SzymonKozlowski.Atipera.RecruitmentTask.Dtos.BranchDto;
import SzymonKozlowski.Atipera.RecruitmentTask.Dtos.RepositoryDto;
import SzymonKozlowski.Atipera.RecruitmentTask.GithubClient;
import SzymonKozlowski.Atipera.RecruitmentTask.Models.GithubBranch;
import SzymonKozlowski.Atipera.RecruitmentTask.Models.GithubRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GithubService {
    private final GithubClient githubClient;

    public GithubService(GithubClient githubClient){
        this.githubClient = githubClient;
    }

    public List<RepositoryDto> getUserRepositories(String username) {
        List<GithubRepository> repos = githubClient.listUserRepositories(username);

        return repos.stream()
                .map(repo -> {
                    List<GithubBranch> branches = githubClient.listRepositoryBranches(
                            repo.owner().login(),
                            repo.name());

                    List<BranchDto> branchDtos = branches.stream()
                            .map(b -> new BranchDto(
                                    b.name(),
                                    b.commit() != null ? b.commit().sha() : null
                            ))
                            .toList();

                    return new RepositoryDto(
                            repo.name(),
                            repo.owner() != null ? repo.owner().login() : null,
                            branchDtos
                    );
                })
                .toList();
    }
}