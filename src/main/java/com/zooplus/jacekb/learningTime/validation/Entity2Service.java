package com.zooplus.jacekb.learningTime.validation;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * Created with IntelliJ IDEA.
 * User: jacek_bilski
 * Date: 12.06.14
 * Time: 11:58
 */
public interface Entity2Service {
    public void processEntity2(@Valid @NotNull Entity2 entity2);
}
