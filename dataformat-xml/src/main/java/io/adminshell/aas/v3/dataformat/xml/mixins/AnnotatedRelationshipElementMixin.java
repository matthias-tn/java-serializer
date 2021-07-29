/*
 * Copyright (c) 2021 Fraunhofer-Gesellschaft zur Foerderung der angewandten Forschung e. V.
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
package io.adminshell.aas.v3.dataformat.xml.mixins;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import io.adminshell.aas.v3.dataformat.xml.deserialization.DataElementsDeserializer;
import io.adminshell.aas.v3.dataformat.xml.serialization.DataElementsSerializer;
import io.adminshell.aas.v3.model.DataElement;

@JsonPropertyOrder({"extensions", "idShort", "displayNames", "category", "descriptions", "kind", "semanticId",
    "qualifiers", "dataSpecifications", "first", "second", "annotations"})
public interface AnnotatedRelationshipElementMixin {
    @JsonSerialize(using = DataElementsSerializer.class)
    @JsonDeserialize(using = DataElementsDeserializer.class)
    public List<DataElement> getAnnotations();
}
