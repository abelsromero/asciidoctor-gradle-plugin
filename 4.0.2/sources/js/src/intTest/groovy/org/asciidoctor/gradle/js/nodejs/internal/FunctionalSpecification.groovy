/*
 * Copyright 2013-2024 the original author or authors.
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
package org.asciidoctor.gradle.js.nodejs.internal

import org.apache.commons.io.FileUtils
import org.asciidoctor.gradle.testfixtures.DslType
import org.asciidoctor.gradle.testfixtures.FunctionalTestFixture
import spock.lang.Specification
import spock.lang.TempDir

import static org.asciidoctor.gradle.testfixtures.DslType.GROOVY_DSL
import static org.asciidoctor.gradle.testfixtures.DslType.KOTLIN_DSL

class FunctionalSpecification extends Specification implements FunctionalTestFixture {

    public static final String TEST_PROJECTS_DIR = System.getProperty(
            'TEST_PROJECTS_DIR',
            './asciidoctor-gradle-js/src/intTest/projects'
    )

    @TempDir
    File testProjectDir

    void setup() {
        initializeProjectLayout()
    }

    @SuppressWarnings(['FactoryMethodName', 'BuilderMethodWithSideEffects'])
    void createTestProject(String docGroup = 'normal') {
        FileUtils.copyDirectory(new File(TEST_PROJECTS_DIR, docGroup), projectDir)
    }

    File getJsConvertGroovyBuildFile(String extraContent, String plugin = 'org.asciidoctor.js.convert') {
        buildFile << """
            plugins {
                id '${plugin}'
            }

            ${offlineRepositories}

            ${extraContent}
        """
        buildFile
    }

    File getJvmConvertKotlinBuildFile(String extraContent, String plugin = 'org.asciidoctor.js.convert') {
        buildFileKts << """
            plugins {
                id ("${plugin}")
            }

            ${getOfflineRepositories(KOTLIN_DSL)}

            ${extraContent}
        """
        buildFileKts
    }

    String getDefaultProcessModeForAppveyor(final DslType dslType = GROOVY_DSL) {
        if (System.getenv('APPVEYOR')) {
            dslType == GROOVY_DSL ? 'inProcess = JAVA_EXEC' : 'inProcess = ProcessMode.JAVA_EXEC'
        } else {
            ''
        }
    }

}