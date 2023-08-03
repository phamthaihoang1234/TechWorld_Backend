package com.example.TechWorld.apiHandle;


import com.example.TechWorld.model.Category;
import com.example.TechWorld.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/categories")
public class CategoryApiHandle {
    @Autowired
    CategoryRepository repo;

    @GetMapping
    public ResponseEntity<List<Category>> getAll() {
        return ResponseEntity.ok(repo.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<Category> getById(@PathVariable("id") Long id) {
        if (!repo.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(repo.findById(id).get());
    }

    @PostMapping
    public ResponseEntity<Category> post(@RequestBody Category category) {
        if (repo.existsById(category.getCategoryId())) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(repo.save(category));
    }

    @PutMapping("{id}")
    public ResponseEntity<Category> put(@RequestBody Category category, @PathVariable("id") Long id) {
        if (!id.equals(category.getCategoryId())) {
            return ResponseEntity.badRequest().build();
        }
        if (!repo.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(repo.save(category));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        if (!repo.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        repo.deleteById(id);
        return ResponseEntity.ok().build();
    }


}
