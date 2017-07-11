package com.github.felipecao.pact.matcher

import org.apache.commons.lang3.time.DateUtils
import org.hamcrest.Description
import org.hamcrest.TypeSafeDiagnosingMatcher
import au.com.dius.pact.model.matchingrules.RegexMatcher

import java.text.ParseException

class TimestampMatcher extends TypeSafeDiagnosingMatcher<String> {

    private String pattern
    private String regex

    private TimestampMatcher(String pattern, String regex) {
        this.pattern = pattern
        this.regex = regex
    }

    @Override
    protected boolean matchesSafely(String item, Description mismatchDescription) {
        mismatchDescription.appendText("was '$item'")
        return patternMatchesValue(item)
    }

    private boolean patternMatchesValue(String value) {
        if (pattern) {
            return matchTimestampPattern(value)
        }
        return matchRegex(value)
    }

    private boolean matchTimestampPattern(String value) {
        try {
            DateUtils.parseDateStrictly(value, pattern)
        }
        catch (ParseException e) {
            return false
        }

        return true
    }

    private boolean matchRegex(String value) {
        return value ==~ regex
    }

    @Override
    void describeTo(Description description) {
        description.appendText("timestamp should match pattern '$pattern'")
    }

    static TimestampMatcher matchesPattern(au.com.dius.pact.model.matchingrules.TimestampMatcher pattern) {
        return new TimestampMatcher(pattern.format, null)
    }

    static TimestampMatcher matchesPattern(RegexMatcher pattern) {
        return new TimestampMatcher(null, pattern.regex)
    }
}
