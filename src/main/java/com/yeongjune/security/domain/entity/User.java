package com.yeongjune.security.domain.entity;

import com.yeongjune.security.jpa.JpaBasePersistable;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@Entity
@Table(name = "tbl_user")
@AttributeOverride(name = "id", column = @Column(name = "user_id"))
@DynamicUpdate
public class User extends JpaBasePersistable implements Serializable {

    @Column(nullable = false)
    private String userName;

    @Column(nullable = false)
    private String password;

    private int active;

    private String roles = "";

    private String permissions = "";

    public User(String userName, String password, String roles, String permissions) {
        this.userName = userName;
        this.password = password;
        this.roles = roles;
        this.permissions = permissions;

        this.active = 1;
    }

    public List<String> getRoleList(){
        if(this.roles.length()>0){
            return Arrays.asList(this.roles.split(","));
        }

        return new ArrayList<>();
    }

    public List<String> getPermissionList(){
        if(this.permissions.length()>0){
            return Arrays.asList(this.permissions.split(","));
        }

        return new ArrayList<>();
    }
}
