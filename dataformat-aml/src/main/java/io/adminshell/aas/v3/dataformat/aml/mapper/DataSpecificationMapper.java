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
package io.adminshell.aas.v3.dataformat.aml.mapper;

import io.adminshell.aas.v3.dataformat.aml.MappingContext;
import io.adminshell.aas.v3.dataformat.aml.model.caex.AttributeType;
import io.adminshell.aas.v3.dataformat.aml.model.caex.InternalElementType;
import io.adminshell.aas.v3.dataformat.aml.model.caex.RoleClassType;
import io.adminshell.aas.v3.dataformat.aml.model.caex.SystemUnitClassType.InternalLink;
import io.adminshell.aas.v3.dataformat.aml.util.AASUtils;
import io.adminshell.aas.v3.dataformat.core.ReflectionHelper;
import io.adminshell.aas.v3.model.DataSpecificationContent;
import io.adminshell.aas.v3.model.EmbeddedDataSpecification;

public class DataSpecificationMapper extends BaseMapper<EmbeddedDataSpecification> {

    public DataSpecificationMapper() {
    }

    @Override
    public void map(EmbeddedDataSpecification value, MappingContext context) throws MappingException {
        if (value.getDataSpecificationContent() != null) {
            //embedded
            DataSpecificationContent content = value.getDataSpecificationContent();
            String refSystemUnitPath = "AssetAdministrationShellDataSpecificationTemplates/" + content.getClass().getSimpleName() + "Template/" + content.getClass().getSimpleName();
            InternalElementType.Builder builder = InternalElementType.builder()
                    .withID(context.getIdentityProvider().getId(value))
                    .withRefBaseSystemUnitPath(refSystemUnitPath)
                    .withName("EmbeddedDataSpecification");
            // serialize properties, but with different attribute path ("IEC:DataSpecificationIEC63360/[propertyName])
        } else {
            // linked
            // should this even be serialized for AML?
        }
    }
}
