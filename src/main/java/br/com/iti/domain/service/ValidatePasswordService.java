package br.com.iti.domain.service;

import br.com.iti.domain.model.ResponseDTO;

public interface ValidatePasswordService {

    ResponseDTO validate(String password);
}
