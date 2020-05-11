//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.timesoft.hr.employeedata.lib.partial;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import lombok.Generated;

public abstract class PartialResource implements BaseResource {
    public static final String FIELD_PARTIAL_PROPERTIES = "partialProperties";
    @JsonIgnore
    @EqualsVerifierIgnore
    private Map<String, Object> partialProperties;

    public PartialResource() {
    }

    void setPartialProperties(Map<String, Object> properties) {
        this.partialProperties = properties == null ? null : new HashMap(properties);
    }

    Map<String, Object> getPartialProperties() {
        return this.partialProperties == null ? Collections.emptyMap() : Collections.unmodifiableMap(this.partialProperties);
    }

    @Generated
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof PartialResource)) {
            return false;
        } else {
            PartialResource other = (PartialResource)o;
            return other.canEqual(this);
        }
    }

    @Generated
    protected boolean canEqual(Object other) {
        return other instanceof PartialResource;
    }

    @Generated
    public int hashCode() {
        int result = 1;
        return result;
    }
}
