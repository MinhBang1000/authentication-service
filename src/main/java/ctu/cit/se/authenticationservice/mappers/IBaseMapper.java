package ctu.cit.se.authenticationservice.mappers;

public interface IBaseMapper <S,D>{
    D convert(S source);
}

