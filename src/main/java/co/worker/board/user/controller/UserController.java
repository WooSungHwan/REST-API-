package co.worker.board.user.controller;

import co.worker.board.user.model.UserParam;
import co.worker.board.user.service.UserService;
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
        userService.add(param);
        return null;
    }

    @GetMapping("/idcheck/{userId}")
    public Object idcheck(@PathVariable("userId") String userId){
        userService.idcheck(userId);
        return "사용 가능한 아이디입니다.";
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
