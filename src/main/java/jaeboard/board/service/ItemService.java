package jaeboard.board.service;

import jaeboard.board.entity.item.Item;
import jaeboard.board.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class ItemService {

    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Transactional
    public void saveItem(Item item) {
        itemRepository.Save(item);
    }

    public List<Item> itemList() {
        return itemRepository.findAll();
    }

    public Item findItem(Item item) {
        return itemRepository.findOne(item.getId());
    }
}
