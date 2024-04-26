package ctu.cit.se.authenticationservice.daos;

import java.util.List;

public interface IBaseDAO <CreateDTO, UpdateDTO, RetrieveDTO, CommandDTO, ID>{
    List<RetrieveDTO> list();
    CommandDTO create(CreateDTO createDTO);
    CommandDTO update(UpdateDTO updateDTO);
    RetrieveDTO retrieve(ID id);
    void delete(ID id);
}
