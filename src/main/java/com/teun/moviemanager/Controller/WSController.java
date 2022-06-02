package com.teun.moviemanager.Controller;

import com.teun.moviemanager.DTO.UpdateMessage;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WSController {
    @PostMapping("/send-update")
    @SendTo("/topic/updatemovie")
    public UpdateMessage sendUpdate(@RequestBody UpdateMessage updateMessage){
        UpdateMessage response = new UpdateMessage();
        response.setTitel("Testing movie update");
        response.setContent(updateMessage.getContent());

        return response;
    }
}
