package entity;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import deserialization.DeserializationProblemLogger;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class TestEntityDeserialization {

    final String validInput = "{\"actor\": {\"id\": \"actor_id\",\"type\": \"actor_type\",\"status\": \"actor_status\",\"context\": \"actor_context\"},\"verb\": \"verb\",\"object\": {\"id\": \"object_id\",\"type\": \"object_type\",\"status\": \"object_status\",\"context\": \"object_context\"},\"target\": {\"id\": \"target_id\",\"type\": \"target_type\",\"status\": \"target_status\",\"context\": \"target_context\"}\n" +
            "}";

    final String invalidInput = "{\"actor\": {\"id\": \"actor_id\",\"type\": \"actor_type\",\"status\": \"actor_status\",\"context\": \"actor_context\",\"invalid_1\": \"actor_invalid_1\"},\"verb\": \"verb\",\"object\": {\"id\": \"object_id\",\"type\": \"object_type\",\"invalid_2\": \"object_invalid_2\",\"status\": \"object_status\",\"context\": \"object_context\"},\"target\": {\"id\": \"target_id\",\"type\": \"target_type\",\"invalid_3\": \"target_invalid_3\",\"invalid_4\": \"target_invalid_4\",\"status\": \"target_status\",\"context\": \"target_context\"}}";

    @Test
    public void testDesializationValidInput() {
        final ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        final DeserializationProblemLogger logger = new DeserializationProblemLogger();
        objectMapper.addHandler(logger);
        try {
            objectMapper.readValue(validInput, Activity.class);
        } catch (IOException e) {
            Assert.fail();
        }
        Assert.assertFalse(logger.getDeserializationProblemLog().foundProblems());
    }

    @Test
    public void testDesializationInValidInput() {
        final ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        final DeserializationProblemLogger logger = new DeserializationProblemLogger();
        objectMapper.addHandler(logger);
        try {
            objectMapper.readValue(invalidInput, Activity.class);
        } catch (IOException e) {
            Assert.fail();
        }
        Assert.assertTrue(logger.getDeserializationProblemLog().foundProblems());

        // example input produces the following output
        // DeserializationProblem{unknownProperties=[actor.invalid_1.invalid_1, object.invalid_2, target.invalid_3, target.invalid_4]}
        // actor.invalid_1.invalid_1 is wrong, as it contains "invalid_1" twice
        System.out.println(logger.getDeserializationProblemLog());
    }
}