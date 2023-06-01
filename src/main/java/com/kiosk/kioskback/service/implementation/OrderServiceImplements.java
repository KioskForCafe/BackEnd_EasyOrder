package com.kiosk.kioskback.service.implementation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kiosk.kioskback.common.constants.ResponseMessage;
import com.kiosk.kioskback.dto.request.order.PatchOrderDto;
import com.kiosk.kioskback.dto.request.order.PostOrderDetailDto;
import com.kiosk.kioskback.dto.request.order.PostOrderDetailLogDto;
import com.kiosk.kioskback.dto.request.order.PostOrderDetailOptionDto;
import com.kiosk.kioskback.dto.request.order.PostOrderDto;
import com.kiosk.kioskback.dto.request.order.PostOrderLogDto;
import com.kiosk.kioskback.dto.response.ResponseDto;
import com.kiosk.kioskback.dto.response.order.DeleteOrderResponseDto;
import com.kiosk.kioskback.dto.response.order.GetOrderDetailResponseDto;
import com.kiosk.kioskback.dto.response.order.GetOrderResponseDto;
import com.kiosk.kioskback.dto.response.order.PatchOrderResponseDto;
import com.kiosk.kioskback.dto.response.order.PostOrderLogResponseDto;
import com.kiosk.kioskback.dto.response.order.PostOrderResponseDto;
import com.kiosk.kioskback.entity.CategoryEntity;
import com.kiosk.kioskback.entity.MenuEntity;
import com.kiosk.kioskback.entity.OptionEntity;
import com.kiosk.kioskback.entity.OptionLogEntity;
import com.kiosk.kioskback.entity.OrderDetailEntity;
import com.kiosk.kioskback.entity.OrderDetailLogEntity;
import com.kiosk.kioskback.entity.OrderDetailOptionEntity;
import com.kiosk.kioskback.entity.OrderEntity;
import com.kiosk.kioskback.entity.OrderLogEntity;
import com.kiosk.kioskback.entity.StoreEntity;
import com.kiosk.kioskback.entity.UserEntity;
import com.kiosk.kioskback.repository.CategoryRepository;
import com.kiosk.kioskback.repository.MenuRepository;
import com.kiosk.kioskback.repository.OptionLogRepository;
import com.kiosk.kioskback.repository.OptionRepository;
import com.kiosk.kioskback.repository.OrderDetailLogRepository;
import com.kiosk.kioskback.repository.OrderDetailOptionRepository;
import com.kiosk.kioskback.repository.OrderDetailRepository;
import com.kiosk.kioskback.repository.OrderLogRepository;
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
    @Autowired private OrderDetailLogRepository orderdetailLogRepository;
    @Autowired private OrderLogRepository orderLogRepository;
    @Autowired private OptionLogRepository optionLogRepository;
    @Autowired private CategoryRepository categoryRepository;

    public ResponseDto<List<GetOrderResponseDto>> getOrderList(String userId, int storeId, String orderState){
        List<GetOrderResponseDto> data = null;

        try {

            UserEntity userEntity = userRepository.findByUserId(userId);
            if(userEntity == null) return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_USER_ID);

            StoreEntity storeEntity = storeRepository.findByStoreId(storeId);
            if(storeEntity == null) return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_STORE);

            List<OrderEntity> orderEntityList;

            if(orderState != null){
                if(!userEntity.isAdmin()) return ResponseDto.setFailed(ResponseMessage.NOT_ADMIN);
                
                boolean isEqualUserId = userId.equals(storeEntity.getUserId());
                if(!isEqualUserId) return ResponseDto.setFailed(ResponseMessage.NOT_PERMISSION);

                orderEntityList = orderRepository.findByStoreIdAndOrderState(storeId, orderState);
            }else{
                orderEntityList = orderRepository.findByStoreId(storeId);
            }
            
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
            if(orderEntity==null) return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_ORDER);

            int storeId = orderEntity.getStoreId();
            StoreEntity storeEntity = storeRepository.findByStoreId(storeId);
            if(storeEntity==null) return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_STORE);
            
            boolean isEqualUserId = userId.equals(storeEntity.getUserId());
            if(!isEqualUserId) return ResponseDto.setFailed(ResponseMessage.NOT_PERMISSION);


            List<OrderDetailEntity> orderDetailEntityList = orderDetailRepository.findByOrderId(orderId);
            for(OrderDetailEntity orderDetailEntity : orderDetailEntityList){
                int menuId = orderDetailEntity.getMenuId();
                int orderDetailId = orderDetailEntity.getOrderDetailId();
                MenuEntity menuEntity = menuRepository.findByMenuId(menuId);
                int categoryId = menuEntity.getCategoryId();
                CategoryEntity categoryEntity = categoryRepository.findByCategoryId(categoryId);
                String categoryName = categoryEntity.getCategoryName();
                List<OrderDetailOptionEntity> orderDetailOptionEntityList = orderDetailOptionRepository.findByOrderDetailId(orderDetailId);
                List<OptionEntity> optionList = new ArrayList<>();
                for(OrderDetailOptionEntity orderDetailOptionEntity : orderDetailOptionEntityList){
                    int optionId = orderDetailOptionEntity.getOptionId();
                    OptionEntity optionEntity = optionRepository.findByOptionId(optionId);
                    optionList.add(optionEntity);
                }
                GetOrderDetailResponseDto getOrderDetailResponseDto = new GetOrderDetailResponseDto(orderDetailEntity, menuEntity, categoryName , optionList);
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

    // 
    public ResponseDto<PostOrderLogResponseDto> postOrderLog(String userId, PostOrderLogDto dto) {

        PostOrderLogResponseDto data = null;
        List<PostOrderDetailLogDto> detailLogList = dto.getOrderDetail();
        System.out.println(dto);

        try {
            OrderLogEntity orderLogEntity = new OrderLogEntity(dto);
            orderLogEntity = orderLogRepository.save(orderLogEntity);
            int orderLogId = orderLogEntity.getOrderLogId();
            Date orderLogDate = orderLogEntity.getCreatedAt();
            
            for(PostOrderDetailLogDto postOrderDetailLogDto : detailLogList) {
                OrderDetailLogEntity orderDetailLogEntity = new OrderDetailLogEntity(postOrderDetailLogDto, orderLogId, orderLogDate);
                orderDetailLogEntity = orderdetailLogRepository.save(orderDetailLogEntity);
                int orderDetailLogId = orderDetailLogEntity.getOrderDetailLogId();
                List<PostOrderDetailOptionDto> optionList = postOrderDetailLogDto.getOptionList();
                for(PostOrderDetailOptionDto postOrderDetailOptionDto : optionList){
                    OptionLogEntity optionLogEntity = new OptionLogEntity(postOrderDetailOptionDto, orderDetailLogId);
                    optionLogRepository.save(optionLogEntity);
                }
            }

            data = new PostOrderLogResponseDto(true);
            
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

    @Override
    public ResponseDto<List<PatchOrderResponseDto>> patchOrder(String userId, PatchOrderDto dto) {

        List<PatchOrderResponseDto> data = null;
        int orderId = dto.getOrderId();
        System.out.println(dto.getOrderState());

        try {
            OrderEntity orderEntity = orderRepository.findByOrderId(orderId);
            if(orderEntity == null) return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_ORDER);

            int storeId = orderEntity.getStoreId();
            String orderState = orderEntity.getOrderState();

            orderEntity.patch(dto);
            orderRepository.save(orderEntity);

            List<OrderEntity> orderEntityList = orderRepository.findByStoreIdAndOrderState(storeId, orderState);

            
            data = PatchOrderResponseDto.copyList(orderEntityList);
            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }
    
}
