/*
 * Copyright 2018 original authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.particleframework.discovery.consul;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import org.particleframework.core.util.StringUtils;

import java.net.URL;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;

/**
 * @author graemerocher
 * @since 1.0
 */
@JsonNaming(PropertyNamingStrategy.UpperCamelCaseStrategy.class)
public class NodeEntry {
    private final String node;
    private final URL address;
    private String datacenter;
    private Map<String, String> taggedAddresses;
    private Map<String, String> nodeMetadata;

    /**
     * Create a new catalog entry
     * @param nodeId The node ID
     * @param address The node address
     */
    @JsonCreator
    public NodeEntry(@JsonProperty("Node") String nodeId, @JsonProperty("Address") URL address) {
        this.node = nodeId;
        this.address = address;
    }


    /**
     * https://www.consul.io/api/catalog.html#taggedaddresses
     *
     * @return The tagged addresses
     */
    public Map<String, String> getTaggedAddresses() {
        if(taggedAddresses == null) {
            return Collections.emptyMap();
        }
        return taggedAddresses;
    }

    /**
     * https://www.consul.io/api/catalog.html#nodemeta
     *
     * @return The node metadata
     */
    public Map<String, String> getNodeMetadata() {
        if(nodeMetadata == null) {
            return Collections.emptyMap();
        }
        return nodeMetadata;
    }

    /**
     * https://www.consul.io/api/catalog.html#node
     *
     * @return The node ID
     */
    public String getNode() {
        return node;
    }

    /**
     * https://www.consul.io/api/catalog.html#address
     *
     * @return The node address
     */
    public URL getAddress() {
        return address;
    }

    /**
     * https://www.consul.io/api/catalog.html#datacenter
     *
     * @return The data center to use
     */
    public Optional<String> getDatacenter() {
        return Optional.ofNullable(datacenter);
    }

    public NodeEntry datacenter(String datacenter) {
        if(StringUtils.isNotEmpty(datacenter))
            this.datacenter = datacenter;
        return this;
    }

    public NodeEntry taggedAddresses(Map<String, String> taggedAddresses) {
        this.taggedAddresses = taggedAddresses;
        return this;
    }

    public NodeEntry nodeMetadata(Map<String, String> nodeMetadata) {
        this.nodeMetadata = nodeMetadata;
        return this;
    }

    public void setDatacenter(String datacenter) {
        this.datacenter = datacenter;
    }

    public void setTaggedAddresses(Map<String, String> taggedAddresses) {
        this.taggedAddresses = taggedAddresses;
    }

    public void setNodeMetadata(Map<String, String> nodeMetadata) {
        this.nodeMetadata = nodeMetadata;
    }

    public void setMeta(Map<String, String> nodeMetadata) {
        this.nodeMetadata = nodeMetadata;
    }
}