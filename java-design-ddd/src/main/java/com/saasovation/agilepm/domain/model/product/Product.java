package com.saasovation.agilepm.domain.model.product;

import com.saasovation.agilepm.domain.model.ConcurrencySafeEntity;
import com.saasovation.agilepm.domain.model.product.backlogitem.BacklogItem;
import com.saasovation.agilepm.domain.model.product.backlogitem.BacklogItemId;
import com.saasovation.agilepm.domain.model.product.backlogitem.BacklogItemType;
import com.saasovation.agilepm.domain.model.product.release.Release;
import com.saasovation.agilepm.domain.model.product.sprint.Sprint;
import com.saasovation.agilepm.domain.model.team.TeamId;
import com.saasovation.agilepm.domain.model.tenant.TenantId;
import com.saasovation.identityaccess.domain.model.identity.Tenant;
import com.saasovation.supply.AggregateRoot;
import com.saasovation.supply.FactoryMethod;

import java.util.Date;
import java.util.Set;

/**
 * Created by fw on 2019/3/20
 */
@AggregateRoot
public class Product extends ConcurrencySafeEntity {

    private Set<ProductBacklogItem> backlogItems;
    private String description;
    private String name;
    private ProductDiscussion productDiscussion;
    private ProductId productId;
    private TenantId tenantId;
    private TeamId teamId;


    public Product(TenantId tenantId, ProductId productId, String name, String description, ProductDiscussion productDiscussion) {
        this.description = description;
        this.name = name;
        this.productDiscussion = productDiscussion;
        this.productId = productId;
        this.tenantId = tenantId;

    }

    public TenantId tenantId() {
        return tenantId;
    }

    public ProductId productId() {
        return productId;
    }

    public Tenant tenant() {
        return null;
    }

    @FactoryMethod
    public BacklogItem planBacklogItem(){
        return new BacklogItem();
    }

    @FactoryMethod
    public Release sehduleRelease(){
        return null;
    }

    @FactoryMethod
    public Sprint sheduleSprint(){
        return null;
    }


    /** chapter 10 aggregate聚合 */
    /**
     * 找到一致性原则
     *  ·若一个待定项提交到了冲刺中，则不能将该待定项其从系统中移除。
     * 	·若一个冲刺中含有待定项．则不能将该冲刺从系统中移除。
     * 	·若一个发布中含有待定项，则不能将该发布从系统中移除。
     *  ·若一个待定项位于发布中，则不能将该待定项从系统中移除。
     *
     * */
    @FactoryMethod
    public BacklogItem planBacklogItem(
            String aSummary, String aCategory,
            BacklogItemType aType, StoryPoints aStoryPoints) {
            //...
        return null;
    }
    public void scheduleRelease(String aName, String aDescription, Date aBegins, Date anEnds) {
            //...
    }
    public void scheduleSprint(String aName, String aGoals, Date aBegins, Date anEnds) {
            //...
    }

    /** 使用迪米特法则和“告诉而非询问”原则 */
    public void reorderFrom(BacklogItemId anId, int anOrdering) {
        for (ProductBacklogItem pbi : this.backlogItems()) {
            pbi.reorderFrom(anId, anOrdering);
        }
    }
    public Set<ProductBacklogItem> backlogItems() {
        return this.backlogItems;
    }

    public void describeAs(){

    }

    public void initiateDiscussion(){

    }
    public void rename(){

    }

    /** chapter 10 aggregate聚合 */
}
