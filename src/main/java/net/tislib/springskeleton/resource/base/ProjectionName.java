package net.tislib.springskeleton.resource.base;

public interface ProjectionName {
    String name();

    Class<? extends BaseProjection> getProjectionClass();
}
