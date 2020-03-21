package co.worker.board.domain.user.controller;

import co.worker.board.domain.user.model.UserParam;
import co.worker.board.domain.user.service.UserService;
import co.worker.board.util.Word;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;

@RestController
@RequestMapping("/api/users")
@Validated
public class UserController {

    private UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/add")
    public Object add(@RequestBody @Valid UserParam param){
        return userService.add(param);
    }

    @GetMapping("/idcheck/{userId}")
    public Object idcheck(@PathVariable("userId") String userId){
        userService.idcheck(userId);
        return Word.USER_ID_AVAILABLE;
    }

    @PutMapping("/edit/{seq}")
    public Object edit(@RequestBody @Valid UserParam param, @PathVariable("seq") @Min(1) Long seq){
        param.setSeq(seq);
        userService.edit(param);
        return null;
    }

    @GetMapping("/{seq}")
    public Object getUserOne(@PathVariable("seq") @Min(1) Long seq){
        return userService.get(seq);
    }

    @GetMapping("/all")
    public Object getUserAll(){
        return userService.getAll();
    }

    @DeleteMapping("/{seq}")
    public Object delete(@PathVariable("seq") @Min(1) Long seq){
        userService.delete(seq);
        return null;
    }
}
