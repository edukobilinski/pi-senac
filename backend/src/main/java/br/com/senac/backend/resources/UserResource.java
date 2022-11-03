package br.com.senac.backend.resources;

import br.com.senac.backend.entities.User;
import br.com.senac.backend.services.UserService;
import br.com.senac.backend.utils.ContextLog;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

import static br.com.senac.backend.utils.MessageLogsEnum.*;

@Log
@RestController
@CrossOrigin
@RequestMapping(value = "/users")
public class UserResource {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<?> findAll() {
        var context = ContextLog.builder()
                .classe(Thread.currentThread().getStackTrace()[1].getClassName())
                .metodo(Thread.currentThread().getStackTrace()[1].getMethodName())
                .build();
        log.info(RSC_0001D.logContext(context.getClasse(), context.getMetodo()));

        var userList = userService.findAll();

        log.info(RSC_0002D.logContext(context.getClasse(), context.getMetodo()));
        return ResponseEntity.ok(userList);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        var context = ContextLog.builder()
                .classe(Thread.currentThread().getStackTrace()[1].getClassName())
                .metodo(Thread.currentThread().getStackTrace()[1].getMethodName())
                .build();
        log.info(RSC_0001D.logContext(context.getClasse(), context.getMetodo()));

        var obj = userService.findById(id);

        log.info(RSC_0002D.logContext(context.getClasse(), context.getMetodo()));
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    public ResponseEntity<?> insert(@RequestBody User obj) {
        var context = ContextLog.builder()
                .classe(Thread.currentThread().getStackTrace()[1].getClassName())
                .metodo(Thread.currentThread().getStackTrace()[1].getMethodName())
                .build();
        log.info(RSC_0001D.logContext(context.getClasse(), context.getMetodo()));

        obj = userService.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(obj.getId()).toUri();

        log.info(RSC_0003D.getObjDescription(uri.toString()));
        log.info(RSC_0002D.logContext(context.getClasse(), context.getMetodo()));
        return ResponseEntity.created(uri).body(obj);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        var context = ContextLog.builder()
                .classe(Thread.currentThread().getStackTrace()[1].getClassName())
                .metodo(Thread.currentThread().getStackTrace()[1].getMethodName())
                .build();
        log.info(RSC_0001D.logContext(context.getClasse(), context.getMetodo()));

        userService.delete(id);

        log.info(RSC_0004D.getObjDescription(id.toString()));
        log.info(RSC_0002D.logContext(context.getClasse(), context.getMetodo()));
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody User obj) {
        var context = ContextLog.builder()
                .classe(Thread.currentThread().getStackTrace()[1].getClassName())
                .metodo(Thread.currentThread().getStackTrace()[1].getMethodName())
                .build();
        log.info(RSC_0001D.logContext(context.getClasse(), context.getMetodo()));

        obj = userService.update(id, obj);

        log.info(RSC_0005D.getObjDescription(obj.toString()));
        log.info(RSC_0002D.logContext(context.getClasse(), context.getMetodo()));
        return ResponseEntity.ok().body(obj);
    }

}
