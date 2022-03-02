package tr.com.hepsiburada.core.mapper;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class DTOMapper {

    private final ModelMapper modelMapper;

    public <M extends Object> M mapModel(Object source, Class<M> target) {
        return modelMapper.map(source, target);
    }

    public <S, T> List<T> mapListModel(List<S> source, Class<T> targetClass) {
        return source
                .stream()
                .map(element -> mapModel(element, targetClass))
                .collect(Collectors.toList());
    }

    public <S, T> Set<T> mapSetModel(Set<S> source, Class<T> targetClass) {
        return source
                .stream()
                .map(element -> mapModel(element, targetClass))
                .collect(Collectors.toSet());
    }

}
