package com.kiosk.kioskback.service.implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kiosk.kioskback.common.constants.ResponseMessage;
import com.kiosk.kioskback.dto.request.order.PostOrderDetailDto;
import com.kiosk.kioskback.dto.request.order.PostOrderDto;
import com.kiosk.kioskback.dto.response.ResponseDto;
import com.kiosk.kioskback.dto.response.order.DeleteOrderResponseDto;
import com.kiosk.kioskback.dto.response.order.GetOrderDetailResponseDto;
import com.kiosk.kioskback.dto.response.order.GetOrderResponseDto;
import com.kiosk.kioskback.dto.response.order.PostOrderResponseDto;
import com.kiosk.kioskback.entity.MenuEntity;
import com.kiosk.kioskback.entity.OptionEntity;
import com.kiosk.kioskback.entity.OrderDetailEntity;
import com.kiosk.kioskback.entity.OrderDetailOptionEntity;
import com.kiosk.kioskback.entity.OrderEntity;
import com.kiosk.kioskback.entity.StoreEntity;
import com.kiosk.kioskback.entity.UserEntity;
import com.kiosk.kioskback.repository.MenuRepository;
import com.kiosk.kioskback.repository.OptionRepository;
import com.kiosk.kioskback.repository.OrderDetailOptionRepository;
import com.kiosk.kioskback.repository.OrderDetailRepository;
import com.kiosk.kioskback.repository.OrderRepository;
import com.kiosk.kioskback.repository.StoreRepository;
import com.kiosk.kioskback.repository.UserRepository;
import com.kiosk.kioskback.service.OrderService;

@Service
public class OrderServiceImplements implements OrderService {

    @Autowired private OrderDetailRepository orderDetailRepository;
    @Autowired private OrderRepository orderRepository;
    @Autowired private MenuRepository menuRepository;
    @Autowired private OrderDetailOptionRepository orderDetailOptionRepository;
    @Autowired private OptionRepository optionRepository;
    @Autowired private UserRepository userRepository;
    @Autowired private StoreRepository storeRepository;

    public ResponseDto<List<GetOrderResponseDto>> getOrderList(String userId, int storeId){
        List<GetOrderResponseDto> data = null;

        try {

            UserEntity userEntity = userRepository.findByUserId(userId);
            if(userEntity == null) return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_USER_ID);
            if(!userEntity.isAdmin()) return ResponseDto.setFailed(ResponseMessage.NOT_ADMIN);

            StoreEntity storeEntity = storeRepository.findByStoreId(storeId);
            // todo : storeEntity == null 확인
            boolean isEqualUserId = userId.equals(storeEntity.getUserId());
            if(!isEqualUserId) return ResponseDto.setFailed(ResponseMessage.NOT_PERMISSION);

            List<OrderEntity> orderEntityList = orderRepository.findByStoreIdAndOrderState(storeId, true);

            data = GetOrderResponseDto.copyList(orderEntityList);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    public ResponseDto<List<GetOrderDetailResponseDto>> getOrderDetailList(String userId, int orderId){
        List<GetOrderDetailResponseDto> data = new ArrayList<>();

        try {
            UserEntity userEntity = userRepository.findByUserId(userId);
            if(userEntity == null) return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_USER_ID);
            if(!userEntity.isAdmin()) return ResponseDto.setFailed(ResponseMessage.NOT_ADMIN);

            OrderEntity orderEntity = orderRepository.findByOrderId(orderId);
            // todo : orderEntity null 확인
            int storeId = orderEntity.getStoreId();
            StoreEntity storeEntity = storeRepository.findByStoreId(storeId);
            // todo : storeEntity == null 확인
            boolean isEqualUserId = userId.equals(storeEntity.getUserId());
            if(!isEqualUserId) return ResponseDto.setFailed(ResponseMessage.NOT_PERMISSION);


            List<OrderDetailEntity> orderDetailEntityList = orderDetailRepository.findByOrderId(orderId);
            // todo : 맞는지 좀 더 확인 해볼 것 (강사님에게 문의 해볼 것)
            for(OrderDetailEntity orderDetailEntity : orderDetailEntityList){
                int menuId = orderDetailEntity.getMenuId();
                MenuEntity menuEntity = menuRepository.findByMenuId(menuId);
                String menuName = menuEntity.getMenuName();
                int count = orderDetailEntity.getCount();
                int orderDetailId = orderDetailEntity.getOrderDetailId();
                List<OrderDetailOptionEntity> orderDetailOptionEntityList = orderDetailOptionRepository.findByOrderDetailId(orderDetailId);
                List<String> optionList = new ArrayList<>();
                for(OrderDetailOptionEntity orderDetailOptionEntity : orderDetailOptionEntityList){
                    int optionId = orderDetailOptionEntity.getOptionId();
                    OptionEntity optionEntity = optionRepository.findByOptionId(optionId);
                    optionList.add(optionEntity.getOptionName());
                }
                GetOrderDetailResponseDto getOrderDetailResponseDto = new GetOrderDetailResponseDto(menuName, count , optionList);
                data.add(getOrderDetailResponseDto);
            }

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    public ResponseDto<PostOrderResponseDto> postOrder(PostOrderDto dto) {

        PostOrderResponseDto data = null;
        int storeId = dto.getStoreId();
        int totalPrice = dto.getTotalPrice();
        String orderState = dto.getOrderState();
        List<PostOrderDetailDto> postOrderDetailDtoList = dto.getOrderDetailList();
        try {

            OrderEntity orderEntity = new OrderEntity(storeId, totalPrice, orderState);
            orderEntity = orderRepository.save(orderEntity);
            int orderId = orderEntity.getOrderId();

            List<OrderDetailOptionEntity> orderDetailOptionEntities = new ArrayList<>();

            for(PostOrderDetailDto postOrderDetailDto : postOrderDetailDtoList){
                OrderDetailEntity orderDetailEntity = new OrderDetailEntity(postOrderDetailDto, orderId);
                orderDetailEntity = orderDetailRepository.save(orderDetailEntity);
                int orderDetailId = orderDetailEntity.getOrderDetailId();
                List<Integer> optionList = postOrderDetailDto.getOptionList();
                for(Integer optionId : optionList){
                    OrderDetailOptionEntity orderDetailOptionEntity = new OrderDetailOptionEntity(orderDetailId, optionId);
                    orderDetailOptionEntities.add(orderDetailOptionEntity);
                }
            }
            orderDetailOptionRepository.saveAll(orderDetailOptionEntities);

            data = new PostOrderResponseDto(true);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    public ResponseDto<DeleteOrderResponseDto> deleteOrderDetail(int orderDetailId) {
        
        DeleteOrderResponseDto data = null;

        try {

            OrderDetailEntity orderDetailEntity = orderDetailRepository.findByOrderDetailId(orderDetailId);
            if (orderDetailEntity == null) return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_ORDER_DETAIL_ID);

            orderDetailRepository.delete(orderDetailEntity);

            data = new DeleteOrderResponseDto();

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }
    
}
