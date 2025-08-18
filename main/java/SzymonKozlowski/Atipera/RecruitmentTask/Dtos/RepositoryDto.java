package SzymonKozlowski.Atipera.RecruitmentTask.Dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record RepositoryDto(String name,
                            String ownerLogin,
                            List<BranchDto> branches) { }
