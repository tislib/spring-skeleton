package net.tislib.springskeleton.resource.projection;

import net.tislib.springskeleton.resource.base.BaseProjection;

public interface IdAndNameProjection extends BaseProjection {
    Number getId();

    String getName();
}
