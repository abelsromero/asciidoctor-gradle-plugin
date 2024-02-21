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
package org.asciidoctor.gradle.internal

import org.asciidoctor.gradle.remote.AsciidoctorWorkerExecutor
import org.ysb33r.grolifant.api.remote.worker.WorkerAppExecutor
import org.ysb33r.grolifant.api.remote.worker.WorkerAppExecutorFactory

/**
 * Creates an executor for running Asciidoctor conversions inside a worker.
 *
 * @author Schalk W. Cronjé
 *
 * @since 4.0
 */
class AsciidoctorExecutorFactory implements WorkerAppExecutorFactory<AsciidoctorWorkerParameters>, Serializable {
    /**
     * Creates an executor that can work with a set of parameters.
     *
     * @return Executor.
     */
    @Override
    WorkerAppExecutor<AsciidoctorWorkerParameters> createExecutor() {
        new AsciidoctorWorkerExecutor()
    }
}
