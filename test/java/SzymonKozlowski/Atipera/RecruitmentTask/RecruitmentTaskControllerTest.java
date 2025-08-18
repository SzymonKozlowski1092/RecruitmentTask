package SzymonKozlowski.Atipera.RecruitmentTask;

import SzymonKozlowski.Atipera.RecruitmentTask.Dtos.BranchDto;
import SzymonKozlowski.Atipera.RecruitmentTask.Dtos.RepositoryDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RecruitmentTaskControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void shouldReturnRepositoriesWithBranchesAndLastCommitSha_forExistingUser() {
        String username = "octocat";
        String url = "http://localhost:" + port + "/api/repositories/" + username;

        ResponseEntity<List<RepositoryDto>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<RepositoryDto>>() {}
        );

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        List<RepositoryDto> repositories = response.getBody();
        assertThat(repositories).isNotNull();

        for (RepositoryDto repo : repositories) {
            assertThat(repo.name()).isNotBlank();
            assertThat(repo.ownerLogin()).isEqualTo(username);

            List<BranchDto> branches = repo.branches();
            assertThat(branches).isNotEmpty();
            for (BranchDto branch : branches) {
                assertThat(branch.name()).isNotBlank();
                assertThat(branch.lastCommitSha()).isNotBlank();
            }
        }
    }
}
