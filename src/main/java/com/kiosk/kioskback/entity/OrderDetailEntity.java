package com.kiosk.kioskback.entity;

import java.util.ArrayList;
import java.util.List;

import com.kiosk.kioskback.dto.request.order.PostOrderDetailDto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "OrderDetail")
@Table(name = "OrderDetail")
public class OrderDetailEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderDetailId;
    private int menuId;
    private int count;
    private int orderId;
    private int priceWithOption;

    public OrderDetailEntity(PostOrderDetailDto postOrderDetailDto, int orderId) {
        this.menuId = postOrderDetailDto.getMenuId();
        this.count = postOrderDetailDto.getMenuCount();
        this.priceWithOption = postOrderDetailDto.getPriceWithOption();
        this.orderId = orderId;
    }

    public static List<OrderDetailEntity> copyList(List<PostOrderDetailDto> postOrderDetailDtoList, int orderId){
        List<OrderDetailEntity> list = new ArrayList<>();

        for(PostOrderDetailDto postOrderDetailDto : postOrderDetailDtoList){
            OrderDetailEntity orderDetailEntity = new OrderDetailEntity(postOrderDetailDto, orderId);
            list.add(orderDetailEntity);
        }
        return list;
    }
    
}
