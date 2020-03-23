package co.worker.board.domain.todo.controller;

import co.worker.board.domain.todo.model.TodoData;
import co.worker.board.domain.todo.model.TodoParam;
import co.worker.board.domain.todo.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/todo")
public class TodoController {

    @Autowired
    private TodoService todoService;

    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Object add(@RequestBody TodoParam todoParam) {
        return todoService.add(todoParam);
    }

    @GetMapping(value = "/{seq}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Object get(@PathVariable("seq") Long seq) {
        return todoService.get(seq);
    }

    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public Object list(){
        return todoService.list();
    }

    @DeleteMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public Object deleteAll(){
        return todoService.deleteAll();
    }

    @DeleteMapping(value = "/{seq}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Object delete(@PathVariable("seq") Long seq){
        return todoService.delete(seq);
    }

    @PutMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Object updateCompleted(@RequestBody TodoParam param){
        return todoService.updateCompleted(param);
    }
}
