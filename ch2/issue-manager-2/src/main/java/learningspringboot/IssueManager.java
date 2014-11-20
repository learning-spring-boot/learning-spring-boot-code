package learningspringboot;

import java.util.ArrayList;
import java.util.List;

import org.springframework.social.github.api.GitHubIssue;
import org.springframework.social.github.api.impl.GitHubTemplate;
import org.springframework.stereotype.Service;

@Service
public class IssueManager {

	String githubToken = "ccdbf257f052a594a0e7bd2823a69ae38a48ffb1";

	String org = "spring-projects";

	String[] repos = new String[] { "spring-boot", "spring-boot-issues" };

	GitHubTemplate gitHubTemplate = new GitHubTemplate(githubToken);

	public List<Issue> findOpenIssues() {
		List<Issue> openIssues = new ArrayList<>();

		for (String repo : repos) {
			final List<GitHubIssue> issues = gitHubTemplate
				.repoOperations().getIssues(org, repo);

			for (GitHubIssue issue : issues) {
				if (issue.getState().equals("open")) {
					openIssues.add(new Issue(repo, issue));
				}
			}
		}

		return openIssues;

	}
}
