/*
 * Copyright 2013-2018 the original author or authors.
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
package org.asciidoctor.gradle.testfixtures.jvm.generators

import groovy.transform.CompileStatic
import groovy.transform.EqualsAndHashCode

@java.lang.SuppressWarnings('NoWildcardImports')
import static org.asciidoctor.gradle.testfixtures.jvm.AsciidoctorjTestVersions.*
@java.lang.SuppressWarnings('NoWildcardImports')
import static org.asciidoctor.gradle.testfixtures.jvm.JRubyTestVersions.*

/** A test fixture generator class for combining versions of AsciidoctorJ & JRuby for
 *PDF backend testing.
 *
 * @since 2.0
 */
@CompileStatic
class PdfBackendJRubyAsciidoctorJCombinationGenerator {

    @SuppressWarnings('ClassName')
    @EqualsAndHashCode
    static class Combination {
        String jrubyVer
        String asciidoctorjVer
        boolean compatible

        @Override
        String toString() {
            "PDF backend is ${compatible ? '' : 'not '}compatible with AsciidoctorJ=${asciidoctorjVer} + min JRuby=${jrubyVer}"
        }

        static Combination of(final String v, final String p, boolean b) {
            new Combination(jrubyVer: v, asciidoctorjVer: p, compatible: b)
        }
    }

    static Set<Combination> get() {
        List<Combination> combinations = [
            Combination.of(AJ20_ABSOLUTE_MAXIMUM, SERIES_20, true),
            Combination.of(AJ20_ABSOLUTE_MINIMUM, SERIES_20, true),
            Combination.of(AJ20_SAFE_MAXIMUM, SERIES_20, true),
            Combination.of(AJ20_SAFE_MINIMUM, SERIES_20, true),
            Combination.of(AJ16_ABSOLUTE_MAXIMUM, SERIES_16, true),
            Combination.of(AJ16_ABSOLUTE_MINIMUM, SERIES_16, true),
            Combination.of(AJ16_SAFE_MAXIMUM, SERIES_16, true),
            Combination.of(AJ16_SAFE_MINIMUM, SERIES_16, true)
        ]
        combinations.toSet()
    }

    static List<Combination> getRandom() {
        List<Combination> vp = get().toList()
        Collections.shuffle(vp)
        vp
    }
}
