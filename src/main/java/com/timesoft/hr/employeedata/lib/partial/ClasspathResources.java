//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.timesoft.hr.employeedata.lib.partial;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import lombok.Generated;
import org.apache.commons.io.IOUtils;

public final class ClasspathResources {
    public static String readString(String resource) {
        return new String(readBytes(resource), StandardCharsets.UTF_8);
    }

    private static byte[] readBytes(String resource) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        return readBytes(classLoader, resource);
    }

    private static byte[] readBytes(ClassLoader classLoader, String resource) {
        URL url = classLoader.getResource(resource);
        if (url == null) {
            throw new IllegalStateException(String.format("Missing resource [%s]", resource));
        } else {
            try {
                InputStream in = url.openStream();
                Throwable var4 = null;

                byte[] var5;
                try {
                    var5 = IOUtils.toByteArray(in);
                } catch (Throwable var15) {
                    var4 = var15;
                    throw var15;
                } finally {
                    if (in != null) {
                        if (var4 != null) {
                            try {
                                in.close();
                            } catch (Throwable var14) {
                                var4.addSuppressed(var14);
                            }
                        } else {
                            in.close();
                        }
                    }

                }

                return var5;
            } catch (IOException var17) {
                throw new IllegalStateException(String.format("Error while reading resource [%s]", resource), var17);
            }
        }
    }

    @Generated
    private ClasspathResources() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
}
