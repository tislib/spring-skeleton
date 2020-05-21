package net.tislib.springskeleton.resource.base;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Set;

public interface BaseResource {

    @JsonIgnore
    Set<String> getFields();

    @JsonIgnore
    void setFields(Set<String> value);
}
