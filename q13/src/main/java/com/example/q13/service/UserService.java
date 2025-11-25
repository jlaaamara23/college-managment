package com.example.q13.service;

import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
@Service
public class UserService {
private List<String> users = new ArrayList<>();
public String createUser(String name) {
String processedName = name.toUpperCase();
users.add(processedName);
return processedName;
}
public List<String> getAllUsers() {
return users;
}
}