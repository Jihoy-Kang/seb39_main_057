package com.courseori.server.item.service;

import com.courseori.server.item.entity.Item;
import com.courseori.server.item.repository.ItemRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ItemService {

    private ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public void deleteItem(long itemId) {
        Item foundItem = findVerifiedItem(itemId);
        itemRepository.delete(foundItem);
    }

    public Item findItem(long itemId) {
        return findVerifiedItem(itemId); //null 처리 필요할 듯
    }

    public Page<Item> findItems(int page, int size) {
        return itemRepository.findAll(PageRequest.of(page, size, Sort.by("itemId").descending()));
    }

    public Item findVerifiedItem(long itemId) {
        Optional<Item> optionalItem =
                itemRepository.findById(itemId);
        Item foundItem =
                optionalItem.orElseThrow(() -> new NoSuchElementException());

        return foundItem;
    }

}
