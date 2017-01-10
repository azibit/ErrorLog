/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.azibit.errorlog.utils;

import com.azibit.errorlog.dtos.UserDto;
import com.azibit.errorlog.entities.User;

/**
 *
 * @author azibit
 */


public class Mapper {
    
    public static User mapToUser(UserDto userDto){
        User user = new User();
        user.setEmail(userDto.getEmail());
        user.setUserId(userDto.getUserName());
        user.setPassword(userDto.getPassword());
        
        return user;
    }
    
    public static UserDto mapToUserDto(User user){
        UserDto dto = new UserDto();
        
        dto.setEmail(user.getEmail());
        dto.setPassword(user.getPassword());
        dto.setUserName(user.getUserId());
        
        return dto;
    }
}
