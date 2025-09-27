package com.nilsw13.springboot.replicate.responsetype.training;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

/**
 * Represents a training job in the Replicate platform.
 *
 * A training job is a process that fine-tunes or trains a model with custom data.
 * This class corresponds to the JSON structure returned by the Replicate API
 * when creating, retrieving, or listing training jobs. It includes fields for
 * tracking the lifecycle of a training job from creation through completion
 * or error states, as well as inputs, outputs, and metrics.
 *
 * Training jobs typically go through several states during their lifecycle:
 * - starting: The training is being prepared
 * - processing: The training is running
 * - succeeded: The training completed successfully
 * - failed: The training encountered an error
 *
 * @author Nilsw13
 * @since 1.0.0
 */
public class Training {

    @JsonProperty("completed_at")
    private String completedAt;
    @JsonProperty("created_at")
    private String createdAt;
    private String error;
    private String id;
    private Map<String, String> input;
    private String logs;
    private Map<String , Double> metrics;
    private Map<String, Object> output;
    @JsonProperty("started_at")
    private String startedAt;
    private String status;
    private Map<String, String> urls;
    private String model;
    private String version;


    public Training() {
        /**
         * Default constructor for Training class.
         *
         * This empty constructor exists for the following reasons:
         * 1. Required by JSON/Jackson deserialization process when mapping API responses
         * 2. Enables library users to instantiate response objects when needed
         * 3. Supports serialization/deserialization in various client implementations
         *
         * Although empty, this constructor is essential for the proper functioning
         * of the API client library and should not be removed.
         */
    }

    public String getCompletedAt() {
        return completedAt;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getError() {
        return error;
    }

    public String getId() {
        return id;
    }

    public Map<String, String> getInput() {
        return input;
    }

    public String getLogs() {
        return logs;
    }

    public Map<String, Double> getMetrics() {
        return metrics;
    }

    public Map<String, Object> getOutput() {
        return output;
    }

    public String getStartedAt() {
        return startedAt;
    }

    public String getStatus() {
        return status;
    }

    public Map<String, String> getUrls() {
        return urls;
    }

    public String getModel() {
        return model;
    }

    public String getVersion() {
        return version;
    }

    public void setCompletedAt(String completedAt) {
        this.completedAt = completedAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public void setError(String error) {
        this.error = error;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setInput(Map<String, String> input) {
        this.input = input;
    }

    public void setLogs(String logs) {
        this.logs = logs;
    }

    public void setMetrics(Map<String, Double> metrics) {
        this.metrics = metrics;
    }

    public void setOutput(Map<String, Object> output) {
        this.output = output;
    }

    public void setStartedAt(String startedAt) {
        this.startedAt = startedAt;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setUrls(Map<String, String> urls) {
        this.urls = urls;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "Training{" +
                "id='" + id + '\'' +
                ", model='" + model + '\'' +
                ", version='" + version + '\'' +
                ", status='" + status + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", startedAt='" + startedAt + '\'' +
                ", completedAt='" + completedAt + '\'' +
                ", input=" + input +
                ", output=" + output +
                ", metrics=" + metrics +
                ", error='" + error + '\'' +
                ", logs='" + (logs != null ? logs.substring(0, Math.min(logs.length(), 50)) + "..." : "null") + '\'' +
                ", urls=" + urls +
                '}';
    }
}
