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
package io.adminshell.aas.v3.dataformat.aml.serialize;

import io.adminshell.aas.v3.dataformat.aml.fixtures.FullExample;
import io.adminshell.aas.v3.dataformat.SerializationException;
import io.adminshell.aas.v3.dataformat.aml.AmlSerializer;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class AmlSerializerTest {

    private final AmlSerializer serializer = new AmlSerializer();

    @Test
    public void testSAPFullExample() throws SerializationException {
        String aml = serializer.write(FullExample.ENVIRONMENT);

        try {
            File xmlOutput = new File("src/test/resources/amlfile/full-example.xml");
            FileWriter fileWriter = new FileWriter(xmlOutput);
            fileWriter.write(aml);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // System.err.println(actual);
    }
}
