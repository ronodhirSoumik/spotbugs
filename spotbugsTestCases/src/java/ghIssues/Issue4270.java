package ghIssues;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class Issue4270 {

    // Fresh ArrayList is always empty — isEmpty() always returns true,
    // so the guarded branch is dead code. No NP_ALWAYS_NULL expected.
    public void freshEmptyArrayList() {
        ArrayList<Object> values = new ArrayList<>();
        if (!values.isEmpty()) {
            Object object = null;
            object.toString();
        }
    }

    // Fresh HashSet is always empty — same reasoning. No NP_ALWAYS_NULL expected.
    public void freshEmptyHashSet() {
        HashSet<Object> values = new HashSet<>();
        if (!values.isEmpty()) {
            Object object = null;
            object.toString();
        }
    }

    // Fresh LinkedList is always empty — same reasoning. No NP_ALWAYS_NULL expected.
    public void freshEmptyLinkedList() {
        LinkedList<Object> values = new LinkedList<>();
        if (!values.isEmpty()) {
            Object object = null;
            object.toString();
        }
    }

    // Parameter list — isEmpty() result is unknown, body is reachable.
    // NP_ALWAYS_NULL should still be reported.
    public void nonFreshList(List<Object> values) {
        if (!values.isEmpty()) {
            Object object = null;
            object.toString();
        }
    }
}
