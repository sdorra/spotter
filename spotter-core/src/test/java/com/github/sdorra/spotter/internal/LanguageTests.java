/*
 * The MIT License
 *
 * Copyright 2017 Sebastian Sdorra
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.github.sdorra.spotter.internal;

import com.github.sdorra.spotter.Language;
import com.github.sdorra.spotter.internal.LanguageDetectionStrategy;
import com.google.common.io.Resources;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

final class LanguageTests {

    private LanguageTests() {
    }

    static List<Language> langFromResource(LanguageDetectionStrategy strategy, String resource) throws IOException {
        URL url = Resources.getResource(resource);
        byte[] content = Resources.toByteArray(url);

        return strategy.detect(resource, content);
    }

    static void assertLangFromResource(LanguageDetectionStrategy strategy, Language expected, String resource) throws IOException {
        List<Language> languages = langFromResource(strategy, resource);
        assertThat(languages).contains(expected);
    }

    static void assertLang(LanguageDetectionStrategy strategy, Language expected, String path) {
        List<Language> languages = strategy.detect(path, new byte[]{});
        assertThat(languages).contains(expected);
    }

    static void assertNotFound(LanguageDetectionStrategy strategy, String path) {
        List<Language> languages = strategy.detect(path, new byte[]{});
        assertThat(languages).isEmpty();
    }
}
