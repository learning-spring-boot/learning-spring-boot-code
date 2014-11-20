package learningspringboot;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.social.github.api.GitHubIssue;
import org.springframework.social.github.api.impl.GitHubTemplate;
import org.springframework.stereotype.Service;

@Service
public class IssueManager implements InitializingBean {

    @Value("${github.token}")
    String githubToken;

    @Value("${org}")
    String org;

    @Value("${repos}")
    String[] repos;

    GitHubTemplate gitHubTemplate;

    @Override
    public void afterPropertiesSet() throws Exception {
        this.gitHubTemplate = new GitHubTemplate(githubToken);
    }

    public List<Issue> findOpenIssues() {
        List<Issue> openIssues = new ArrayList<>();

        for (String repo : repos) {
            for (GitHubIssue issue : gitHubTemplate
                    .repoOperations().getIssues(org, repo)) {
                if (issue.getState().equals("open")) {
                    openIssues.add(new Issue(repo, issue));
                }
            }
        }

        return openIssues;

    }
}
