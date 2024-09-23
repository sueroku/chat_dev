package com.example.chat_dev.user.dto;

import com.example.exodia.user.domain.*;
import com.example.exodia.department.domain.Department;
import com.example.exodia.position.domain.Position;
import com.example.exodia.department.repository.DepartmentRepository;
import com.example.exodia.position.repository.PositionRepository;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

@Data
public class UserRegisterDto {
    private String userNum;
    private String profileImage;
    private String name;
    private String gender;
    private String status;
    private String password;
    private String email;
    private String address;
    private String phone;
    private String socialNum;
    private HireType hireType;
    private NowStatus nowStatus;
    private Long departmentId;
    private Long positionId;
    private int annualLeave;
}

