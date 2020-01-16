package co.worker.board.home;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/home")
public class HomeController {

    @GetMapping("/get")
    public ResponseEntity getHome(@RequestParam("name") String name,
                                  @RequestParam("age") String age){
        Home home = new Home();
        home.setAge(Integer.parseInt(age));
        home.setName(name);

        return ResponseEntity.ok(home);
    }
}
