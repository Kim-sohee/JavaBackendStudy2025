package com.sinse.electroshop.model.store;

import com.sinse.electroshop.domain.Store;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class JpaStoreDAO implements StoreDAO{

    private final StoreRepository storeRepository;

    @Override
    public Store save(Store store) {
        return storeRepository.save(store);
    }

    @Override
    public Store findById(int storeId) {
        return null;
    }

    @Override
    public List<Store> findAll() {
        return List.of();
    }

    @Override
    public Store update(Store store) {
        return null;
    }

    @Override
    public void deleteById(int storeId) {

    }

    @Override
    public Store login(Store store) {
        return storeRepository.findByBusinessIdAndPassword(store.getBusinessId(), store.getPassword());
    }
}
