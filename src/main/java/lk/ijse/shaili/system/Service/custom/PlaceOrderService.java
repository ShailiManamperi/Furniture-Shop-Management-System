package lk.ijse.shaili.system.Service.custom;

import lk.ijse.shaili.system.Dto.DeliveryDTO;
import lk.ijse.shaili.system.Dto.PlaceOrderDTO;
import lk.ijse.shaili.system.Service.SuperService;

import java.sql.SQLException;

public interface PlaceOrderService extends SuperService {
    public boolean PlaceOrderWithDelivery(PlaceOrderDTO placeOrder, DeliveryDTO deliveryDTO) throws SQLException,ClassNotFoundException;

    public boolean PlaceOrderWithoutDelivery(PlaceOrderDTO placeOrder) throws SQLException,ClassNotFoundException;
}
