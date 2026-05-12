package idm.technic.challenge.controller;

import idm.technic.challenge.entity.ApiResponse;
import idm.technic.challenge.entity.Usuario;
import idm.technic.challenge.service.IdmService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/idm")
@Slf4j
public class IdmController {

  @Autowired
  private IdmService idmService;


  //Create
  @PostMapping("/create")
  public Mono<ApiResponse> create(@Valid @RequestBody Usuario usuario) {
    return idmService.create(usuario);
  }

  //Read
  @GetMapping("/findall")
  public Mono<ApiResponse> findAll() {
    return idmService.findAll();
  }

  @GetMapping("/findbyid/{id}")
  public Mono<ApiResponse> findById(@PathVariable Long id) {
    return idmService.findById(id);
  }


  //Update
  @PutMapping("/update")
  public Mono<ApiResponse> update(@RequestBody Usuario usuario) {
    return idmService.update(usuario);
  }

  //Delete
  @DeleteMapping("/delete/{id}")
  public Mono<ApiResponse> delete(@PathVariable Long id) {
    return idmService.delete(id);
  }
}
