package com.nilsw13.springboot.replicate.unitaire;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nilsw13.springboot.replicate.responsetype.training.Training;
import com.nilsw13.springboot.replicate.api.ReplicateRestClient;
import com.nilsw13.springboot.replicate.impl.TrainingBuilderServiceImpl;
import com.nilsw13.springboot.replicate.impl.TrainingServiceImpl;
import com.nilsw13.springboot.replicate.service.TrainingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@Tag("unit-test")
 class TrainingServiceImplTest {

    @Mock
    private ReplicateRestClient mockRestClient;

     TrainingService trainingService;

    @BeforeEach
     void setUp() {
        MockitoAnnotations.openMocks(this);
        trainingService = new TrainingServiceImpl(mockRestClient);
    }

    @Test
    void testGetters() {
        Map<String, String> input = new HashMap<>();
        input.put("input-images", "image test.png");
        Map<String, Double> metrics = new HashMap<>();
        metrics.put("test metrics", 2.36);
        Training train = new Training();
        train.setVersion("test version");
        train.setMetrics(metrics);
        train.setLogs("tets logs");
        train.setInput(input);
        train.setError("null");
        train.setCreatedAt("12/12/12");
        train.setCompletedAt("12/13/13");

        assertThat(train.getVersion()).isEqualTo("test version");
        assertThat(train.getMetrics()).isEqualTo(metrics);
        assertThat(train.getOutput());

        assertThat(train.getLogs()).isEqualTo("tets logs");
        assertThat(train.getInput()).isEqualTo(input);
        assertThat(train.getError()).isEqualTo("null");
        assertThat(train.getCreatedAt()).isEqualTo("12/12/12");
        assertThat(train.getCompletedAt()).isEqualTo("12/13/13");

    }

    @Test
    public void testOutputWithNonStringValues() throws Exception {
        String json = """
            {
              "id": "abc123",
              "model": "my-model",
              "version": "1.0",
              "status": "completed",
              "created_at": "2023-01-01T00:00:00Z",
              "started_at": "2023-01-01T00:01:00Z",
              "completed_at": "2023-01-01T00:02:00Z",
              "input": {},
              "output": {
                "validation_images": ["img1.png", "img2.png"],
                "version": "",
                "weights": ""
              },
              "metrics": {},
              "urls": {},
              "logs": null,
              "error": null
            }
            """;

        ObjectMapper objectMapper = new ObjectMapper();

        // Désérialisation
        Training training = objectMapper.readValue(json, Training.class);

        // Vérifications
        assertNotNull(training.getOutput());
        assertTrue(training.getOutput().get("validation_images") instanceof List);
        assertEquals("",
                training.getOutput().get("version"));
        assertEquals("",
                training.getOutput().get("weights"));

        List<?> validationImages = (List<?>) training.getOutput().get("validation_images");
        assertEquals(2, validationImages.size());
        assertEquals("img1.png", validationImages.get(0));
        assertEquals("img2.png", validationImages.get(1));
    }





    private static class TestableTrainingBuilder extends TrainingBuilderServiceImpl {
        // Variables pour capturer les arguments
        public boolean capturedWait;
        public int capturedTimeout;
        public int executeOverloadCallCount = 0;
        public Training mockTraining = new Training();

        /**
         * Constructs a new TrainingBuilderServiceImpl with the required dependencies.
         *
         * @param replicateRestClient The REST client for API communication
         * @param modelOwner          The username or organization that owns the source model
         * @param modelName           The name of the source model
         * @param modelversion        The version ID of the source model
         */
        public TestableTrainingBuilder(ReplicateRestClient replicateRestClient, String modelOwner, String modelName, String modelversion) {
            super(replicateRestClient, modelOwner, modelName, modelversion);
        }

        @Override
        public Training execute(boolean wait, int timeout) throws InterruptedException {
            capturedWait = wait;
            capturedTimeout = timeout;
            executeOverloadCallCount++;

            return mockTraining;
        }
    }



}
