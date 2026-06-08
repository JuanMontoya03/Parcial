package com.saberpro.config;

import com.saberpro.model.Facultad;
import com.saberpro.repository.FacultadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class FacultadConverter implements Converter<String, Facultad> {

    @Autowired
    private FacultadRepository facultadRepository;

    @Override
    public Facultad convert(String source) {
        if (source == null || source.isBlank()) return null;
        try {
            Long id = Long.parseLong(source);
            return facultadRepository.findById(id).orElse(null);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
