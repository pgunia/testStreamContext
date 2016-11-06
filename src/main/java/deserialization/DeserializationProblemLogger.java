package deserialization;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonStreamContext;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.deser.DeserializationProblemHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DeserializationProblemLogger extends DeserializationProblemHandler {

    final DeserializationProblem deserializationProblemLog = new DeserializationProblem();

    public boolean handleUnknownProperty(final DeserializationContext ctxt, final JsonParser p, final JsonDeserializer<?> deserializer,
                                         final Object beanOrClass, final String propertyName)
            throws IOException {

        final JsonStreamContext parsingContext = ctxt.getParser().getParsingContext();
        final List<String> pathList = new ArrayList<>();
        pathList.add(propertyName);

        addParent(parsingContext, pathList);
        Collections.reverse(pathList);
        final String path = String.join(".", pathList);

        deserializationProblemLog.addUnknownProperty(path);
        return super.handleUnknownProperty(ctxt, p, deserializer, beanOrClass, propertyName);

    }

    public DeserializationProblem getDeserializationProblemLog() {
        return deserializationProblemLog;
    }

    private void addParent(final JsonStreamContext streamContext, final List<String> pathList) {
        if (streamContext != null && streamContext.getCurrentName() != null) {
            pathList.add(streamContext.getCurrentName());
            addParent(streamContext.getParent(), pathList);
        }
    }
}
