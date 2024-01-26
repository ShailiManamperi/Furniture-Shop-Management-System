package lk.ijse.shaili.system.Service.custom;


import lk.ijse.shaili.system.Dto.BestItemDTO;
import lk.ijse.shaili.system.Dto.ItemDTO;
import lk.ijse.shaili.system.Entity.Item;
import lk.ijse.shaili.system.Service.SuperService;
import lk.ijse.shaili.system.Service.exception.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface ItemService extends SuperService {
     ItemDTO saveItem(ItemDTO itemDTO) throws DuplicateException;

     ItemDTO updatItem(ItemDTO itemDTO) throws NotFoundException;

     List<ItemDTO> searchItemList(String text) throws NotFoundException;

     boolean deleteItem(String id) throws  NotFoundException;

     String generateNewItemId() throws SQLException;

     boolean searchDuplicate(String id) throws NotFoundException;

     List<ItemDTO> getAllItems();

     Optional<Item> searchItem(String id);

     ArrayList<String> loadItemId() throws SQLException, ClassNotFoundException;

     BestItemDTO findBestItem();


}
