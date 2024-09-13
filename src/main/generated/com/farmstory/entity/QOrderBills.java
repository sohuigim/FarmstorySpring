package com.farmstory.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QOrderBills is a Querydsl query type for OrderBills
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QOrderBills extends EntityPathBase<OrderBills> {

    private static final long serialVersionUID = -1827009267L;

    public static final QOrderBills orderBills = new QOrderBills("orderBills");

    public final NumberPath<Integer> billsItemCount = createNumber("billsItemCount", Integer.class);

    public final NumberPath<Integer> billsItemDiscount = createNumber("billsItemDiscount", Integer.class);

    public final NumberPath<Integer> billsItemNo = createNumber("billsItemNo", Integer.class);

    public final NumberPath<Integer> billsItemPrice = createNumber("billsItemPrice", Integer.class);

    public final NumberPath<Integer> OrderNo = createNumber("OrderNo", Integer.class);

    public final NumberPath<Integer> ProdNo = createNumber("ProdNo", Integer.class);

    public QOrderBills(String variable) {
        super(OrderBills.class, forVariable(variable));
    }

    public QOrderBills(Path<? extends OrderBills> path) {
        super(path.getType(), path.getMetadata());
    }

    public QOrderBills(PathMetadata metadata) {
        super(OrderBills.class, metadata);
    }

}

