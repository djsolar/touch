package com.twinflag.touch.service;

import com.twinflag.touch.model.Program;
import com.twinflag.touch.model.User;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface TemplateService {

    void saveTemplate(Program program);

    void uploadTemplate(MultipartFile file, String fileName) throws Exception;

    Program findTemplate(Integer id);

    DataTablesOutput<Program> findAllTemplate(DataTablesInput dataTablesInput);

    boolean deleteTemplate(Integer id);

    List<Object> getTemplateInfos();
}
