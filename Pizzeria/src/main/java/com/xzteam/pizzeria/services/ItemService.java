package com.xzteam.pizzeria.services;

import com.xzteam.pizzeria.domain.Item;
import com.xzteam.pizzeria.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class ItemService {
    private static final Logger log = Logger.getLogger(ItemService.class.getName());
    @Autowired
    ItemRepository itemRepository;

    public List<Item> getAll() {
        return itemRepository.findAll();
    }

    public Item getItemById(Long id) {
        return itemRepository.findOne(id);
    }

    public Item addItem(Item item) {
        log.info("Adding item with id " + item.getId());
        return itemRepository.save(item);
    }

    public void deleteItem(Long id) {
        Item item = itemRepository.findOne(id);
        if (item != null) {
            log.info("Deleting item with id " + item.getId());
            itemRepository.delete(item);
        }
    }

}
