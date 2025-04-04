package ems.controllers;

import ems.models.Metadata;
import ems.services.MetadataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/metadata")
public class MetadataController {

    @Autowired
    private MetadataService metadataService;

    @GetMapping
    public List<Metadata> getAllMetadatas() {
        return metadataService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Metadata> getMetadataById(@PathVariable Long id) {
        Metadata metadata = metadataService.findById(id);
        if (metadata == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(metadata, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Metadata> createMetadata(@RequestBody Metadata metadata) {
        Metadata created = metadataService.save(metadata);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Metadata> updateMetadata(@PathVariable Long id, @RequestBody Metadata metadata) {
        Metadata updated = metadataService.save(metadata);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMetadata(@PathVariable Long id) {
        metadataService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/findBySerialnumber")
    public List<Metadata> findBySerialnumber(@RequestParam String serialNumber) {
        return metadataService.findBySerialnumber(serialNumber);
    }

    @GetMapping("/findByTimeBetween")
    public List<Metadata> findByTimeBetween(@RequestParam LocalDateTime start, @RequestParam LocalDateTime end) {
        return metadataService.findByTimeBetween(start, end);
    }

    @GetMapping("/findByData")
    public List<Metadata> findByData(@RequestParam String data) {
        return metadataService.findByData(data);
    }

    @GetMapping("/findByPairingstatus")
    public List<Metadata> findByPairingstatus(@RequestParam Boolean pairingStatus) {
        return metadataService.findByPairingstatus(pairingStatus);
    }

    @GetMapping("/findByStatsmappingversion")
    public List<Metadata> findByStatsmappingversion(@RequestParam String statsMappingVersion) {
        return metadataService.findByStatsmappingversion(statsMappingVersion);
    }

}