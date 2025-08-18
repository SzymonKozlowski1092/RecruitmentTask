package SzymonKozlowski.Atipera.RecruitmentTask.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record GithubBranch(String name, Commit commit) {
    @JsonIgnoreProperties(ignoreUnknown = true)
    public record Commit(String sha){}
}
