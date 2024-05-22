package ctu.cit.se.authenticationservice.daos.imples;

import ctu.cit.se.authenticationservice.daos.ifaces.ICustomUserDAO;
import ctu.cit.se.authenticationservice.dtos.others.CommandDTO;
import ctu.cit.se.authenticationservice.dtos.users.CreateUserReqDTO;
import ctu.cit.se.authenticationservice.dtos.users.RetrieveUserResDTO;
import ctu.cit.se.authenticationservice.dtos.users.UpdateUserReqDTO;
import ctu.cit.se.authenticationservice.entities.CustomUser;
import ctu.cit.se.authenticationservice.exceptions.messages.CustomExceptionMessage;
import ctu.cit.se.authenticationservice.mappers.IBaseMapper;
import ctu.cit.se.authenticationservice.repositories.ICustomUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CustomUserDAO implements ICustomUserDAO {
    @Autowired
    private ICustomUserRepository userRepository;
    @Autowired
    private IBaseMapper<CreateUserReqDTO, CustomUser> createMapper;
    @Autowired
    private IBaseMapper<UpdateUserReqDTO, CustomUser> updateMapper;
    @Autowired
    private IBaseMapper<CustomUser, RetrieveUserResDTO> retrieveMapper;

    @Override
    public List<RetrieveUserResDTO> list() {
        return userRepository.findAll().stream().map(user -> retrieveMapper.convert(user)).collect(Collectors.toList());
    }

    @Override
    public CommandDTO create(CreateUserReqDTO createUserReqDTO) {
        CustomUser createdUser = userRepository.save(createMapper.convert(createUserReqDTO));
        return CommandDTO.builder().id(createdUser.getId().toString()).build();
    }

    @Override
    public CommandDTO update(UpdateUserReqDTO updateUserReqDTO) {
        CustomUser updatedUser = userRepository.save(updateMapper.convert(updateUserReqDTO));
        return CommandDTO.builder().id(updatedUser.getId().toString()).build();
    }

    @Override
    public RetrieveUserResDTO retrieve(UUID uuid) {
        CustomUser retrieveUser = userRepository.findById(uuid).orElseThrow(
                () -> new IllegalArgumentException(CustomExceptionMessage.USER_NOT_FOUND)
        );
        return retrieveMapper.convert(retrieveUser);
    }

    @Override
    public void delete(UUID uuid) {
        CustomUser retrieveUser = userRepository.findById(uuid).orElseThrow(
                () -> new IllegalArgumentException(CustomExceptionMessage.USER_NOT_FOUND)
        );
        userRepository.delete(retrieveUser);
    }
}
