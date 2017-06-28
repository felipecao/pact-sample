package com.github.felipecao.pact.matcher

import org.apache.commons.lang3.time.DateUtils
import org.hamcrest.Description
import org.hamcrest.TypeSafeDiagnosingMatcher

import java.text.ParseException

class TimestampMatcher extends TypeSafeDiagnosingMatcher<String> {

    private String pattern

    TimestampMatcher(String pattern) {
        this.pattern = pattern
    }

    @Override
    protected boolean matchesSafely(String item, Description mismatchDescription) {
        mismatchDescription.appendText("was '$item'")
        return patternMatchesValue(item)
    }

    private boolean patternMatchesValue(String value) {
        try {
            DateUtils.parseDateStrictly(value, pattern)
        }
        catch (ParseException e) {
            return false
        }

        return true
    }

    @Override
    void describeTo(Description description) {
        description.appendText("timestamp should match pattern '$pattern'")
    }

    static TimestampMatcher matchesPattern(String pattern) {
        return new TimestampMatcher(pattern)
    }
}
