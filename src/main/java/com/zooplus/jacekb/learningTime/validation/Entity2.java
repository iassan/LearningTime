package com.zooplus.jacekb.learningTime.validation;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * Created with IntelliJ IDEA.
 * User: jacek_bilski
 * Date: 12.06.14
 * Time: 11:42
 */
public class Entity2 {
    @Valid
    @NotNull
    private Entity1 entity1;

    public Entity1 getEntity1() {
        return entity1;
    }

    public void setEntity1(Entity1 entity1) {
        this.entity1 = entity1;
    }
}
