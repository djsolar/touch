package com.twinflag.touch.service;

import com.twinflag.touch.entity.DataTableViewPage;
import com.twinflag.touch.model.Program;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface TemplateService {

    void saveTemplate(Program program);

    void uploadTemplate(MultipartFile file, String fileName) throws Exception;

    Program findTemplate(Integer id);

    DataTableViewPage<Program> findAllTemplate(int page, int pageSize);

    boolean deleteTemplate(Integer id);

    List<Object> getTemplateInfos();
}
