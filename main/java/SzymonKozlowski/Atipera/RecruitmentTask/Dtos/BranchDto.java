package SzymonKozlowski.Atipera.RecruitmentTask.Dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record BranchDto(String name, String lastCommitSha) {
}
