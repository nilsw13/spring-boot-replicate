package com.nilsw13.springboot.replicate.responsetype.deployment;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;


/**
 * Represents a specific release of a deployment in the Replicate API.
 *
 * A deployment release encapsulates the specific model version, configuration,
 * and metadata for a point-in-time release of a deployment. Each time a deployment
 * is updated, a new release is created with an incremented release number.
 *
 * This class contains details about the model version being deployed, when it was
 * created, who created it, and the specific configuration parameters in effect.
 *
 * @author Nilsw13
 * @since 1.0.0
 */
public class DeploymentRelease {


    private String number;
    private String model;
    private String version;

    @JsonProperty("created_at")
    private String createdAt;

    @JsonProperty("created_by")
    private Map<String, String> createdBy;

    private DeploymentConfiguration configuration;


    public DeploymentRelease() {
        /**
         * Default constructor for DeploymentRelease class.
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

    public void setNumber(String number) {
        this.number = number;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public void setCreatedBy(Map<String, String> createdBy) {
        this.createdBy = createdBy;
    }

    public void setConfiguration(DeploymentConfiguration configuration) {
        this.configuration = configuration;
    }

    public String getNumber() {
        return number;
    }

    public String getModel() {
        return model;
    }

    public String getVersion() {
        return version;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public Map<String, String> getCreatedBy() {
        return createdBy;
    }

    public DeploymentConfiguration getConfiguration() {
        return configuration;
    }
}
