package com.saasovation.agilepm.application.product;

import com.saasovation.agilepm.domain.model.product.Product;
import com.saasovation.agilepm.domain.model.product.ProductId;
import com.saasovation.agilepm.domain.model.product.ProductRepository;
import com.saasovation.agilepm.domain.model.product.StoryPoints;
import com.saasovation.agilepm.domain.model.product.backlogitem.*;
import com.saasovation.agilepm.domain.model.team.Team;
import com.saasovation.agilepm.domain.model.team.TeamMemberId;
import com.saasovation.agilepm.domain.model.tenant.TenantId;
import com.saasovation.agilepm.infrastructure.persistence.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by fw on 2019/8/26
 */
public class ProductBacklogItemService {


    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private BacklogItemRepository backlogItemRepository;
    @Autowired
    private TeamRepository teamRepository;

    /** chapter 10 aggregate  */

    @Transactional(rollbackFor = Exception.class)
    public void planProductBacklogItem(
            String aTenantId, String aProductId,
            String aSummary, String aCategory,
            String aBacklogItemType, String aStoryPoints) {

        Product product =
                productRepository
                        .productOfId(new TenantId(aTenantId),new ProductId(aProductId));
        BacklogItem plannedBacklogItem =
                product.planBacklogItem(
                        aSummary,
                        aCategory,
                        BacklogItemType.valueOf(aBacklogItemType),
                        StoryPoints.valueOf(aStoryPoints));
        backlogItemRepository.add(plannedBacklogItem);
    }

    @Transactional(rollbackFor = Exception.class)
    public void assignTeamMemberToTask(
            String aTenantId,
            String aBacklogItemId,
            String aTaskId,
            String aTeamMemberId) {

        BacklogItem backlogItem =
                backlogItemRepository.backlogItemOfId(
                        new TenantId(aTenantId),
                        new BacklogItemId(aBacklogItemId));
        Team ofTeam =
                teamRepository.teamOfId(
                        backlogItem.tenantId(),
                        backlogItem.teamId());
        backlogItem.assignTeamMemberToTask(
                new TeamMemberId(aTeamMemberId),
                ofTeam,
                new TaskId(aTaskId));
    }

    /** 单个事务中更新多个聚合实例:情况一，方便用户界面 */
    @Transactional(rollbackFor = Exception.class)
    public void planBatchOfProductBacklogItems(
            String aTenantId, String productId,
            BacklogItemDescription[] aDescriptions) {

        // 出于方便考虑，用户界面可能允许用户一次性地给多个对象定义共有的属性，然后再对它们进行批量处理。
        // 如，在 Scrum中，团队成员可能会一次性地创建多个待定项。在用户界面中，他们可先填入那些公有的待定项属性，
        // 然后再分别填入各个待定项的特有属性。所有的待定项将一次性地进行处理

        Product product =
                productRepository.productOfId(
                        new TenantId(aTenantId),
                        new ProductId(productId));
        for (BacklogItemDescription desc : aDescriptions) {
            BacklogItem plannedBacklogItem =
                    product.planBacklogItem(
                            desc.summary(),
                            desc.category(),
                            BacklogItemType.valueOf(
                                    desc.backlogItemType()),
                            StoryPoints.valueOf(
                                    desc.storyPoints()));
            backlogItemRepository.add(plannedBacklogItem);
        }
    }

    /** chapter 10 aggregate  */
}
