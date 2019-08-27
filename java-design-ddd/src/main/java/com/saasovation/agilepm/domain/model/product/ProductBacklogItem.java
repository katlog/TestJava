package com.saasovation.agilepm.domain.model.product;

import com.saasovation.agilepm.domain.model.product.backlogitem.BacklogItemId;

/**
 * Created by fw on 2019/8/26
 */
public class ProductBacklogItem {

    private BacklogItemId backlogItemId;
    private int ordering;

    protected  void reorderFrom(BacklogItemId anId, int anOrdering) {
        if (this.backlogItemId().equals(anId)) {
            this.setOrdering(anOrdering);
        } else if (this.ordering() >= anOrdering) {
            this.setOrdering(this.ordering() + 1);
        }
    }

    private void setOrdering(int anOrdering) {
        this.ordering = anOrdering;
    }
    private int ordering() {
        return ordering;
    }
    private BacklogItemId backlogItemId() {
        return backlogItemId;
    }
}
