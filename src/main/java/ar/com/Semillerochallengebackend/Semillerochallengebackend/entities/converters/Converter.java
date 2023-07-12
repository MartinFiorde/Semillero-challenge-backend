package ar.com.Semillerochallengebackend.Semillerochallengebackend.entities.converters;

import java.util.ArrayList;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ParseException;

public abstract class Converter<E extends Object, D extends Object> {

    protected ModelMapper modelMapper;

    @Autowired
    public Converter() {
        this.modelMapper = new ModelMapper();
    }

    public abstract D entityToDto(E entity);

    public abstract E dtoToEntity(D dto) throws ParseException;

    public List<D> entitiesToDto(List<E> entities) {
        List<D> list = new ArrayList<>();
        entities.forEach((aux) -> list.add(entityToDto(aux)));
        return list;
    }

    public List<E> dtosToEntities(List<D> dtos) {
        List<E> list = new ArrayList<>();
        dtos.forEach((aux) -> list.add(dtoToEntity(aux)));
        return list;
    }
}
