package com.suood.guava.utilities;

import com.google.common.base.CharMatcher;
import com.google.common.base.Joiner;
import com.google.common.collect.Maps;
import org.junit.Test;

import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by FENGCUIJIE on 2017/3/6.
 */
public class StringWork {

    @Test
    public void testMapJoiner() {
    //Using LinkedHashMap so that the original order is preserved

        String expectedString = "Washington D.C=Redskins#New York City=Giants#Philadelphia=Eagles#Dallas=Cowboys";
        Map<String,String> testMap = Maps.newLinkedHashMap();
        testMap.put("Washington D.C","Redskins");
        testMap.put("New York City","Giants");
        testMap.put("Philadelphia","Eagles");
        testMap.put("Dallas","Cowboys");
        String returnedString = Joiner.on("#").
                withKeyValueSeparator("=").join(testMap);
        assertThat(returnedString,is(expectedString));
    }

    @Test
    public void testRemoveWhiteSpace(){
        String tabsAndSpaces = "String with spaces and tabs";
        String expected = "String with spaces and tabs";
        String scrubbed = CharMatcher.whitespace().
                collapseFrom(tabsAndSpaces,' ');
        assertThat(scrubbed,is(expected));
    }
}
