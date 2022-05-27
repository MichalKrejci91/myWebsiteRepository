package com.sslithranox.service;

import com.sslithranox.entity.Artwork;
import com.sslithranox.repository.ArtworkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ArtworkService {

    @Autowired
    private ArtworkRepository repository;

    public List<Artwork> listAll() {
        return repository.findAll();
    }

    public void save(Artwork artwork) {
        repository.save(artwork);
    }

    public Artwork get(long id) {
        return repository.findById(id).get();
    }

    public void delete(long id) {
        repository.deleteById(id);
    }
}
