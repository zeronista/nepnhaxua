package com.dev.thucduong.dto;

import com.dev.thucduong.model.User;
import lombok.Data;
import java.util.List;

@Data
public class UserDTO {
    private String id;
    private String email;
    private String password;
    private String fullName;
    private List<User.Address> addresses;
    private List<String> orderIds;
    private String role;
}