//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.timesoft.hr.employeedata.lib.partial;

import com.fasterxml.jackson.databind.module.SimpleModule;

public class PartialResourceModule extends SimpleModule {
    private static final String MODULE_NAME = "PartialResource";

    public PartialResourceModule() {
        super("PartialResource");
        this.setDeserializerModifier(new PartialResourceDeserializerModifier());
    }
}
