package com.tambapps.website.model.request;

import com.tambapps.website.model.OrderingType;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class OrderRequest {
    @NotNull
    private OrderingType type;

    @NotNull
    private List<Long> pizzaIds;


}
