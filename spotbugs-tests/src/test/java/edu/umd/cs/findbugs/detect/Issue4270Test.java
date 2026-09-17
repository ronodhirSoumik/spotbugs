package edu.umd.cs.findbugs.detect;

import edu.umd.cs.findbugs.AbstractIntegrationTest;
import org.junit.jupiter.api.Test;

class Issue4270Test extends AbstractIntegrationTest {

    @Test
    void testFreshEmptyCollectionIsEmptyBranch() {
        performAnalysis("ghIssues/Issue4270.class");

        // Fresh empty collections: isEmpty() is always true, so !isEmpty() branch is dead code.
        // No NP_ALWAYS_NULL should be reported inside the dead branch.
        assertNoBugInMethod("NP_ALWAYS_NULL", "ghIssues.Issue4270", "freshEmptyArrayList");
        assertNoBugInMethod("NP_ALWAYS_NULL", "ghIssues.Issue4270", "freshEmptyHashSet");
        assertNoBugInMethod("NP_ALWAYS_NULL", "ghIssues.Issue4270", "freshEmptyLinkedList");

        // Parameter list: isEmpty() result is unknown, body is reachable.
        // NP_ALWAYS_NULL should still be reported.
        assertBugInMethod("NP_ALWAYS_NULL", "ghIssues.Issue4270", "nonFreshList");
    }
}
