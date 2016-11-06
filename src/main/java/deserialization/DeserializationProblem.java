package deserialization;

import java.util.ArrayList;
import java.util.List;

public class DeserializationProblem {

    final List<String> unknownProperties = new ArrayList<>();

    public DeserializationProblem() {

    }

    public void addUnknownProperty(final String prop) {
        this.unknownProperties.add(prop);
    }

    public boolean foundProblems() {
        return !unknownProperties.isEmpty();
    }

    @Override
    public String toString() {
        return "DeserializationProblem{" +
                "unknownProperties=" + unknownProperties +
                '}';
    }
}
