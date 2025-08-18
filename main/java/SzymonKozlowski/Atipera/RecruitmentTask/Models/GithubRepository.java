package SzymonKozlowski.Atipera.RecruitmentTask.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record GithubRepository(String name, Owner owner, boolean fork) {
    @JsonIgnoreProperties(ignoreUnknown = true)
    public record Owner(String login){}
}
