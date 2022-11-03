package br.com.senac.backend.resources;

import br.com.senac.backend.entities.Campaign;
import br.com.senac.backend.services.CampaignService;
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
@RequestMapping(value = "/campaigns")
public class CampaignResource {

    @Autowired
    private CampaignService campaignService;

    @GetMapping
    public ResponseEntity<?> findAll() {
        var context = ContextLog.builder()
                .classe(Thread.currentThread().getStackTrace()[1].getClassName())
                .metodo(Thread.currentThread().getStackTrace()[1].getMethodName())
                .build();
        log.info(RSC_0001D.logContext(context.getClasse(), context.getMetodo()));

        var userList = campaignService.findAll();

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

        var obj = campaignService.findById(id);

        log.info(RSC_0002D.logContext(context.getClasse(), context.getMetodo()));
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    public ResponseEntity<?> insert(@RequestBody Campaign obj) {
        var context = ContextLog.builder()
                .classe(Thread.currentThread().getStackTrace()[1].getClassName())
                .metodo(Thread.currentThread().getStackTrace()[1].getMethodName())
                .build();
        log.info(RSC_0001D.logContext(context.getClasse(), context.getMetodo()));

        obj = campaignService.insert(obj);
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

        campaignService.delete(id);

        log.info(RSC_0004D.getObjDescription(id.toString()));
        log.info(RSC_0002D.logContext(context.getClasse(), context.getMetodo()));
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Campaign obj) {
        var context = ContextLog.builder()
                .classe(Thread.currentThread().getStackTrace()[1].getClassName())
                .metodo(Thread.currentThread().getStackTrace()[1].getMethodName())
                .build();
        log.info(RSC_0001D.logContext(context.getClasse(), context.getMetodo()));

        obj = campaignService.update(id, obj);

        log.info(RSC_0005D.getObjDescription(obj.toString()));
        log.info(RSC_0002D.logContext(context.getClasse(), context.getMetodo()));
        return ResponseEntity.ok().body(obj);
    }

}
