package SzymonKozlowski.Atipera.RecruitmentTask.services;

import SzymonKozlowski.Atipera.RecruitmentTask.Dtos.BranchDto;
import SzymonKozlowski.Atipera.RecruitmentTask.Dtos.RepositoryDto;
import SzymonKozlowski.Atipera.RecruitmentTask.Models.GithubBranch;
import SzymonKozlowski.Atipera.RecruitmentTask.Models.GithubRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecruitmentTaskService {
    private final GithubService githubService;

    public RecruitmentTaskService(GithubService githubService){
        this.githubService = githubService;
    }

    public List<RepositoryDto> getUserRepositories(String username) {
        List<GithubRepository> repos = githubService.listUserRepositories(username);

        return repos.stream()
                .map(repo -> {
                    List<GithubBranch> branches = githubService.listRepositoryBranches(
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