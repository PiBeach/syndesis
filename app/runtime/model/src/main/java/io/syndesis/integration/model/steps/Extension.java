/*
 * Copyright (C) 2016 Red Hat, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.syndesis.integration.model.steps;

import java.util.HashMap;
import java.util.Map;

import com.google.auto.service.AutoService;


@AutoService(Step.class)
public class Extension extends Step {
    public static final String KIND = "extension";

    private String name;
    private Map<String, Object> properties;

    public Extension() {
        this(null, new HashMap<>());
    }

    public Extension(String name, Map<String, Object> properties) {
        super(KIND);

        this.name = name;
        this.properties = properties == null ? new HashMap<>() : properties;
    }

    @Override
    public String toString() {
        return "Extension{" +
            "target='" + name + '\'' +
            ", properties=" + properties +
            '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, Object> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, Object> properties) {
        this.properties = properties;
    }

    // ****************************
    // DSL
    // ****************************

    public Extension name(String name) {
        setName(name);

        return this;
    }

    public Extension property(String name, Object value) {
        if (this.properties == null) {
            this.properties = new HashMap<>();
        }

        this.properties.put(name, value);

        return this;
    }
}
