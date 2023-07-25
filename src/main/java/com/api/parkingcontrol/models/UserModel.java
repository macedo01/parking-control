package com.api.parkingcontrol.models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "TB_USER")
public class UserModel implements UserDetails, Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id; //UUID usado para caso de ter uma arquitetura de microserviços com varios dados destruibidos talvez de conflitos em id sequenciais

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String passoword;

    @ManyToMany
    @JoinTable(name = "TB_USERS_ROLES",
    joinColumns = @JoinColumn(name = "user_id"),
    inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<RoleModel> roles; // tabela de assitencia

    @Column(nullable = false)
    private boolean isAccountNonExpired; // se a conta expirar e tiver que ser feito algum procedimento para tal

    @Column(nullable = false)
    private boolean isAccountNonLocked; // conta nao expirou mas foi blockeada por algum motivo

    @Column(nullable = false)
    private boolean isCredentialsNonExpired; // tem que trocar as crendenciais

    @Column(nullable = false)
    private boolean isEnabled; // ativa ou nao ativa

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles;
    }

    @Override
    public String getPassword() {
        return this.passoword;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return this.isEnabled;
    }
}
