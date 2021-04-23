package com.fasterxml.jackson.datatype.jdk8;

import java.util.Optional;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.OptionalLong;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

@SuppressWarnings("deprecation")
public class TestConfigureAbsentsAsNulls extends ModuleTestBase
{
    @JsonAutoDetect(fieldVisibility=Visibility.ANY)
    public static final class OptionalData {
        public Optional<String> myString = Optional.empty();
    }

    public static final class OptionalIntWrapper {
        @JsonInclude(JsonInclude.Include.NON_NULL)
        public OptionalInt optional = OptionalInt.empty();
    }

    public static final class OptionalLongWrapper {
        @JsonInclude(JsonInclude.Include.NON_NULL)
        public OptionalLong optional = OptionalLong.empty();
    }

    public static final class OptionalDoubleWrapper {
        @JsonInclude(JsonInclude.Include.NON_NULL)
        public OptionalDouble optional = OptionalDouble.empty();
    }

    /*
    /**********************************************************************
    /* Test methods, basic Optional
    /**********************************************************************
     */

    public void testConfigAbsentsAsNullsTrue() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new Jdk8Module().configureAbsentsAsNulls(true));

        OptionalData data = new OptionalData();
        String value = mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL).writeValueAsString(data);
        assertEquals("{}", value);
    }

    public void testConfigAbsentsAsNullsFalse() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new Jdk8Module().configureAbsentsAsNulls(false));

        OptionalData data = new OptionalData();
        String value = mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL).writeValueAsString(data);
        assertEquals("{\"myString\":null}", value);
    }

    public void testConfigNonAbsentAbsentsAsNullsTrue() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new Jdk8Module().configureAbsentsAsNulls(true));

        OptionalData data = new OptionalData();
        String value = mapper.setSerializationInclusion(JsonInclude.Include.NON_ABSENT).writeValueAsString(data);
        assertEquals("{}", value);
    }

    public void testConfigNonAbsentAbsentsAsNullsFalse() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new Jdk8Module().configureAbsentsAsNulls(false));

        OptionalData data = new OptionalData();
        String value = mapper.setSerializationInclusion(JsonInclude.Include.NON_ABSENT).writeValueAsString(data);
        assertEquals("{}", value);
    }

    /*
    /**********************************************************************
    /* Test methods, OptionalXxx scalar variants
    /**********************************************************************
     */

    public void testOptionalIntAbsentAsNulls() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new Jdk8Module().configureAbsentsAsNulls(true));
        String value = mapper.writeValueAsString(new OptionalIntWrapper());
        assertEquals("{}", value);
    }

    public void testOptionalLongAbsentAsNulls() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new Jdk8Module().configureAbsentsAsNulls(true));
        String value = mapper.writeValueAsString(new OptionalLongWrapper());
        assertEquals("{}", value);
    }

    public void testOptionalDoubleAbsentAsNulls() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new Jdk8Module().configureAbsentsAsNulls(true));
        String value = mapper.writeValueAsString(new OptionalDoubleWrapper());
        assertEquals("{}", value);
    }
}
