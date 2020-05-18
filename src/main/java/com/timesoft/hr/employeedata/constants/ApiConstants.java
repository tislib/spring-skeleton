package com.timesoft.hr.employeedata.constants;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ApiConstants {

    public final static String VERSION = "1.0";
    public final static String API_PATH = "/api/" + VERSION;

    public final static String API_ID_RESOURCE = "{id}";

    public final static String API_COUNTRIES = API_PATH + "/" + "countries";


    public final static String PARAM_PROJECTION = "projection";
    public final static String DEFAULT_PROJECTION = "DEFAULT";

}
