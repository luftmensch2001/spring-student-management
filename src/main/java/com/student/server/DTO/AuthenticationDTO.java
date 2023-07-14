package com.student.server.DTO;

import com.student.server.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationDTO {
    User user;
    String accessToken;
}
