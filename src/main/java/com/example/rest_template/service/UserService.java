package com.example.rest_template.service;

import com.example.rest_template.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    @Value("${spring.external.service.base-url}")
    private String baseUrl;

    private final RestTemplate restTemplate;
    public List<UserDTO> getUsers() {
        UserDTO[] response = restTemplate.getForObject(baseUrl+"/users", UserDTO[].class);

        return Arrays.asList(response);
    }

    public ResponseEntity<UserDTO> saveUser(UserDTO userDTO) {
        String url = baseUrl + "/users";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<UserDTO> request = new HttpEntity<>(userDTO, headers);

        ResponseEntity<UserDTO> response = restTemplate.exchange(url, HttpMethod.POST, request, UserDTO.class);

        return response;
    }

    public void updateUser(Integer id, UserDTO userDTO) {
        restTemplate.put(baseUrl + "/users/" + id, userDTO);
    }

    public void deleteUser(Integer id) {
        restTemplate.delete(baseUrl + "/users/" + id);
    }
}
