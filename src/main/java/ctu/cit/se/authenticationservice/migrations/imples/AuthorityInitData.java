package ctu.cit.se.authenticationservice.migrations.imples;

import ctu.cit.se.authenticationservice.entities.CustomGrantedAuthority;
import ctu.cit.se.authenticationservice.entities.Project;
import ctu.cit.se.authenticationservice.migrations.ifaces.IAuthorityInitData;
import ctu.cit.se.authenticationservice.repositories.IProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class AuthorityInitData implements IAuthorityInitData {
    @Autowired
    private IProjectRepository projectRepository;

    @Override
    public List<CustomGrantedAuthority> getInitData() {
        Project project = projectRepository.findByName("Default Project - Testing as purpose").orElse(null);
        List<CustomGrantedAuthority> authorities = new ArrayList<>();
        if (Objects.nonNull(project)) {
            authorities = List.of(
                    /* USER */
                    CustomGrantedAuthority.builder().projectOfAuthorities(project).authority("user-create").description("Tạo tài khoản").build(),
                    CustomGrantedAuthority.builder().projectOfAuthorities(project).authority("user-retrieve").description("Xem tài khoản").build(),
                    CustomGrantedAuthority.builder().projectOfAuthorities(project).authority("user-delete").description("Xóa tài khoản").build(),
                    CustomGrantedAuthority.builder().projectOfAuthorities(project).authority("user-update").description("Cập nhật tài khoản").build(),
                    /* ROLE */
                    CustomGrantedAuthority.builder().projectOfAuthorities(project).authority("role-create").description("Tạo vai trò").build(),
                    CustomGrantedAuthority.builder().projectOfAuthorities(project).authority("role-retrieve").description("Xem vai trò").build(),
                    CustomGrantedAuthority.builder().projectOfAuthorities(project).authority("role-delete").description("Xóa vai trò").build(),
                    CustomGrantedAuthority.builder().projectOfAuthorities(project).authority("role-update").description("Cập nhật vai trò").build(),
                    /* AUTHORITY */
                    CustomGrantedAuthority.builder().projectOfAuthorities(project).authority("authority-create").description("Tạo quyền").build(),
                    CustomGrantedAuthority.builder().projectOfAuthorities(project).authority("authority-retrieve").description("Xem quyền").build(),
                    CustomGrantedAuthority.builder().projectOfAuthorities(project).authority("authority-delete").description("Xóa quyền").build(),
                    CustomGrantedAuthority.builder().projectOfAuthorities(project).authority("authority-update").description("Cập nhật quyền").build()
            );
        }

        return authorities;
    }
}
