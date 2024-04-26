package ctu.cit.se.authenticationservice.migrations;

import java.util.List;

public interface IBaseInitData <T>{
    List<T> getInitData();
}
