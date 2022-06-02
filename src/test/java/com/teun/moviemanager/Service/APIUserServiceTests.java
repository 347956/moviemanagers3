package com.teun.moviemanager.Service;

import com.teun.moviemanager.DTO.UserDTO;
import com.teun.moviemanager.Models.APIUser;
import com.teun.moviemanager.Repositories.IUserRepository;
import com.teun.moviemanager.Services.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class APIUserServiceTests {
    @Mock
    private IUserRepository userRepositoryMock;
    @Mock
    private BCryptPasswordEncoder encoder;
    @InjectMocks
    private UserService userService;

    @Test
    void createUserTest_CreatesAUserAndChecksIfADTOIsSentBack_ShouldReturnTrue(){
        //assign
        UserDTO userInputDTO = new UserDTO();
        userInputDTO.setUserName("Mockito");
        userInputDTO.setName("Otikcom");
        userInputDTO.setEmail("Mockito@mockmail.com");
        userInputDTO.setPassword("Mock");
        APIUser APIUserMock = new APIUser();
        APIUserMock.setUserName("Mockito");
        APIUserMock.setName("Otikcom");
        APIUserMock.setEmail("Mockito@mockmail.com");
        APIUserMock.setPassword("Mock");
        UserDTO userDTOReturned = new UserDTO();
        //act
        Mockito.when(userRepositoryMock.save(any(APIUser.class))).thenReturn(APIUserMock);
        userDTOReturned = userService.CreateUser(userInputDTO);
        //assert
        assert(userDTOReturned.getName() == userInputDTO.getName() && userDTOReturned.getName() == APIUserMock.getName());
    }
}
