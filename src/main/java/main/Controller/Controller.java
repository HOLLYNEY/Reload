package main.Controller;

import main.SQL.SQL;
import main.User.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;

@RequestMapping("/api")
@RestController



public class Controller {
    @GetMapping("/get/{login}")
    public ResponseEntity<?> getStaticJson(@PathVariable String login) {
        SQL databaseManager = new SQL();
        User user = databaseManager.select(login);
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return ResponseEntity.ok(user);
    }

    @PostMapping("/processJson")
    public ResponseEntity<?> processJson(@RequestBody Map<String,String> userReq) {
        try {
            if((userReq.size()!=4) || (!userReq.containsKey("login")) || (!userReq.containsKey("password")) || (!userReq.containsKey("timestamp")) || (!userReq.containsKey("email"))){
                throw new Exception("BAD JSON");
            }
            SQL databaseManager = new SQL();
            User user = new User(userReq.get("login"), userReq.get("password"),userReq.get("timestamp"), userReq.get("email"));
            int rowsUpdated = databaseManager.insert(user);
            if (rowsUpdated > 0) {
                return ResponseEntity.ok("User inserted successfully: " + user);
            } else {
                throw new Exception("Failed to insert user into database");
            }

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }

    }

}
