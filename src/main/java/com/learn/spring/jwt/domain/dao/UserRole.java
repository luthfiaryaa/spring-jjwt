package com.learn.spring.jwt.domain.dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "M_USER_ROLE")public class UserRole implements GrantedAuthority {

    private static final long serialVersionUID = -896978443561403016L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "role", nullable = false)
    private String role;

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    @Override
    public String getAuthority() {
        return role;
    }

}
