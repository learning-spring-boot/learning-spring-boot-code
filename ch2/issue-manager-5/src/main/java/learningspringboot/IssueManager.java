package learningspringboot;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.social.github.api.GitHubIssue;
import org.springframework.social.github.api.impl.GitHubTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class IssueManager {

    @Value("${github.token}")
    String githubToken;

    @Value("${org}")
    String org;

    @Value("${repos}")
    String[] repos;

    @Bean
    public GitHubTemplate gitHubTemplate() {
        return new GitHubTemplate(githubToken);
    }

    public List<Issue> findOpenIssues() {
        List<Issue> openIssues = new ArrayList<>();

        for (String repo : repos) {
            for (GitHubIssue issue : gitHubTemplate()
                    .repoOperations().getIssues(org, repo)) {
                if (issue.getState().equals("open")) {
                    openIssues.add(new Issue(repo, issue));
                }
            }
        }

        return openIssues;

    }
}
