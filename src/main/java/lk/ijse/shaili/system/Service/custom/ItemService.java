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
    public ItemDTO saveItem(ItemDTO itemDTO) throws DuplicateException;

    public ItemDTO updatItem(ItemDTO itemDTO) throws NotFoundException;

    public List<ItemDTO> searchItemList(String text) throws NotFoundException;

    public boolean deleteItem(String id) throws  NotFoundException;

    public String generateNewItemId() throws SQLException;

    public boolean searchDuplicate(String id) throws NotFoundException;

    public List<ItemDTO> getAllItems();

    public Optional<Item> searchItem(String id);

    public ArrayList<String> loadItemId() throws SQLException, ClassNotFoundException;

    public BestItemDTO findBestItem();

}
